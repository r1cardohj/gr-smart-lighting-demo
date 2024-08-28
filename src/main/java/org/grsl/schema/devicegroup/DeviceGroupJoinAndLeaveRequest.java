package org.grsl.schema.devicegroup;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;


@Data
public class DeviceGroupJoinAndLeaveRequest {
    @Pattern(regexp = "[0-9]+", message = "deviceGroupId must a number.")
    @NotBlank
    private String deviceGroupId;
    @Length(min = 1, message = "At least hava one deviceId in deviceIdSet")
    private List<Long> deviceIdSet;
}
