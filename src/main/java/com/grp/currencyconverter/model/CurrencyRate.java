package com.grp.currencyconverter.model;

/**
 * The CurrencyRate class represents the exchange rate of a currency.
 * It contains information about the currency code and its corresponding exchange rate.
 */
public class CurrencyRate {

    /** The currency code. */
    private String currencyCode;

    /** The exchange rate of the currency. */
    private double rate;

    /**
     * Constructs a new CurrencyRate object with the specified currency code and exchange rate.
     *
     * @param currencyCode The currency code.
     * @param rate The exchange rate.
     */
    public CurrencyRate(String currencyCode, double rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    /**
     * Gets the currency code.
     *
     * @return The currency code.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Gets the exchange rate of the currency.
     *
     * @return The exchange rate.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets the currency code.
     *
     * @param currencyCode The currency code to set.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Sets the exchange rate of the currency.
     *
     * @param rate The exchange rate to set.
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Returns a string representation of the CurrencyRate object.
     * This representation includes the currency code and exchange rate.
     *
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        return STR."CurrencyRate{currencyCode='\{currencyCode}', rate=\{rate}\{'}'}";
    }
}
