package org.grsl.schema.device;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceIDOnlyRequest {
    @NotBlank
    private long id;
}
