package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class DeviceLog {
    @Id
    private long id;
    private long deviceId;
    private String operation;
    private Date createdDt;
    private String remark;

    public static DeviceLog on(long deviceId) {
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setDeviceId(deviceId);
        deviceLog.setOperation("on");
        deviceLog.setCreatedDt(new Date());
        return deviceLog;
    }
    public static DeviceLog off(long deviceId) {
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setDeviceId(deviceId);
        deviceLog.setOperation("off");
        deviceLog.setCreatedDt(new Date());
        return deviceLog;
    }

    public static DeviceLog chgBrightness(long deviceId, int brightness) {
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setDeviceId(deviceId);
        deviceLog.setOperation("chgBrightness");
        deviceLog.setCreatedDt(new Date());
        deviceLog.setRemark(Integer.toString(brightness));
        return deviceLog;
    }
}
