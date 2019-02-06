package com.home.trip.generator;

/**
 * TimeConverter
 */
public class TimeConverter {

    public static long minutesToTime(long minutes) {
        return  minutes * 60 * 1000;
    }

    public static long daysToTime(long days) {
        return  days * 24 * 60 * 60 * 1000;
    }
}