package org.example.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Device {
    private long id;
    private String name;
    private String deviceCode;
    private String specifications; //规格型号
    private String position;
    private List<Scene> scenes; //场景
    private LightingType lightingType; //照明类型
    private Boolean isOnline;
    private String serialNumber;
    private String ChargeBy; //负责人
    private Date exFactoryDate; //出厂日期
    private Date expiredDate; //报废日期
}
