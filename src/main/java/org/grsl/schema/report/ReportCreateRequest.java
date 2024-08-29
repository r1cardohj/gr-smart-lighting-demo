package org.grsl.schema.report;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
public class ReportCreateRequest {
    @Past
    private Date beginDt;
    @Past
    private Date endDt;
    @NotEmpty
    private List<Long> deviceIds;
}
