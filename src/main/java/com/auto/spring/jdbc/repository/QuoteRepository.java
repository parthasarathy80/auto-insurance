package com.auto.spring.jdbc.repository;

import com.auto.spring.jdbc.model.Quote;

import java.util.List;

public interface QuoteRepository {

    int save(Quote quote) ;

    int update(Quote quote);

    Quote findById(Long id);


    List<Quote> findAll();

    public List<Quote> findlast24hoursQuotes(String zip, String quoteCondition);


}
