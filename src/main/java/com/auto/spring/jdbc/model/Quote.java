package com.auto.spring.jdbc.model;

import java.util.Objects;

public class Quote {


    private long id;

    private String name;

    private String zip;
    private String dob;

    private double quote;

    public Quote() {
    }

    public Quote( String name, String zip, String dob) {
        this.name = name;
        this.zip = zip;
        this.dob = dob;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return id == quote.id && Objects.equals(name, quote.name) && Objects.equals(zip, quote.zip) && Objects.equals(dob, quote.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, zip, dob);
    }
}
