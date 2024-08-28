package org.grsl.repositories;

import org.grsl.models.DeviceLog;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceLogRepository extends CrudRepository<DeviceLog, Long> {
    @Query("SELECT * FROM device_log WHERE device_id=:deviceId ORDER BY created_dt DESC")
    public List<DeviceLog> findDeviceLogsByDeviceId(long deviceId);

    @Query("SELECT * FROM device_log ORDER BY created_dt DESC")
    public List<DeviceLog> findAllDeviceLogs();
}
