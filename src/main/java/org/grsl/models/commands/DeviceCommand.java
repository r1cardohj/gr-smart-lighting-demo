package org.grsl.models.commands;

import lombok.Data;
import org.grsl.models.CommandType;

@Data
public class DeviceCommand {
    private Long deviceId;
    private CommandType commandType;
}
