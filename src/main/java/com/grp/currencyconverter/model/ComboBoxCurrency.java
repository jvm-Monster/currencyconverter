package com.grp.currencyconverter.model;

/**
 * The ComboBoxCurrency class represents a currency item in a combo box,
 * containing information about the currency code and the country name.
 */
public class ComboBoxCurrency {

    /** The currency code. */
    private String currencyCode;

    /** The country name associated with the currency. */
    private String countryName;

    /**
     * Constructs a new ComboBoxCurrency object with default values.
     */
    public ComboBoxCurrency() {
        // Default constructor
    }

    /**
     * Constructs a new ComboBoxCurrency object with the specified currency code and country name.
     *
     * @param currencyCode The currency code.
     * @param countryName The country name associated with the currency.
     */
    public ComboBoxCurrency(String currencyCode, String countryName) {
        this.currencyCode = currencyCode;
        this.countryName = countryName;
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
     * Sets the currency code.
     *
     * @param currencyCode The currency code to set.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Gets the country name associated with the currency.
     *
     * @return The country name.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name associated with the currency.
     *
     * @param countryName The country name to set.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Returns a string representation of the ComboBoxCurrency object.
     * This representation includes the currency code.
     *
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        return currencyCode;
    }
}
