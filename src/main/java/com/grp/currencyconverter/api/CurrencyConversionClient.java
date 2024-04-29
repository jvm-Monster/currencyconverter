/**
 * The CurrencyConversionClient class provides methods to interact with the currency conversion API
 * for converting currency amounts from one currency to another.
 */
package com.grp.currencyconverter.api;

import com.grp.currencyconverter.api.info.ApiInfo;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static java.lang.StringTemplate.STR;

public class CurrencyConversionClient {

    // Instance variable
    private static final AsyncHttpClient client = new DefaultAsyncHttpClient(); // Asynchronous HTTP client for making API requests

    /**
     * Initiates a currency conversion request to the currency conversion API.
     *
     * @param amount The amount of currency to convert.
     * @param from   The currency code of the source currency.
     * @param to     The currency code of the target currency.
     * @return CompletableFuture<Response> representing the response of the API request.
     * @throws IOException if an I/O error occurs during the request.
     */
    public static CompletableFuture<Response> requestConversion(int amount, String from, String to) throws IOException {
        // Construct the API URL for currency conversion
        String apiUrl = STR."\{ApiInfo.API_URL}convert?from=\{from}&to=\{to}&amount=\{amount}";
        String apiKey = ApiInfo.API_KEY; // API key for accessing the currency conversion API

        // Create and execute the asynchronous HTTP request to perform currency conversion
        return client
                .prepareGet(apiUrl)
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com")
                .execute()
                .toCompletableFuture();
    }

    /**
     * Cancels the ongoing currency conversion request represented by the given CompletableFuture object.
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
