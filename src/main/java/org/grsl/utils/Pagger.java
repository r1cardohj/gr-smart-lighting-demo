package org.grsl.utils;


public class Pagger {
    private Integer limit;
    private Integer offset;
    static Integer DEFAULT_PAGE = 1;
    static Integer DEFAULT_PER_PAGE = 10;

    public Pagger(Integer page, Integer perPage) {
        Integer safePage = toSafePage(page);
        Integer safePerPage = toSafePerPage(perPage);
        this.limit = safePerPage;
        this.offset = safePerPage * (safePage - 1);
    }

    private Integer toSafePage(Integer page) {
        if (page != null && page > 0)
            return page;
        return DEFAULT_PAGE;
    }

    private Integer toSafePerPage(Integer perPage) {
        if (perPage != null && perPage > 0)
            return perPage;
        return DEFAULT_PER_PAGE;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }
}
