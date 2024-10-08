package org.grsl.schema.device;

import lombok.Data;
import org.grsl.models.Device;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class DeviceCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String deviceCode;
    private String specifications;
    private String position;
    private String lightingType;
    private String serialNumber;
    private String chargeBy;
    @Past
    private Date exFactoryDate;
    @Future
    private Date expiredDate;

    public Device toDevice() {
        Device device = new Device();
        device.setName(name);
        device.setDeviceCode(deviceCode);
        device.setSpecifications(specifications);
        device.setPosition(position);
        device.setIsOnline(false);
        device.setLightingType(lightingType);
        device.setSerialNumber(serialNumber);
        device.setChargeBy(chargeBy);
        device.setExFactoryDate(exFactoryDate);
        device.setExpiredDate(expiredDate);
        return device;
    }
}
