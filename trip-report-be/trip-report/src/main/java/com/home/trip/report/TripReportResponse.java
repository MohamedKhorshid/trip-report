package com.home.trip.report;

/**
 * TripReportResponse
 */
public class TripReportResponse {

    private Hits hits;

    @Override
    public String toString() {
        return "hits:\n" + hits;
    }

    /**
     * @return the hits
     */
    public Hits getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(Hits hits) {
        this.hits = hits;
    }
}