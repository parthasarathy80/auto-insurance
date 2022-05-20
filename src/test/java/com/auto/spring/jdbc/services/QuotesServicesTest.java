/**
 * 
 */
package com.auto.spring.jdbc.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.auto.spring.jdbc.model.Quote;
import com.auto.spring.jdbc.repository.QuoteRepository;

/**
 * @author
 *
 */
@RunWith(SpringRunner.class)
public class QuotesServicesTest {

	@MockBean
	QuoteRepository quoteRepository;

	@InjectMocks
	public QuotesServicesImpl quotesServices;
	
	@Test
	public void getSaveQuotesTest() throws ParseException {

		Quote quote = new Quote();
		quote.setName("Jeff");
		quote.setQuote(200);
		quote.setDob("02-12-1990");
		quote.setId(1);

		Mockito.when(quoteRepository.save(Mockito.any(Quote.class))).thenReturn(1);
		int quoteID = quotesServices.saveQuotes(quote);
		//Quote quoteCheck=quoteRepository.findById((long)quoteID);
		
		assertEquals(quoteID, quote.getId());

	}

}
