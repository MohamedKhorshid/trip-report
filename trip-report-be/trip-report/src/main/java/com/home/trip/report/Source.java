package com.home.trip.report;

/**
 * Source
 */
public class Source {

    private long start;
    private long end;

    /**
     * @return the start
     */
    public long getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(long start) {
        this.start = start;
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

    @Override
    public String toString() {
        return "start: " + start + "\tend: " + end;
    }
    
}