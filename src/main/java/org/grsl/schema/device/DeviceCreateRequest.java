package org.grsl.schema.device;

import lombok.Data;
import org.grsl.models.Device;

import java.util.Date;

@Data
public class DeviceCreateRequest {
    private String name;
    private String deviceCode;
    private String specifications;
    private String position;
    private String lightingType;
    private String serialNumber;
    private String chargeBy;
    private Date exFactoryDate;
    private Date expiredDate;

    public Device toDevice() {
        Device device = new Device();
        device.setName(name);
        device.setDeviceCode(deviceCode);
        device.setSpecifications(specifications);
        device.setPosition(position);
        device.setLightingType(lightingType);
        device.setSerialNumber(serialNumber);
        device.setChargeBy(chargeBy);
        device.setExFactoryDate(exFactoryDate);
        device.setExpiredDate(expiredDate);
        return device;
    }
}
