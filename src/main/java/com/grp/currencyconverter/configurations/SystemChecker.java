/**
 * The SystemChecker class provides a static method to determine the operating system and obtain an instance
 * of the appropriate InternetChecker implementation based on the detected operating system.
 */
package com.grp.currencyconverter.configurations;

public class SystemChecker {

    /**
     * Determines the operating system and returns an instance of the corresponding InternetChecker implementation.
     *
     * @return An instance of the InternetChecker implementation suitable for the detected operating system.
     */
    public static InternetChecker getOperatingSystem() {
        // Get the operating system name
        String os = System.getProperty("os.name").toLowerCase();
        // Check if the operating system is Windows
        if (os.contains("win")) {
            return new WindowsInternetChecker();
        }
        // Check if the operating system is a Unix-like system (Linux, Unix, or macOS)
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return new LinuxAndUnixMacInternetChecker();
        }
        // Check if the operating system is macOS
        else if (os.contains("mac")) {
            return new MacOsInternetChecker();
        }
        // If the operating system is unknown or unsupported, return an instance of UnknownInternetChecker
        else {
            return new UnknownInternetChecker();
        }
    }
}
