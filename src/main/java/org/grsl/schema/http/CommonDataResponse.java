package org.grsl.schema.http;


public class CommonDataResponse<T> extends Code200Response{
    private T data;

    public CommonDataResponse(T data) {
        super();
        this.data = data;
        if (this.data == null) {
            this.setCode(404);
            this.setMsg("data is not found");
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
