package com.home.trip.report;

/**
 * Hit
 */
public class Hit {

    private Source _source;

    /**
     * @return the _source
     */
    public Source get_source() {
        return _source;
    }

    /**
     * @param _source the _source to set
     */
    public void set_source(Source _source) {
        this._source = _source;
    }

    @Override
    public String toString() {
        return "\nsource: " + _source;
    }
}