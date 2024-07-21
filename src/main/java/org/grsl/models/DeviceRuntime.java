package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Pattern;

@Data
public class DeviceRuntime {
    @Id
    private Long id;
    private Long deviceId;
    private Integer brightness;
    @Pattern(regexp = "[0-1]", message = "status is invalid, 0 is off, 1 is on.")
    private Integer status;
}
