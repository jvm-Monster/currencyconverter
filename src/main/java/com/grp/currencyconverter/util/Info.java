package com.grp.currencyconverter.util;

public class Info {
    private long timestamp;
    private double rate;

    public Info(){}
    public Info(long timestamp, double rate) {
        this.timestamp = timestamp;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
}
