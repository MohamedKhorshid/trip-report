package com.home.trip.generator;

import java.util.Calendar;

/**
 * TripRequest
 */
public class TripRequest {

    private static long START_DATE;

    static {
        Calendar start = Calendar.getInstance();

        start.set(Calendar.YEAR, 2019);
        start.set(Calendar.MONTH, 0);
        start.set(Calendar.DAY_OF_MONTH, 1);
        start.set(Calendar.HOUR, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        START_DATE = start.getTime().getTime();
    }

    private long start;
    private long end;
    private String captainName;
    private String customerName;

    /**
     * @return the start
     */
    public long getStart() {
        return start;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the captainName
     */
    public String getCaptainName() {
        return captainName;
    }

    /**
     * @param captainName the captainName to set
     */
    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    /**
     * @return the end
     */
    public long getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * @param start the start to set
     */
    public void setStart(long start) {
        this.start = start;
    }

    public static TripRequest newRandomTrip() {

        long tripStart = START_DATE + Randomizer.randomizeNumber(0,  TimeConverter.daysToTime(31));
        long duration = Randomizer.randomizeNumber(TimeConverter.minutesToTime(15),
                TimeConverter.minutesToTime(120));

        TripRequest trip = new TripRequest();

        trip.setCustomerName(Randomizer.randomizeName());
        trip.setCaptainName(Randomizer.randomizeName());
        trip.setStart(tripStart);
        trip.setEnd(tripStart + duration);

        return trip;
    }

    @Override
    public String toString() {
        return "Start: " + start + ", End: " + end + ", customer name: " + customerName + ", captain name: "
                + captainName;
    }

}