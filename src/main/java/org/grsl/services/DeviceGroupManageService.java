package org.grsl.services;

import org.grsl.models.DeviceGroup;
import org.grsl.repositories.DeviceGroupRespository;
import org.grsl.repositories.DeviceRepository;
import org.grsl.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceGroupManageService {

    DeviceGroupRespository deviceGroupRespository;

    public DeviceGroupManageService(DeviceGroupRespository deviceGroupRespository) {
        this.deviceGroupRespository = deviceGroupRespository;
    }

    public DeviceGroup getDeviceGroupById(long id) {
        return this.deviceGroupRespository.findById(id)
                .orElseThrow(DeviceGroupRespository.DeviceGroupNotFoundException::new);
    }

    public List<DeviceGroup> getDeviceGroupByPage(Integer page, Integer perPage) {
        Pager pager = new Pager(page, perPage);
        return this.deviceGroupRespository.findAllDeviceGroupByPage(pager.getLimit(), pager.getOffset());
    }

    public void createDeiveGroup(DeviceGroup deviceGroup) {
        DeviceGroup deviceGroupExist = this.deviceGroupRespository.findDeviceByName(deviceGroup.getName());
        if (deviceGroupExist != null)
            throw new DeviceGroupRespository.DeviceGroupNameRepeatException();
        this.deviceGroupRespository.save(deviceGroup);
    }

    public void updateDeviceGroup(DeviceGroup deviceGroup) {
        DeviceGroup baseDeviceGroup = this.getDeviceGroupById(deviceGroup.getId());
        DeviceGroup sameNameDevice = deviceGroupRespository.findSameNameButOtherDeviceGroup(deviceGroup.getId(),
                deviceGroup.getName());
        if (sameNameDevice != null)
            throw new DeviceGroupRespository.DeviceGroupNameRepeatException();
        DeviceGroup merged = this.getMergedDevice(baseDeviceGroup, deviceGroup);
        if (baseDeviceGroup.equals(merged))
            return;
        this.deviceGroupRespository.save(merged);
    }

    public void deleteDeviceGroup(long id) {
        if (!this.deviceGroupRespository.existsById(id))
            throw new DeviceRepository.DeviceNotFoundException();
        this.deviceGroupRespository.deleteById(id);
    }

    public DeviceGroup getMergedDevice(DeviceGroup d1, DeviceGroup d2) {
        DeviceGroup mergedDeviceGroup = new DeviceGroup();
        mergedDeviceGroup.setId(d2.getId());
        mergedDeviceGroup.setName(Optional.ofNullable(d2.getName()).orElse(d1.getName()));
        mergedDeviceGroup.setDescription(Optional.ofNullable(d2.getDescription()).orElse(d1.getDescription()));
        return mergedDeviceGroup;
    }
}
