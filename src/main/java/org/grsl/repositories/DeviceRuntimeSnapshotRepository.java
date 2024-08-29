package org.grsl.repositories;

import org.grsl.models.DeviceRuntimeSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRuntimeSnapshotRepository extends CrudRepository<DeviceRuntimeSnapshot, Long> {
}
