package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Report {
    @Id
    private long id;
    private String reportName;
    private Date createdDt;
    private int status; // 0: 待生成  1: 已完成
    private Date beginDt;
    private Date endDt;
}
