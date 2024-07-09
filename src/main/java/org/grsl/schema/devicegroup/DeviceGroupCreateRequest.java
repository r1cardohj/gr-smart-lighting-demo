package org.grsl.schema.devicegroup;

import lombok.Data;
import org.grsl.models.DeviceGroup;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceGroupCreateRequest {
    @NotBlank
    private String name;
    private String description;

    public DeviceGroup toDeviceGroup() {
        DeviceGroup dg = new DeviceGroup();
        dg.setName(this.name);
        dg.setDescription(this.description);
        return dg;
    }
}
