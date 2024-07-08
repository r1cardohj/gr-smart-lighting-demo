package org.grsl.schema.http;

public class Code400Response extends BaseHttpResponse {
    public Code400Response(String msg) {
        super(400, msg);
    }

    public Code400Response() {
        super(400, "Bad Request");
    }
}
