module com.grp.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    //requires MaterialFX;
    requires javafx.graphics;
    requires async.http.client;
    requires com.fasterxml.jackson.databind;
    requires net.bytebuddy;

    exports com.grp.currencyconverter.util;

    opens com.grp.currencyconverter to javafx.fxml;
    exports com.grp.currencyconverter;
    exports com.grp.currencyconverter.api;
    opens com.grp.currencyconverter.api to javafx.fxml;
    exports com.grp.currencyconverter.api.info;
    opens com.grp.currencyconverter.api.info to javafx.fxml;
    exports com.grp.currencyconverter.contoller;
    opens com.grp.currencyconverter.contoller to javafx.fxml;
    exports com.grp.currencyconverter.configurations;
    opens com.grp.currencyconverter.configurations to javafx.fxml;
    exports com.grp.currencyconverter.tasks;
    opens com.grp.currencyconverter.tasks to javafx.fxml;
    exports com.grp.currencyconverter.model;
    opens com.grp.currencyconverter.model to javafx.fxml;
    opens com.grp.currencyconverter.util to javafx.fxml;


}