/**
 * The InternetChecker interface defines a contract for classes that perform internet connectivity checks.
 * Classes implementing this interface must provide an implementation for the checkInternet() method,
 * which checks whether the device is connected to the internet.
 */
package com.grp.currencyconverter.configurations;

public interface InternetChecker {

    /**
     * Checks whether the device is connected to the internet.
     *
     * @return true if the device is connected to the internet, false otherwise.
     */
    boolean checkInternet();
}
