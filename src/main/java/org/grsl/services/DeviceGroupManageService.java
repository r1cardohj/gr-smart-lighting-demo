package org.grsl.services;

import org.grsl.models.Device;
import org.grsl.models.DeviceDeviceGroup;
import org.grsl.models.DeviceGroup;
import org.grsl.repositories.DeviceDeviceGroupRepository;
import org.grsl.repositories.DeviceGroupRepository;
import org.grsl.repositories.DeviceRepository;
import org.grsl.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceGroupManageService {

    DeviceGroupRepository deviceGroupRepository;
    DeviceDeviceGroupRepository deviceDeviceGroupRepository;
    DeviceRepository deviceRepository;

    public DeviceGroupManageService(DeviceGroupRepository deviceGroupRepository,
                                    DeviceDeviceGroupRepository deviceDeviceGroupRepository,
                                    DeviceRepository deviceRepository) {
        this.deviceGroupRepository = deviceGroupRepository;
        this.deviceDeviceGroupRepository = deviceDeviceGroupRepository;
        this.deviceRepository = deviceRepository;
    }

    public DeviceGroup getDeviceGroupById(long id) {
        return this.deviceGroupRepository.findById(id)
                .orElseThrow(DeviceGroupRepository.DeviceGroupNotFoundException::new);
    }

    public Long getDeviceGroupTotalCount() {
        return this.deviceGroupRepository.count();
    }

    public List<DeviceGroup> getDeviceGroupByPage(Page page) {
        return this.deviceGroupRepository.findAllDeviceGroupByPage(page.getLimit(), page.getOffset());
    }

    public void createDeviceGroup(DeviceGroup deviceGroup) {
        DeviceGroup deviceGroupExist = this.deviceGroupRepository.findDeviceByName(deviceGroup.getName());
        if (deviceGroupExist != null)
            throw new DeviceGroupRepository.DeviceGroupNameRepeatException();
        this.deviceGroupRepository.save(deviceGroup);
    }

    public void updateDeviceGroup(DeviceGroup deviceGroup) {
        DeviceGroup baseDeviceGroup = this.getDeviceGroupById(deviceGroup.getId());
        DeviceGroup sameNameDevice = deviceGroupRepository.findSameNameButOtherDeviceGroup(deviceGroup.getId(),
                deviceGroup.getName());
        if (sameNameDevice != null)
            throw new DeviceGroupRepository.DeviceGroupNameRepeatException();
        DeviceGroup merged = this.getMergedDevice(baseDeviceGroup, deviceGroup);
        if (baseDeviceGroup.equals(merged))
            return;
        this.deviceGroupRepository.save(merged);
    }

    public void deleteDeviceGroup(long id) {
        if (!this.deviceGroupRepository.existsById(id))
            throw new DeviceRepository.DeviceNotFoundException();
        this.deviceGroupRepository.deleteById(id);
    }

    public DeviceGroup getMergedDevice(DeviceGroup d1, DeviceGroup d2) {
        DeviceGroup mergedDeviceGroup = new DeviceGroup();
        mergedDeviceGroup.setId(d2.getId());
        mergedDeviceGroup.setName(Optional.ofNullable(d2.getName()).orElse(d1.getName()));
        mergedDeviceGroup.setDescription(Optional.ofNullable(d2.getDescription()).orElse(d1.getDescription()));
        return mergedDeviceGroup;
    }

    public Boolean isDeviceInGroup(long deviceId, long deviceGroupId) {
        return this.deviceDeviceGroupRepository.findDeviceDeviceGroupByGrpAndDevice(deviceId, deviceGroupId) != null;
    }

    @Transactional
    public void joinDeviceGroup(long deviceGroupId, List<Long> deviceIdSet) {
        List<DeviceDeviceGroup> DeviceDeviceGroups = new ArrayList<>();

        if (!this.deviceGroupRepository.existsById(deviceGroupId))
            throw new DeviceGroupRepository.DeviceGroupNotFoundException();

        for (Long deviceId : deviceIdSet) {
            if (!this.deviceRepository.existsById(deviceId))
                throw new DeviceRepository.DeviceNotFoundException();

            if (this.isDeviceInGroup(deviceId, deviceGroupId))
                throw new DeviceDeviceGroupRepository.DeviceInGrpAlreadyException();

            DeviceDeviceGroup deviceDeviceGroup = new DeviceDeviceGroup();
            deviceDeviceGroup.setDeviceId(deviceId);
            deviceDeviceGroup.setDeviceGroupId(deviceGroupId);
            DeviceDeviceGroups.add(deviceDeviceGroup);
        }
        this.deviceDeviceGroupRepository.saveAll(DeviceDeviceGroups);
    }

    @Transactional
    public void leaveDeviceGroup(long deviceGroupId, List<Long> deviceIdSet) {
        if (!this.deviceGroupRepository.existsById(deviceGroupId))
            throw new DeviceGroupRepository.DeviceGroupNotFoundException();

        for (Long deviceId : deviceIdSet) {
            if (!this.deviceRepository.existsById(deviceId))
                throw new DeviceRepository.DeviceNotFoundException();

            if (!this.isDeviceInGroup(deviceId, deviceGroupId))
                throw new DeviceDeviceGroupRepository.DeivceNotInGrpExcpetion();

            DeviceDeviceGroup deviceDeviceGroup = this.deviceDeviceGroupRepository
                    .findDeviceDeviceGroupByGrpAndDevice(deviceId, deviceGroupId);

            this.deviceDeviceGroupRepository.delete(deviceDeviceGroup);
        }


    }

    public Iterable<DeviceGroup> getDeviceGroupByDeviceId(long deviceId) {
        List<DeviceDeviceGroup> deviceDeviceGroups = this.deviceDeviceGroupRepository
                                                        .findDeviceDeviceGroupsByDeviceId(deviceId);

        if (deviceDeviceGroups.isEmpty())
            return new ArrayList<>();

        List<Long> deviceGroupIds = deviceDeviceGroups.stream()
                                    .map(DeviceDeviceGroup::getDeviceGroupId)
                                    .collect(Collectors.toList());

        return this.deviceGroupRepository.findAllById(deviceGroupIds);
    }

    public Iterable<Device> getDeviceByDeviceGroupId(long deviceGroupId) {
        List<DeviceDeviceGroup> deviceDeviceGroups = this.deviceDeviceGroupRepository
                                                        .findDeviceDeviceGroupsByDeviceGroupId(deviceGroupId);

        if  (deviceDeviceGroups.isEmpty())
            return new ArrayList<>();

        List<Long> deviceIds = deviceDeviceGroups.stream()
                                .map(DeviceDeviceGroup::getDeviceId)
                                .collect(Collectors.toList());

        return this.deviceRepository.findAllById(deviceIds);
    }
}
