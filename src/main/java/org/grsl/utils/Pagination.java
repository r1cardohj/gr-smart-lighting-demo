package org.grsl.utils;

import lombok.Data;

@Data
public class Pagination {
    private Long totalCount;
    private Integer pageSize;
    private Integer page;
}
