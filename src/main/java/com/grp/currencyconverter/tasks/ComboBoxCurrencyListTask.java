/**
 * ComboCurrencyListTask is a background task responsible for fetching a list of ComboBoxCurrency objects
 * from a remote API asynchronously. It extends the javafx.concurrent.Task class to perform its operation
 * in a separate thread.
 * The task retrieves data from the ComboCurrencyListApi and processes the response to generate a list
 * of ComboBoxCurrency objects.
 */
package com.grp.currencyconverter.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.grp.currencyconverter.api.ComboBoxCurrencyListApi;
import com.grp.currencyconverter.api.CurrencyConversionClient;
import com.grp.currencyconverter.model.ComboBoxCurrency;
import com.grp.currencyconverter.api.ComboBoxCurrencyClient;
import com.grp.currencyconverter.util.JsonUtils;
import javafx.concurrent.Task;
import org.asynchttpclient.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ComboBoxCurrencyListTask extends Task<List<ComboBoxCurrency>> {

    /**
     * Overrides the call() method of the Task class to perform the currency list retrieval operation.
     * It initiates an asynchronous call to the ComboCurrencyListApi to fetch the currency data.
     *
     * @return A List of ComboBoxCurrency objects representing currency codes and their corresponding country names.
     * @throws Exception If an error occurs during the execution of the task.
     */
    @Override
    protected List<ComboBoxCurrency> call() throws Exception {
        CompletableFuture<Response> futureResponse = ComboBoxCurrencyListApi.currencyLists();

        if (isCancelled()) {
            // Task was cancelled before executing
            return null;
        }

        if (futureResponse == null) {
            // Future is null, return null
            return null;
        }

        try {
            Response response = futureResponse.get();

            if (isCancelled()) {
                // Task was cancelled after executing but before getting the response
                CurrencyConversionClient.cancelRequest(futureResponse);
                return null;
            }
            if (response != null) {
                ComboBoxCurrencyClient comboBoxCurrencyClient = JsonUtils.parseJson(response.getResponseBody(), new TypeReference<>() {});
                List<ComboBoxCurrency> comboBoxCurrencyList = convertMapToList(comboBoxCurrencyClient.getCurrencySymbol());

                updateValue(comboBoxCurrencyList);
                return comboBoxCurrencyList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Converts a Map of currency codes and country names into a list of ComboBoxCurrency objects.
     *
     * @param currencyMap A Map containing currency codes as keys and country names as values.
     * @return A List of ComboBoxCurrency objects.
     */
    private static List<ComboBoxCurrency> convertMapToList(Map<String, String> currencyMap) {
        List<ComboBoxCurrency> comboBoxCurrencyList = new ArrayList<>();
        for (Map.Entry<String, String> entry : currencyMap.entrySet()) {
            ComboBoxCurrency comboBoxCurrency = new ComboBoxCurrency();
            comboBoxCurrency.setCurrencyCode(entry.getKey());
            comboBoxCurrency.setCountryName(entry.getValue());
            comboBoxCurrencyList.add(comboBoxCurrency);
        }
        return comboBoxCurrencyList;
    }
}
