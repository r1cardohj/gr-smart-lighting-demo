package org.grsl.schema.generics;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class idOnlyRequest {
    @Pattern(regexp = "[0-9]*", message = "id must be number")
    private String id;

    public long getLongId() {
        return Long.parseLong(id);
    }
}
