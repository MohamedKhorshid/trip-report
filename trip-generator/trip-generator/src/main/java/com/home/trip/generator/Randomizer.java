package com.home.trip.generator;

/**
 * Randomizer
 */
public class Randomizer {

    public static String randomizeName() {
        return "random Name"; 
    }

    public static long randomizeNumber(long startRange, long endRange) {
        double random = Math.random();
        return startRange + (long)(random * (endRange - startRange));
    }
}