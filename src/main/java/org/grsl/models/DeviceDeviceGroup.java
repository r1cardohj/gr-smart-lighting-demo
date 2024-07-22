package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

/*
建立设备和设备组中的多对多关系的一个中间对象
 */
@Data
public class DeviceDeviceGroup {
    @Id
    private long id;
    private long deviceId;
    private long deviceGroupId;
}
