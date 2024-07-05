package org.grsl.schema.device;

import org.grsl.models.Device;
import org.grsl.schema.http.BaseHttpResponse;


public class DeviceResponse extends BaseHttpResponse {
    private Device device;

    public DeviceResponse(Device device) {
        super(200, "successfully");
        this.device = device;
        if (this.device == null) {
            this.setCode(404);
            this.setMsg("device is not found");
        }
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
