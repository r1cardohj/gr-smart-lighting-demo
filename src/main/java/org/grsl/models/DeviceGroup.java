package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class DeviceGroup implements BaseModel{
    @Id
    private long id;
    private String name;
    private String description;
}
