package org.grsl.repositories;

import org.grsl.models.ReportDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDetailRepository extends CrudRepository<ReportDetail, Long> {

    @Query("SELECT * FROM report_detail WHERE report_id = :reportId")
    public List<ReportDetail> findAllReportDetailByReportId(long reportId);
}
