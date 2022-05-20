package com.auto.spring.jdbc.services;

import com.auto.spring.jdbc.common.Constants;
import com.auto.spring.jdbc.repository.QuoteRepository;
import com.auto.spring.jdbc.common.UtilCommon;
import com.auto.spring.jdbc.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuotesServicesImpl implements QuotesServices {

    @Autowired
    QuoteRepository quoteRepository;

    
    

    @Override
    public int saveQuotes(Quote quote) throws ParseException{
       /* Random objGenerator = new Random();
        int quoteId=objGenerator.nextInt(10000000);*/

        quote.setQuote(this.getQuotes(quote));
        return quoteRepository.save(quote);

    }
    public double getQuotesPerMonth(Quote quote) throws ParseException {
        int age = calculateAge(quote.getDob());
        //600 + 0.3*(Math.abs(age - 50))  1.5,
        return Math.pow(600 + 0.3*(Math.abs(age - 50)),1.5) ;
    }

    public double getQuotes(Quote quote) throws ParseException {
        int remainingDays=UtilCommon.DaysRemaining();
        double monthlyPremiumQuote= this.getQuotesPerMonth(quote);
        return monthlyPremiumQuote*6; //6 Month rate

    }
    
    @Override
	public double getPremium(Quote quote) throws ParseException {
		int remainingDays = UtilCommon.DaysRemaining();
        double monthlyPremiumQuote = this.getQuotesPerMonth(quote);
        if (remainingDays>0) {


            int daysinMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
            double perdayQuote = monthlyPremiumQuote / daysinMonth;
            return (perdayQuote * remainingDays + (5 * monthlyPremiumQuote));// Pro-rated
        }
        else{
            return 6 * monthlyPremiumQuote;
        }

	}

    @Override
    public List<Quote> getLast24Quotes(String zip, String condition, double quoteValue){
        String quotesCondition;
        if("greater than".equalsIgnoreCase(condition)){
            quotesCondition="> "+quoteValue;
        }else{
            quotesCondition="< "+quoteValue;
        }

        return quoteRepository.findlast24hoursQuotes(zip,quotesCondition);
    }

    private int calculateAge(String dob) throws ParseException {
       Date d= UtilCommon.stringToDate(dob, Constants.dateFormatstr);
        LocalDate ld=UtilCommon.convertToLocalDateViaInstant(d);
       return  Period.between(ld,LocalDate.now()).getYears();
    }
}
