package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeviceMaintenanceProvider implements BaseModel{
    @Id
    private long id;
    private String providerCode;
    private String providerName;
    private String contractPersonName;
    private String contractPersonPhone;
}
