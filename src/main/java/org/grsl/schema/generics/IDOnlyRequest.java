package org.grsl.schema.generics;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class IDOnlyRequest {
    @Pattern(regexp = "[0-9]+", message = "id must be number")
    @NotBlank
    private String id;

    public long getLongId() {
        return Long.parseLong(id);
    }
}
