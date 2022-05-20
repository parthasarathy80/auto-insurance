package com.auto.spring.jdbc.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.auto.spring.jdbc.model.Quote;
import com.auto.spring.jdbc.repository.QuoteRepository;
import com.auto.spring.jdbc.services.QuotesServices;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QuotesController.class)
//@TestPropertySource(properties = "spring.config.additional-location=classpath:application-test.properties")
public class QuotesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    QuoteRepository quoteRepository;
    
    @MockBean
    QuotesServices quotesServices;


    @Test
    void testGetAllQuotesOkStatus() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/quotes").param("title", "testing")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        List<Quote> quotes = new ArrayList<Quote>();
        quotes.add(new Quote());
        Mockito.when(quoteRepository.findAll()).thenReturn(quotes);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void testGetAllQuotesNOContentFound() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/quotes").param("title", "testing")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        List<Quote> quotes = new ArrayList<Quote>();
        Mockito.when(quoteRepository.findAll()).thenReturn(quotes);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

    }

    @Test
    void testGetAllQuotesInternalServerError() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/quotes").param("title", "test")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        Mockito.when(quoteRepository.findAll()).thenReturn(Mockito.anyList());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

    }

    @Test
    void testGetQuotesByIdOKStatus() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/quotes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(quoteRepository.findById(Mockito.anyLong())).thenReturn(new Quote());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void testGetQuoteByIdNotFound() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/quotes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }

}
