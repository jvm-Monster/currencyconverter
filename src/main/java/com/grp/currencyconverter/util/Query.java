package com.grp.currencyconverter.util;

public class Query {
    private String from;
    private String to;
    private int amount;
    public Query(){}
    public Query(String from, String to, int amount) {}

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
