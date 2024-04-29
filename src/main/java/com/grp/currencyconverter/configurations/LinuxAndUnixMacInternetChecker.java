/**
 * The LinuxAndUnixMacInternetChecker class is an implementation of the InternetChecker interface
 * specifically designed for Linux, Unix, and macOS operating systems. It checks for internet connectivity
 * by sending a ping request to google.com and analyzing the response.
 */
package com.grp.currencyconverter.configurations;

import java.io.IOException;

public class LinuxAndUnixMacInternetChecker implements InternetChecker {

    /**
     * Checks whether the device is connected to the internet by sending a ping request to google.com.
     *
     * @return true if the device is connected to the internet, false otherwise.
     */
    @Override
    public boolean checkInternet() {
        try {
            // Execute a ping command to google.com
            ProcessBuilder processBuilder = new ProcessBuilder("ping -c 1 google.com");
            Process process = processBuilder.start();
            // Wait for the process to complete and get the exit code
            int exitCode = process.waitFor();
            // If the exit code is 0, it means the ping request was successful, indicating internet connectivity
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            // An exception occurred during the process, indicating no internet connectivity
            return false;
        }
    }
}
