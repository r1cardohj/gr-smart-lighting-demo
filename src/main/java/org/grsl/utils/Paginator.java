package org.grsl.utils;


public class Paginator {
    private final Integer limit;
    private final Integer offset;

    private final Integer page;
    private final Integer perPage;
    static Integer DEFAULT_PAGE = 1;
    static Integer DEFAULT_PER_PAGE = 10;

    public Paginator(Integer page, Integer perPage) {
        this.page = page;
        this.perPage = perPage;
        Integer safePage = this.getSafePage();
        Integer safePerPage = this.getSafePerPage();
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

    public Integer getSafePage() {
        return this.toSafePage(this.page);
    }

    public Integer getSafePerPage() {
        return this.toSafePerPage(this.perPage);
    }

    public Pagination pagination(Long totalCount) {
        Pagination pagination = new Pagination();

        pagination.setTotalCount(totalCount);
        pagination.setPage(this.getSafePage());
        pagination.setPageSize(this.getSafePerPage());
        return pagination;
    }

    public Page page() {
        Page page = new Page();
        page.setPage(this.getSafePage());
        page.setPerPage(this.getSafePerPage());
        page.setLimit(this.limit);
        page.setOffset(this.offset);
        return page;
    }
}
