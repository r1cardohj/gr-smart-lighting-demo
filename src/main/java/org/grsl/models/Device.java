package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Device implements BaseModel{
    @Id
    private long id;
    private String name;
    private String deviceCode;
    private String specifications; //规格型号
    private String position;
    //private List<Scene> scenes; //场景
    private String lightingType; //照明类型
    private Boolean isOnline;
    private String serialNumber; //序列号
    private String chargeBy; //负责人
    private Date exFactoryDate; //出厂日期
    private Date expiredDate; //报废日期
}
