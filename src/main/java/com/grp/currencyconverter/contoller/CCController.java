
package com.grp.currencyconverter.contoller;

import com.grp.currencyconverter.model.ComboBoxCurrency;
import com.grp.currencyconverter.model.CurrencyRate;
import com.grp.currencyconverter.api.RequestProcessor;
import com.grp.currencyconverter.configurations.InternetChecker;
import com.grp.currencyconverter.configurations.SystemChecker;
import com.grp.currencyconverter.service.ComboBoxCurrencyService;
import com.grp.currencyconverter.service.CurrencyConversionService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * CCController is the controller class for the Currency Converter application. It manages the interaction
 * between the user interface defined in the corresponding FXML file and the backend services.
 * This class implements the Initializable interface to initialize the controller when the FXML file is loaded.
 */
public class CCController implements Initializable {

    // FXML components injected by the FXMLLoader
    @FXML
    private VBox ParentComponent;
    @FXML
    private TextField amount;
    @FXML
    private VBox amountVBox;
    @FXML
    private Button convert;
    @FXML
    private ListView<CurrencyRate> currencyRateList;
    @FXML
    private Text current_amount;
    @FXML
    private Label current_amount_label;
    @FXML
    private Text current_info;
    @FXML
    private VBox fromVBox;
    @FXML
    private VBox innerAmountVBox;
    @FXML
    private VBox innerFromVBox;
    @FXML
    private VBox innerToVBox;
    @FXML
    private VBox mainVBoxComponent;
    @FXML
    private VBox toVBox;
    @FXML
    private ComboBox<ComboBoxCurrency> from;
    @FXML
    private ComboBox<ComboBoxCurrency> to;

    // Properties and utilities
    private InternetChecker internetChecker;
    private final StringProperty fromSelected = new SimpleStringProperty();
    private final StringProperty toSelected = new SimpleStringProperty();
    private final BooleanProperty internet = new SimpleBooleanProperty(false);

    /**
     * Handles the currency conversion process when the Convert button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the Convert button.
     */
    @FXML
    void convert(ActionEvent event) {
        convert.setDisable(true); // Disable the Convert button to prevent multiple clicks
        // Initiate the currency conversion process using the CurrencyConversionService
        CurrencyConversionService.convertCurrency(current_amount, amount.getText(), fromSelected.getValue(), toSelected.getValue());
        convert.setDisable(false); // Enable the Convert button after conversion is complete
    }

    /**
     * Handles the selection of the "From" currency in the ComboBox.
     *
     * @param event The ActionEvent triggered by selecting a currency in the "From" ComboBox.
     */
    @FXML
    void fromFunction(ActionEvent event) {
        fromSelected.setValue(from.getValue().getCurrencyCode());
    }

    /**
     * Handles the selection of the "To" currency in the ComboBox.
     *
     * @param event The ActionEvent triggered by selecting a currency in the "To" ComboBox.
     */
    @FXML
    void toFunction(ActionEvent event) {
        toSelected.setValue(to.getValue().getCurrencyCode());
    }

    /**
     * Initializes the controller when the FXML file is loaded.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the internetChecker to check internet connectivity
        internetChecker = SystemChecker.getOperatingSystem();
        internet.setValue(internetChecker.checkInternet());
        boolean isConnected = internet.getValue();
        // If not connected to the internet, show an alert message
        if (!isConnected) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Currency Converter");
            alert.setHeaderText("Please I need the internet to work");
            alert.setContentText("Please note that if you are not connected to the internet you won't be able to do make currency conversions");
            alert.showAndWait();
        }

        convert.setStyle("-fx-font-size:25"); // Set the style of the Convert button
        ComboBoxCurrencyService.fetchComboCurrencies(from, to); // Fetch combo currencies for "From" and "To" ComboBoxes
        RequestProcessor.requestProcessorForCurrencyList(currencyRateList); // Process currency list request
        amount.setText("1"); // Set default amount value
    }
}
