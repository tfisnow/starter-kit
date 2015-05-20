package com.generic.comparator;

import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;

public class Comparator {

    public static boolean compareExactText(String actual, String expected) {
       
    	Logg.createLogger().info(Utilities.getCurrentThreadId() + "Actual Value=" + actual + " Expected Value="
                + expected);
    	Logg.createLogger().info(Utilities.getCurrentThreadId() + "Result of exact comparison is "
                + actual.equals(expected));
        return actual.equals(expected);
    }

    public static boolean comparePartialText(String actual, String expected) {
    	Logg.createLogger().info(Utilities.getCurrentThreadId() + "Actual Value=" + actual + " Expected Value="
                + expected);
    	Logg.createLogger().info(Utilities.getCurrentThreadId() + "Result of partial comparison is "
                + actual.contains(expected));
        return actual.contains(expected);
    }
}
