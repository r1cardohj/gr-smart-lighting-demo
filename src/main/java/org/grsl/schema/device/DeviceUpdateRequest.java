package org.grsl.schema.device;

import lombok.Data;
import org.grsl.models.Device;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class DeviceUpdateRequest {

    @Pattern(regexp = "[0-9]*", message = "id must be number")
    @NotBlank
    private String id;
    private String name;
    private String deviceCode;
    private String specifications; //规格型号
    private String position;
    //private List<Scene> scenes; //场景
    private String lightingType; //照明类型
    private Boolean isOnline;
    private String serialNumber; //序列号
    private String chargeBy; //负责人
    @Past
    private Date exFactoryDate; //出厂日期
    @Future
    private Date expiredDate; //报废日期

    public Device toDevice() {
        Device device = new Device();
        device.setId(Long.parseLong(id));
        device.setName(name);
        device.setDeviceCode(deviceCode);
        device.setSpecifications(specifications);
        device.setIsOnline(isOnline);
        device.setPosition(position);
        device.setLightingType(lightingType);
        device.setSerialNumber(serialNumber);
        device.setChargeBy(chargeBy);
        device.setExFactoryDate(exFactoryDate);
        device.setExpiredDate(expiredDate);
        return device;
    }
}
