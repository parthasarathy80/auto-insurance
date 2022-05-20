package com.auto.spring.jdbc.controller;

import com.auto.spring.jdbc.common.Constants;
import com.auto.spring.jdbc.model.Quote;
import com.auto.spring.jdbc.repository.QuoteRepository;
import com.auto.spring.jdbc.services.QuotesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class QuotesController {


    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    QuotesServices quotesServices;

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes(@RequestParam(required = false) String title) {
        try {
            List<Quote> quotes = new ArrayList<Quote>();

            quoteRepository.findAll().forEach(quotes::add);

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable("id") long id) {
        Quote quote = quoteRepository.findById(id);

        if (quote != null) {
            return new ResponseEntity<>(quote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/quotes")
    public ResponseEntity<String> createQuote(@RequestBody Quote quote) {
        try {
            Quote quoteObj = new Quote(quote.getName(), quote.getZip(), quote.getDob());
            int id = quotesServices.saveQuotes(quoteObj);
            return new ResponseEntity<>("Quote was created successfully. Quote ID  =" + id + "  Quote Value =" + quoteObj.getQuote(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ///quotes/08512?quoteValue=4000&condition=greaterThan
    @GetMapping("/quotes/zip/{zip}")
    public ResponseEntity<List<Quote>> getQuotesByIdgetLast24Quotes(@PathVariable("zip") String zip, @RequestParam Map<String, String> reqParam) {
        try {
            List<Quote> lsquotes = new ArrayList<>();
            double quote = Double.parseDouble(reqParam.get(Constants.QUOTES_VALUE));
            quotesServices.getLast24Quotes(zip, reqParam.get(Constants.CONDITION), quote).forEach(lsquotes::add);

            if (lsquotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lsquotes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/premium/{id}")//Prorated
    public ResponseEntity<Object> getPremium(@PathVariable("id") long id) {
        try {
            Quote quote = quoteRepository.findById(id);
            double premium = quotesServices.getPremium(quote);
            return new ResponseEntity<>(
                    "Quote ID  =" + id + "  premium Quote Value =" + premium,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

