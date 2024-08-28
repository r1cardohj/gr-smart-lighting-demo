package org.grsl.repositories;

import org.grsl.models.DeviceGroup;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceGroupRepository extends CrudRepository<DeviceGroup, Long> {
    @Query("SELECT * FROM device_group LIMIT :offset,:limit")
    public List<DeviceGroup> findAllDeviceGroupByPage(int limit, int offset);

    @Query("SELECT * FROM device_group WHERE name=:name")
    public DeviceGroup findDeviceByName(String name);

    @Query("SELECT * FROM device_group WHERE name=:name AND id!=:id")
    public DeviceGroup findSameNameButOtherDeviceGroup(long id, String name);


    class DeviceGroupNotFoundException extends RuntimeException {
        @Override
        public String toString() {
            return "device group can not found";
        }
    }

    class DeviceGroupExistException extends RuntimeException {
        @Override
        public String toString() {
            return "device group is exist yet";
        }
    }

    class DeviceGroupNameRepeatException extends RuntimeException {
        @Override
        public String toString() {
            return "device name is exist.";
        }
    }

}
