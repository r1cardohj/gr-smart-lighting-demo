package org.grsl.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ReportDetail {
    @Id
    private long id;
    private long reportId;
    private long deviceId;
    private double totalTime;
    private double avgBrightness;
}
