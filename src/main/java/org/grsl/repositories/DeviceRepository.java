package org.grsl.repositories;

import org.grsl.models.Device;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    @Query("SELECT * FROM device LIMIT :offset,:limit")
    public List<Device> findAllDeviceByPage(int limit, int offset);


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
