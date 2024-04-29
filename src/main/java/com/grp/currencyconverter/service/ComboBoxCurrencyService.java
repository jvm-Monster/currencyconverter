package com.grp.currencyconverter.service;

import com.grp.currencyconverter.model.ComboBoxCurrency;
import com.grp.currencyconverter.tasks.ComboBoxCurrencyListTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

/**
 * The ComboCurrencyService class provides methods for fetching and updating combo boxes
 * containing currency information.
 */
public class ComboBoxCurrencyService {

    /** The task for fetching combo currency lists. */
    private static ComboBoxCurrencyListTask comboBoxCurrencyListTask;

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private ComboBoxCurrencyService() {
        // Private constructor to prevent instantiation
    }

    /**
     * Fetches combo currencies and populates the provided combo boxes.
     *
     * @param ui1 The first combo box to populate.
     * @param ui2 The second combo box to populate.
     */
    public static void fetchComboCurrencies(ComboBox<ComboBoxCurrency> ui1, ComboBox<ComboBoxCurrency> ui2) {
        if (comboBoxCurrencyListTask != null && comboBoxCurrencyListTask.isRunning()) {
            System.out.println("Combo currencies are already being fetched");
            comboBoxCurrencyListTask.cancel();
        }

        comboBoxCurrencyListTask = new ComboBoxCurrencyListTask();
        comboBoxCurrencyListTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ObservableList<ComboBoxCurrency> comboBoxItems = FXCollections.observableArrayList(newValue);
                comboBoxItems.sort(Comparator.comparing(ComboBoxCurrency::getCurrencyCode));

                ui1.setItems(comboBoxItems);
                ui2.setItems(comboBoxItems);
                updateComboBox(ui1);
                updateComboBox(ui2);
            }
        });

        Thread thread = new Thread(comboBoxCurrencyListTask);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Sets a custom cell factory for the provided combo box to display currency information.
     *
     * @param object The combo box for which to set the cell factory.
     */
    public static void updateComboBox(ComboBox<ComboBoxCurrency> object) {
        object.setCellFactory(comboBoxCurrencyListView -> new ListCell<>() {
            @Override
            protected void updateItem(ComboBoxCurrency item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    // Display currency code and country name in an HBox
                    HBox hBox = new HBox(new Text(item.getCurrencyCode()), new Text(item.getCountryName()));
                    hBox.setSpacing(20);
                    setGraphic(hBox);
                }
            }
        });
    }
}
