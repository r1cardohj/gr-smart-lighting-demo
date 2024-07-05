package org.grsl.schema.http;

import lombok.Data;

@Data
public class BaseHttpResponse {
    private int code;
    private String msg;

    public BaseHttpResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
