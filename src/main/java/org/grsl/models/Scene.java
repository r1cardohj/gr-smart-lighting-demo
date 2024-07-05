package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Scene implements BaseModel{
    @Id
    private long id;
    private String name;
}
