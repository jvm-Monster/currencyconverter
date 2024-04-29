/**
 * The ComboCurrencyListApi class provides methods to interact with the combo currency API
 * for retrieving a list of currency symbols and their corresponding country names.
 */
package com.grp.currencyconverter.api;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ComboBoxCurrencyListApi {

    // Instance variable
    private static AsyncHttpClient client; // Asynchronous HTTP client for making API requests

    /**
     * Retrieves a CompletableFuture object representing the asynchronous HTTP request to the combo currency API.
     *
     * @return CompletableFuture<Response> representing the response of the API request.
     */
    public static CompletableFuture<Response> currencyLists() {
        try {
            client = new DefaultAsyncHttpClient(); // Initialize the asynchronous HTTP client
            // API key for accessing the combo currency API
            String apiKey = "2752d563c2msh97eea80ccbe85c5p1748cfjsn5fa72ff4f8d2";
            // Create and execute the asynchronous HTTP request to retrieve currency symbols
            return client.prepare("GET", "https://currency-conversion-and-exchange-rates.p.rapidapi.com/symbols")
                    .setHeader("X-RapidAPI-Key", "2752d563c2msh97eea80ccbe85c5p1748cfjsn5fa72ff4f8d2")
                    .setHeader("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture();
        } catch (Exception e) {
            return null; // Return null if an exception occurs during the API request
        }
    }

    /**
     * Cancels the ongoing API request represented by the given CompletableFuture object.
     *
     * @param responseFuture CompletableFuture<Response> representing the ongoing API request.
     * @throws IOException if an I/O error occurs while closing the asynchronous HTTP client.
     */
    public static void cancelRequest(CompletableFuture<Response> responseFuture) throws IOException {
        if (!responseFuture.isDone()) {
            // If the request is not completed, cancel it and close the asynchronous HTTP client
            responseFuture.cancel(true);
            client.close();
        }
    }
}
