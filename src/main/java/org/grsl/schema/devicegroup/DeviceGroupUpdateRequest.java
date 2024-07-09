package org.grsl.schema.devicegroup;

import lombok.Data;
import org.grsl.models.Device;
import org.grsl.models.DeviceGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DeviceGroupUpdateRequest {
    @Pattern(regexp = "[0-9]*", message = "id must be number")
    private String id;
    private String name;
    private String description;

    public DeviceGroup toDeviceGroup() {
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.setId(Long.parseLong(this.id));
        deviceGroup.setName(this.name);
        deviceGroup.setDescription(this.getDescription());
        return deviceGroup;
    }
}
