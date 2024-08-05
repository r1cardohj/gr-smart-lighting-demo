package org.grsl.schema.devicegroup;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class DeviceGroupJoinOrLeaveRequest {
    @NotBlank
    private String groupId;
    @NotEmpty
    private List<Long> deviceIdSet;

    public Long getGroupIdLong() {
        return Long.parseLong(this.groupId);
    }
}
