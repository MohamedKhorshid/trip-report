package com.home.trip.report;

import java.util.List;

/**
 * Hits
 */
public class Hits {

    private List<Hit> hits;
    private int total;

    /**
     * @return the hits
     */
    public List<Hit> getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "total " + total + "\nhits: " + hits;
    }
}