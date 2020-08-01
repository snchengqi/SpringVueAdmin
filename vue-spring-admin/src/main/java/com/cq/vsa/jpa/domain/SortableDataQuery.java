package com.cq.vsa.jpa.domain;

/**
 * Sortable Data Query Interface
 * @Author: chengqi
 * @Date: Created in 14:06 2019/4/23
 */
public interface SortableDataQuery extends DataQuery {

    String DEFAULT_DIRECTION = "ASC";

    /**
     * construct sortable data query object using default direction
     * @param order
     * @return
     */
    static SortableDataQuery of(String order) {
        return of(order, DEFAULT_DIRECTION);
    }

    /**
     * construct sortable data query object
     * @param order
     * @param direction
     * @return
     */
    static SortableDataQuery of(String order, String direction) {
        return new DefaultSortableDataQuery(order, direction);
    }

    /**
     * Get order field
     * @return
     */
    String getOrder();

    /**
     * Get sort direction
     * @return
     */
    String getDirection();

    /**
     * default sortable data query object
     * @Author: chengqi
     * @Date: Created in 14:30 2019/4/23
     */
    class DefaultSortableDataQuery implements SortableDataQuery {

        private String order;
        private String direction = DEFAULT_DIRECTION;

        public DefaultSortableDataQuery() {}

        public DefaultSortableDataQuery(String order, String direction) {
            this.order = order;
            this.direction = direction;
        }

        @Override
        public String getOrder() {
            return order;
        }

        @Override
        public String getDirection() {
            return direction;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
