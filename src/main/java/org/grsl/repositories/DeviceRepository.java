package org.grsl.repositories;

import org.grsl.models.Device;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    @Query("SELECT * FROM device LIMIT :page,:perPage")
    public List<Device> findAllDeviceByPage(int page, int perPage);


    /*
    自定义异常
     */
    class DeviceNotFoundException extends RuntimeException {
        @Override
        public String toString() {
            return "device can't not found";
        }
    }
    class DeviceExistException extends RuntimeException {
        @Override
        public String toString() {
            return "device existed already.";
        }
    }
}
