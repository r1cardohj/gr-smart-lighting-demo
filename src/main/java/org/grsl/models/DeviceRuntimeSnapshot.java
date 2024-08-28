package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class DeviceRuntimeSnapshot {
    @Id
    private long id;
    private Long deviceId;
    private Integer brightness;
    @Pattern(regexp = "[0-1]", message = "status is invalid, 0 is off, 1 is on.")
    private Integer status;
    private Date createdDt;
}
