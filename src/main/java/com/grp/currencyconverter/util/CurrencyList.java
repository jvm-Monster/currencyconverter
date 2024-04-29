package com.grp.currencyconverter.util;

import java.util.Map;

public class CurrencyList {
    private long timestamp;
    private String base;
    private boolean success;
    private String date;
    private Map<String,Object> rates;

    public CurrencyList() {}
    public CurrencyList(long timestamp, String base, boolean success, String date, Map<String,Object> rates) {}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Object> getRates() {
        return rates;
    }

    public void setRates(Map<String, Object> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return STR."CurrencyListApiObject{timestamp=\{timestamp}, base='\{base}\{'\''}, success=\{success}, date='\{date}\{'\''}, rates=\{rates}\{'}'}";
    }
}
