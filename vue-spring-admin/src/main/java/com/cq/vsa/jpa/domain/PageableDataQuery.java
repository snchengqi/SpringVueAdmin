package com.cq.vsa.jpa.domain;

/**
 * Pageable Data Query Interface
 * @Author: chengqi
 * @Date: Created in 14:27 2019/4/23
 */
public interface PageableDataQuery extends DataQuery {

    Integer DEFAULT_PAGE = 0;

    Integer DEFAULT_SIZE = 10;

    /**
     * construct pageable data query object using default arguments
     * @return
     */
    static PageableDataQuery of() {
        return of(DEFAULT_PAGE, DEFAULT_SIZE);
    }

    /**
     * construct pageable data query object
     * @param page
     * @param size
     * @return
     */
    static PageableDataQuery of(int page, int size) {
        return new DefaultPageableDataQuery(page, size);
    }

    /**
     * Get page number
     * @return
     */
    Integer getPage();

    /**
     * Get page size
     * @return
     */
    Integer getSize();

    /**
     * default pageable data query object
     */
    class DefaultPageableDataQuery implements PageableDataQuery {

        private Integer page = DEFAULT_PAGE;
        private Integer size = DEFAULT_SIZE;

        public DefaultPageableDataQuery() {}

        public DefaultPageableDataQuery(Integer page, Integer size) {
            this.page = page;
            this.size = size;
        }

        @Override
        public Integer getPage() {
            return page;
        }

        @Override
        public Integer getSize() {
            return size;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }
}
