package org.grsl.schema.device;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeviceIDOnlyRequest {
    @NotNull
    private long id;
}
