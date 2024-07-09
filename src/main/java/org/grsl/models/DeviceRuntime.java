package org.grsl.models;

import lombok.Data;

@Data
public class DeviceRuntime {
    private String deviceId;
    private Float brightness;
    private DeviceRuntimeStatus status;
}
