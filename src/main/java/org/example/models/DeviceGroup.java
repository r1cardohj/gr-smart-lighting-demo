package org.example.models;

import lombok.Data;

import java.util.List;

@Data
public class DeviceGroup {
    private long id;
    private String name;
    private List<Device> devices;
}
