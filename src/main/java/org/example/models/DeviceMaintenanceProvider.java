package org.example.models;

import lombok.Data;

import java.util.List;

@Data
public class DeviceMaintenanceProvider {
    private long id;
    private String providerCode;
    private String providerName;
    private String contractPersonName;
    private String contractPersonPhone;
}
