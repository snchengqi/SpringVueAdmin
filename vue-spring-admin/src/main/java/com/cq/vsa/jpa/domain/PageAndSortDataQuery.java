package com.cq.vsa.jpa.domain;

/**
 * pageable and sortable data query interface
 * @Author: chengqi
 * @Date: Created in 15:04 2019/4/23
 */
public interface PageAndSortDataQuery extends PageableDataQuery, SortableDataQuery {

    /**
     * default pageable and sortable data query object
     */
    class DefaultPageAndSortDataQuery extends DefaultPageableDataQuery implements PageAndSortDataQuery {

        private String order;
        private String direction = DEFAULT_DIRECTION;

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
