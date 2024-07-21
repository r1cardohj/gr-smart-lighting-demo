package org.grsl.schema.deviceruntime;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class DeviceIdRequest {
    @Pattern(regexp = "[0-9]+", message = "deviceId is necessary,and must be number.")
    @NotBlank
    private String deviceId;

    public Long getLongDeviceId() {
        return Long.parseLong(deviceId);
    }
}
