/**
 * The ComboCurrencyClient class represents the response object received from the combo currency API.
 * It contains information about the success status of the request and a map of currency symbols and their corresponding country names.
 */
package com.grp.currencyconverter.api;

import java.util.Map;

public class ComboBoxCurrencyClient {

    // Instance variables
    private boolean success; // Indicates whether the request was successful
    private Map<String, String> symbols; // Map of currency symbols and their corresponding country names

    /**
     * Default constructor for the ComboCurrencyClient class.
     */
    public ComboBoxCurrencyClient() {}

    /**
     * Parameterized constructor for the ComboCurrencyClient class.
     *
     * @param success Indicates whether the request was successful.
     * @param symbols Map of currency symbols and their corresponding country names.
     */
    public ComboBoxCurrencyClient(boolean success, Map<String, String> symbols) {
        this.success = success;
        this.symbols = symbols;
    }

    /**
     * Getter method for retrieving the map of currency symbols and their corresponding country names.
     *
     * @return A map containing currency symbols and their corresponding country names.
     */
    public Map<String, String> getCurrencySymbol() {
        return symbols;
    }

    /**
     * Getter method for retrieving the success status of the request.
     *
     * @return true if the request was successful, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for setting the success status of the request.
     *
     * @param success true if the request was successful, false otherwise.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Setter method for setting the map of currency symbols and their corresponding country names.
     *
     * @param symbols A map containing currency symbols and their corresponding country names.
     */
    public void setSymbols(Map<String, String> symbols) {
        this.symbols = symbols;
    }
}
