/**
 * The RequestProcessor class provides methods for processing requests to retrieve currency conversion rates
 * and updating the user interface with the obtained data.
 */
package com.grp.currencyconverter.api;

import com.grp.currencyconverter.util.CurrencyRateCellFactory;
import com.grp.currencyconverter.util.JsonUtils;
import com.grp.currencyconverter.model.CurrencyRate;
import com.grp.currencyconverter.util.CurrencyList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.asynchttpclient.Response;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class RequestProcessor {

    /**
     * Initiates an asynchronous request to retrieve the latest currency conversion rates,
     * and updates the provided ListView with the obtained currency rates.
     *
     * @param currencyRateList The ListView component to be updated with currency rates.
     */
    public static void requestProcessorForCurrencyList(ListView<CurrencyRate> currencyRateList) {
        CompletableFuture.supplyAsync(() -> {
            try {
                return CurrencyListApi.currencyLists();
            } catch (Exception e) {
                return null;
            }
        }).thenAcceptAsync(response -> {
            handleCurrencyListResponse(response, currencyRateList);
        });
    }

    /**
     * Handles the response from the currency list API and updates the ListView with currency rates.
     *
     * @param response        The response received from the currency list API.
     * @param currencyRateList The ListView component to be updated with currency rates.
     */
    private static void handleCurrencyListResponse(Response response, ListView<CurrencyRate> currencyRateList) {
        if (response != null && response.getStatusCode() == 200) {
            try {
                processSuccessfulResponse(response, currencyRateList);
            } catch (IOException e) {
                currencyRateList.getItems().add(new CurrencyRate("currency code error", 0.0));
            }
        } else {
            currencyRateList.getItems().add(new CurrencyRate("currency code error", 0.0));
        }
    }

    /**
     * Processes a successful response from the currency list API and updates the ListView with currency rates.
     *
     * @param response        The response received from the currency list API.
     * @param currencyRateList The ListView component to be updated with currency rates.
     * @throws IOException if an I/O error occurs while parsing the response.
     */
    private static void processSuccessfulResponse(Response response, ListView<CurrencyRate> currencyRateList) throws IOException {
        String responseBody = response.getResponseBody();
        CurrencyList currencyList = JsonUtils.parseJson(responseBody, CurrencyList.class);
        if (currencyList != null) {
            updateCurrencyRates(currencyList, currencyRateList);
        }
    }

    /**
     * Updates the ListView with currency rates obtained from the response.
     *
     * @param currencyList    The CurrencyList object containing currency rates.
     * @param currencyRateList The ListView component to be updated with currency rates.
     */
    private static void updateCurrencyRates(CurrencyList currencyList, ListView<CurrencyRate> currencyRateList) {
        List<CurrencyRate> currencyRates = new ArrayList<>();
        List<String> currencyCodes = new ArrayList<>();
        for (Map.Entry<String, Object> entry : currencyList.getRates().entrySet()) {
            CurrencyRate currencyRate = new CurrencyRate(entry.getKey(), Double.parseDouble(entry.getValue().toString()));
            currencyRates.add(currencyRate);
            currencyCodes.add(currencyRate.getCurrencyCode());
        }
        ObservableList<CurrencyRate> observableCurrencyItem = FXCollections.observableArrayList(currencyRates);
        FXCollections.sort(observableCurrencyItem, Comparator.comparing(CurrencyRate::getCurrencyCode));

        currencyRateList.setItems(observableCurrencyItem);
        currencyRateList.setCellFactory(_ -> new CurrencyRateCellFactory().call(currencyRateList));
    }

}
