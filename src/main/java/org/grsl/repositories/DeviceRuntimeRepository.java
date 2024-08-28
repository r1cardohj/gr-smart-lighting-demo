package org.grsl.repositories;

import org.grsl.models.DeviceRuntime;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRuntimeRepository extends CrudRepository<DeviceRuntime, Long> {

    @Query("SELECT * FROM device_runtime WHERE device_id = :deviceId")
    public DeviceRuntime getDeviceRuntimeByDeviceId(long deviceId);

    @Query("SELECT count(*) FROM device_runtime WHERE status = 1")
    public int getStatusOnDeviceCount();

    class DeviceRuntimeNotFoundException extends RuntimeException {
        @Override
        public String toString() {
            return "The runtime of device is not found.";
        }
    }

    class DeviceRuntimeIsExistException extends RuntimeException {
        @Override
        public String toString() {
            return "The runtime of device is exist.";
        }
    }
}
