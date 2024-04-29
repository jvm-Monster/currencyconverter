package com.grp.currencyconverter.util;

public class ConversionAmount {
    private boolean success;
    private Query query;
    private Info info;
    private String date;
    private double result;
    private double amount;
    public ConversionAmount(){}
    public ConversionAmount(boolean success, Info info, String date, double result) {
        this.success = success;
        this.info = info;
        this.date = date;
        this.result = result;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Query getQuery() {
        return query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }
}
