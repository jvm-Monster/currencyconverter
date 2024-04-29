/**
 * The CurrencyListApi class provides a method to interact with the currency list API
 * for retrieving the latest currency conversion rates.
 */
package com.grp.currencyconverter.api;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;

public class CurrencyListApi {

    /**
     * Retrieves the response from the currency list API, which contains the latest currency conversion rates.
     *
     * @return Response object representing the response of the API request.
     */
    public static Response currencyLists() {
        try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {
            // API key for accessing the currency list API
            String apiKey = "2752d563c2msh97eea80ccbe85c5p1748cfjsn5fa72ff4f8d2";
            // Create and execute the asynchronous HTTP request to retrieve currency lists
            CompletableFuture<Response> responseFuture = client.prepare("GET", "https://currency-conversion-and-exchange-rates.p.rapidapi.com/latest?from=USD&to=EUR%2CGBP")
                    .setHeader("X-RapidAPI-Key", "2752d563c2msh97eea80ccbe85c5p1748cfjsn5fa72ff4f8d2")
                    .setHeader("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture();
            return responseFuture.get(); // Wait for the response and return it
        } catch (Exception e) {
            return null; // Return null if an exception occurs during the API request
        }
    }
}
