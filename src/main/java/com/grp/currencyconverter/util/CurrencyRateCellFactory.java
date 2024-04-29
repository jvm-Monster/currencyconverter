package com.grp.currencyconverter.util;

import com.grp.currencyconverter.model.CurrencyRate;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CurrencyRateCellFactory implements Callback<ListView<CurrencyRate>, ListCell<CurrencyRate>> {
    @Override
    public ListCell<CurrencyRate> call(ListView<CurrencyRate> currencyRateListView) {
        return new ListCell<>() {

            @Override
            protected void updateItem(CurrencyRate currencyRate, boolean empty) {
                super.updateItem(currencyRate, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (currencyRate!=null) {
                    setText(STR."\{currencyRate.getCurrencyCode()} \{currencyRate.getRate()}");
                    //setGraphic(new Label(STR."\{currencyRate.getCurrencyCode()} \{currencyRate.getRate()}"));
                    currencyRateListView.setPadding(new Insets(10, 10, 10, 10));
                } else{
                    setText(null);
                    setGraphic(null);
                }

            }
        };
    }
}
