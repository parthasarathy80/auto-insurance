package com.auto.spring.jdbc.services;

import com.auto.spring.jdbc.model.Quote;

import java.text.ParseException;
import java.util.List;

public interface QuotesServices {
    public int saveQuotes(Quote quote) throws ParseException;
    public List<Quote> getLast24Quotes(String zip, String condition, double quoteValue);
    public double getPremium(Quote quote) throws ParseException;
}
