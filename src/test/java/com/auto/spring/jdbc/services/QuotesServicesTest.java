package com.auto.spring.jdbc.services;

import java.text.ParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auto.spring.jdbc.model.Quote;
import com.auto.spring.jdbc.repository.JdbcQuoteRepository;
import com.auto.spring.jdbc.repository.QuoteRepository;

/**
 * @author
 *
 */
@ExtendWith(MockitoExtension.class)
public class QuotesServicesTest {

	@Mock
	JdbcQuoteRepository quoteRepository;

	@Mock
	QuoteRepository quotRepository;

	private QuotesServicesImpl quotesServices;

	@BeforeEach
	public void setup() {
		quotesServices = new QuotesServicesImpl(quotRepository);
	}

	@Test
	void getSaveQuotesTest() throws ParseException {

		Quote quote = new Quote();
		quote.setName("Jeff");
		quote.setQuote(200);
		quote.setDob("20-12-2000");

		int quoteID = quotesServices.saveQuotes(quote);
		Assertions.assertNotNull(quoteID);
		Assertions.assertEquals(quoteID, quote.getId());

	}

	@Test
	public void getPremiumTest() throws ParseException {
		Quote quote= new Quote();
		quote.setName("Jeff");
		quote.setQuote(200);
		quote.setDob("20-12-2000");

		double premium=quotesServices.getPremium(quote);
		Assertions.assertEquals(premium,90106.51706790138);


	}

}



