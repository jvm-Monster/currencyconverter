/**
 * The UnknownInternetChecker class is an implementation of the InternetChecker interface
 * designed to handle unknown or unsupported operating systems. It always returns false,
 * indicating that internet connectivity status is unknown or cannot be determined.
 */
package com.grp.currencyconverter.configurations;

public class UnknownInternetChecker implements InternetChecker {

    /**
     * Checks whether the device is connected to the internet.
     * This implementation always returns false as the internet connectivity status
     * is unknown or cannot be determined for the current operating system.
     *
     * @return false indicating unknown internet connectivity status.
     */
    @Override
    public boolean checkInternet() {
        return false;
    }
}
