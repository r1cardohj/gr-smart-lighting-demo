package org.grsl.services;

import org.grsl.models.Device;
import org.grsl.models.DeviceDeviceGroup;
import org.grsl.models.DeviceGroup;
import org.grsl.repositories.DeviceDeviceGroupRespository;
import org.grsl.repositories.DeviceGroupRespository;
import org.grsl.repositories.DeviceRepository;
import org.grsl.utils.Page;
import org.grsl.utils.Paginator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceGroupManageService {

    DeviceGroupRespository deviceGroupRespository;
    DeviceDeviceGroupRespository deviceDeviceGroupRespository;
    DeviceRepository deviceRepository;

    public DeviceGroupManageService(DeviceGroupRespository deviceGroupRespository,
                                    DeviceDeviceGroupRespository deviceDeviceGroupRespository,
                                    DeviceRepository deviceRepository) {
        this.deviceGroupRespository = deviceGroupRespository;
        this.deviceDeviceGroupRespository = deviceDeviceGroupRespository;
        this.deviceRepository = deviceRepository;
    }

    public DeviceGroup getDeviceGroupById(long id) {
        return this.deviceGroupRespository.findById(id)
                .orElseThrow(DeviceGroupRespository.DeviceGroupNotFoundException::new);
    }

    public Long getDeviceGroupTotalCount() {
        return this.deviceGroupRespository.count();
    }

    public List<DeviceGroup> getDeviceGroupByPage(Page page) {
        return this.deviceGroupRespository.findAllDeviceGroupByPage(page.getLimit(), page.getOffset());
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

    public Boolean isDeviceInGroup(long deviceId, long deviceGroupId) {
        return this.deviceDeviceGroupRespository.findDeviceDeviceGroupByGrpAndDevice(deviceId, deviceGroupId) != null;
    }

    @Transactional
    public void joinDeviceGroup(long deviceGroupId, List<Long> deviceIdSet) {
        List<DeviceDeviceGroup> DeviceDeviceGroups = new ArrayList<>();

        if (!this.deviceGroupRespository.existsById(deviceGroupId))
            throw new DeviceGroupRespository.DeviceGroupNotFoundException();

        for (Long deviceId : deviceIdSet) {
            if (!this.deviceRepository.existsById(deviceId))
                throw new DeviceRepository.DeviceNotFoundException();

            if (this.isDeviceInGroup(deviceId, deviceGroupId))
                throw new DeviceDeviceGroupRespository.DeviceInGrpAlreadyException();

            DeviceDeviceGroup deviceDeviceGroup = new DeviceDeviceGroup();
            deviceDeviceGroup.setDeviceId(deviceId);
            deviceDeviceGroup.setDeviceGroupId(deviceGroupId);
            DeviceDeviceGroups.add(deviceDeviceGroup);
        }
        this.deviceDeviceGroupRespository.saveAll(DeviceDeviceGroups);
    }

    @Transactional
    public void leaveDeviceGroup(long deviceGroupId, List<Long> deviceIdSet) {
        if (!this.deviceGroupRespository.existsById(deviceGroupId))
            throw new DeviceGroupRespository.DeviceGroupNotFoundException();

        for (Long deviceId : deviceIdSet) {
            if (!this.deviceRepository.existsById(deviceId))
                throw new DeviceRepository.DeviceNotFoundException();

            if (!this.isDeviceInGroup(deviceId, deviceGroupId))
                throw new DeviceDeviceGroupRespository.DeivceNotInGrpExcpetion();

            DeviceDeviceGroup deviceDeviceGroup = this.deviceDeviceGroupRespository
                    .findDeviceDeviceGroupByGrpAndDevice(deviceId, deviceGroupId);

            this.deviceDeviceGroupRespository.delete(deviceDeviceGroup);
        }


    }

    public Iterable<DeviceGroup> getDeviceGroupByDeviceId(long deviceId) {
        List<DeviceDeviceGroup> deviceDeviceGroups = this.deviceDeviceGroupRespository
                                                        .findDeviceDeviceGroupsByDeviceId(deviceId);

        if (deviceDeviceGroups.isEmpty())
            return new ArrayList<>();

        List<Long> deviceGroupIds = deviceDeviceGroups.stream()
                                    .map(DeviceDeviceGroup::getDeviceGroupId)
                                    .collect(Collectors.toList());

        return this.deviceGroupRespository.findAllById(deviceGroupIds);
    }

    public Iterable<Device> getDeviceByDeviceGroupId(long deviceGroupId) {
        List<DeviceDeviceGroup> deviceDeviceGroups = this.deviceDeviceGroupRespository
                                                        .findDeviceDeviceGroupsByDeviceGroupId(deviceGroupId);

        if  (deviceDeviceGroups.isEmpty())
            return new ArrayList<>();

        List<Long> deviceIds = deviceDeviceGroups.stream()
                                .map(DeviceDeviceGroup::getDeviceId)
                                .collect(Collectors.toList());

        return this.deviceRepository.findAllById(deviceIds);
    }
}
