/**
 * The WindowsInternetChecker class is an implementation of the InternetChecker interface
 * specifically designed for Windows operating systems. It checks for internet connectivity
 * by establishing a connection to a known website (https://www.google.com) and analyzing the response.
 */
package com.grp.currencyconverter.configurations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WindowsInternetChecker implements InternetChecker {

    /**
     * Checks whether the device is connected to the internet by establishing a connection to google.com.
     *
     * @return true if the device is connected to the internet, false otherwise.
     */
    @Override
    public boolean checkInternet() {
        try {
            // Create a URI for the www.google.com website
            URI uri = new URI("https://www.google.com");
            // Open a connection to the URI
            HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
            // Connect to the URL
            urlConnection.connect();
            // Check the response code to determine internet connectivity
            return urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            // An exception occurred during the connection, indicating no internet connectivity
            System.out.println(e.getMessage());
            return false;
        } catch (URISyntaxException e) {
            // An exception occurred while constructing the URI
            throw new RuntimeException(e);
        }
    }
}
