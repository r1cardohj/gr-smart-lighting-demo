package org.grsl.repositories;

import org.grsl.models.DeviceDeviceGroup;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDeviceGroupRespository extends CrudRepository<DeviceDeviceGroup, Long> {
    @Query("SELECT * FROM device_device_group WHERE device_id = :deviceId")
    public List<DeviceDeviceGroup> findDeviceDeviceGroupsByDeviceId(long deviceId);

    @Query("SELECT * FROM device_device_group WHERE device_group = :deviceGroupId")
    public List<DeviceDeviceGroup> findDeviceDeviceGroupsByDeviceGroupId(long deviceGroupId);

    @Query("SELECT * FROM device_device_group WHERE device_group = :deviceGroupId AND device_id = :deviceId")
    public DeviceDeviceGroup findDeviceDeviceGroupByGrpAndDevice(long deviceId, long deviceGroupId);

    public class DeviceInGrpAlreadyException extends RuntimeException {}

    public class DeivceNotInGrpExcpetion extends RuntimeException {}
}
