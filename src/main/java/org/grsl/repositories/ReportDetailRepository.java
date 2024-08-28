package org.grsl.repositories;

import org.grsl.models.ReportDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailRepository extends CrudRepository<ReportDetail, Long> {
}
