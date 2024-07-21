package org.grsl.schema.deviceruntime;

import lombok.Data;
import org.grsl.schema.generics.IDOnlyRequest;

import javax.validation.constraints.*;

@Data
public class AdjustBrightnessRequest {
    @Pattern(regexp = "[0-9]+", message = "id must be number")
    @NotBlank
    private String deviceId;
    @Max(100)
    @Min(0)
    @NotEmpty
    private Integer brightness;

    public Long getLongDeviceId() {
        return Long.parseLong(deviceId);
    }
}
