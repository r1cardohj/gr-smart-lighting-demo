package org.grsl.schema.device;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class DeviceIDOnlyRequest {
    @Pattern(regexp = "[0-9]*", message = "id must be number")
    private String id;

    public long getLongId() {
        return Long.parseLong(id);
    }
}
