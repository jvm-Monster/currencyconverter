package com.grp.currencyconverter.util;

import java.text.DecimalFormat;

public class CurrencyDecimalFormatter {
    public static String convertAmountToDecimal(double amount){
        DecimalFormat decimalFormat = new DecimalFormat("#.00000");
        return decimalFormat.format(amount);
    }
}
