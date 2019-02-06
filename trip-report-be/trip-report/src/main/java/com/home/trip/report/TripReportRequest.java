package com.home.trip.report;

/**
 * TripReportRequest
 */
public class TripReportRequest {

    private Query query;

    /**
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    public static class Query {
        private Range range;

        /**
         * @return the range
         */
        public Range getRange() {
            return range;
        }

        /**
         * @param range the range to set
         */
        public void setRange(Range range) {
            this.range = range;
        }

        public static class Range {
            private RangeAttr start;

            /**
             * @return the start
             */
            public RangeAttr getStart() {
                return start;
            }

            /**
             * @param start the start to set
             */
            public void setStart(RangeAttr start) {
                this.start = start;
            }

            public static class RangeAttr {
                private long gte;
                private long lte;

                /**
                 * @return the gte
                 */
                public long getGte() {
                    return gte;
                }

                /**
                 * @param gte the gte to set
                 */
                public void setGte(long gte) {
                    this.gte = gte;
                }

                /**
                 * @param lte the lte to set
                 */
                public void setLte(long lte) {
                    this.lte = lte;
                }

                /**
                 * @return the lte
                 */
                public long getLte() {
                    return lte;
                }
            }
        }
    }
}