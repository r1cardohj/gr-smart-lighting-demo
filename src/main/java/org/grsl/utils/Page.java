package org.grsl.utils;

import lombok.Data;

@Data
public class Page {
    private Integer page;
    private Integer perPage;
    private Integer limit;
    private Integer offset;
}
