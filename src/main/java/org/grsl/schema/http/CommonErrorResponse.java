package org.grsl.schema.http;


public class CommonErrorResponse<T> extends Code400Response{
    public T errors;

    public CommonErrorResponse(T errors) {
        super();
        this.errors = errors;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }
}
