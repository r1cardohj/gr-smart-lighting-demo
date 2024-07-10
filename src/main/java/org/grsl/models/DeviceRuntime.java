package org.grsl.models;

import lombok.Data;

@Data
public class DeviceRuntime {
    private String deviceId;
    private Float brightness;
    private Boolean status;
}
