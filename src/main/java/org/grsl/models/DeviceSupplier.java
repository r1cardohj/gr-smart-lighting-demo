package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class DeviceSupplier implements BaseModel{
    @Id
    private long id;
    private String name;
    private List<Device> devices;
}
