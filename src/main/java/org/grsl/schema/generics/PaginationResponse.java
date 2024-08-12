package org.grsl.schema.generics;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.utils.Pagination;


public class PaginationResponse<T> extends CommonDataResponse {

    private Pagination pagination;

    public PaginationResponse(T data, Pagination pagination){
        super(data);
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
