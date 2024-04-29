/**
 * CurrencyConversionTask is a background task responsible for performing currency conversion asynchronously.
 * It extends the javafx.concurrent.Task class to execute its operation in a separate thread.
 * The task initiates a currency conversion request using the CurrencyConversionClient and processes the response
 * to obtain the converted amount.
 */
package com.grp.currencyconverter.tasks;

import com.grp.currencyconverter.api.CurrencyConversionClient;
import com.grp.currencyconverter.util.ConversionAmount;
import com.grp.currencyconverter.util.JsonUtils;
import javafx.concurrent.Task;
import org.asynchttpclient.Response;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CurrencyConversionTask extends Task<ConversionAmount> {

    // Variables to store conversion details
    private final String fromUsd;
    private final String toUsd;
    private final String amount;

    /**
     * Constructor to initialize the CurrencyConversionTask with conversion details.
     *
     * @param amount   The amount to be converted.
     * @param fromUsd  The currency code to convert from.
     * @param toUsd    The currency code to convert to.
     */
    public CurrencyConversionTask(String amount, String fromUsd, String toUsd) {
        this.fromUsd = fromUsd;
        this.toUsd = toUsd;
        this.amount = amount;
    }

    /**
     * Overrides the call() method of the Task class to perform the currency conversion operation.
     *
     * @return The ConversionAmount object representing the converted amount.
     * @throws Exception If an error occurs during the execution of the task.
     */
    @Override
    protected ConversionAmount call() throws Exception {
        System.out.println(isCancelled()); // For debugging purposes
        return convert();
    }

    /**
     * Initiates the currency conversion request and processes the response to obtain the converted amount.
     *
     * @return The ConversionAmount object representing the converted amount.
     * @throws IOException          If an I/O error occurs during the conversion process.
     * @throws ExecutionException   If the execution of the currency conversion request fails.
     * @throws InterruptedException If the currency conversion task is interrupted.
     */
    private ConversionAmount convert() throws IOException, ExecutionException, InterruptedException {
        // Initiate the currency conversion request asynchronously
        CompletableFuture<Response> responseCompletableFuture = CurrencyConversionClient.requestConversion(Integer.parseInt(amount), fromUsd, toUsd);

        // If the task is cancelled before the request is made, return null
        if (isCancelled()) {
            return null;
        }

        // Get the response from the future
        Response response = responseCompletableFuture.get();

        // If the task is cancelled after the request is made but before the response is received,
        // cancel the request and return null
        if (isCancelled()) {
            CurrencyConversionClient.cancelRequest(responseCompletableFuture);
            return null;
        }

        // Process the response and parse the JSON to obtain the converted amount
        if (response != null) {
            try {
                ConversionAmount conversionAmount = JsonUtils.parseJson(response.getResponseBody(), ConversionAmount.class);
                updateValue(conversionAmount); // Update the value of the task with the converted amount
                return conversionAmount;
            } catch (Exception e) {
                updateValue(new ConversionAmount());
            }
        }

        return new ConversionAmount();
    }
}
