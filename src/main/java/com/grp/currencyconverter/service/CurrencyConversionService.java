/**
 * CurrencyConversionService is a utility class designed to facilitate currency conversion operations
 * by interacting with CurrencyConversionTask and updating the user interface accordingly.
 * It provides a static method convertCurrency() to initiate currency conversion process.
 */
package com.grp.currencyconverter.service;

import com.grp.currencyconverter.tasks.CurrencyConversionTask;
import com.grp.currencyconverter.util.CurrencyDecimalFormatter;
import javafx.scene.text.Text;

public class CurrencyConversionService {

    // Static instance of CurrencyConversionTask
    private static CurrencyConversionTask currencyConversionTask;

    // Private constructor to prevent instantiation of this utility class
    private CurrencyConversionService() {
        // Private constructor to prevent instantiation
    }

    /**
     * Initiates currency conversion process.
     *
     * @param uiToUpdate   The Text node in the UI to be updated with the converted amount.
     * @param amount       The amount to be converted.
     * @param fromCurrency The currency to convert from.
     * @param toCurrency   The currency to convert to.
     */
    public static void convertCurrency(Text uiToUpdate, String amount, String fromCurrency, String toCurrency) {
        // If there's an ongoing conversion task, cancel it before starting a new one
        if (currencyConversionTask != null && currencyConversionTask.isRunning()) {
            System.out.println("Currency conversion task is already running, cancelling it...");
            currencyConversionTask.cancel();
        }

        // Create a new CurrencyConversionTask with the provided parameters
        currencyConversionTask = new CurrencyConversionTask(amount, fromCurrency, toCurrency);

        // Add a listener to the task's valueProperty to update the UI with the converted amount
        currencyConversionTask.valueProperty().addListener((observableValue, oldConversionAmount, newConversionAmount) -> {
            // Update the UI with the converted amount
            if (newConversionAmount != null) {
                String formattedAmount = CurrencyDecimalFormatter.convertAmountToDecimal(newConversionAmount.getResult());
                System.out.println(STR."Converted amount: \{formattedAmount}");
                // Update UI here using the provided Node uiToUpdate
                uiToUpdate.setText(formattedAmount);
            } else {
                System.out.println("Conversion failed or task was cancelled.");
            }
        });

        // Start the currency conversion task in a separate thread
        Thread thread = new Thread(currencyConversionTask);
        thread.setDaemon(true); // Set the thread as daemon to allow the application to exit even if it's running
        thread.start();
    }
}
