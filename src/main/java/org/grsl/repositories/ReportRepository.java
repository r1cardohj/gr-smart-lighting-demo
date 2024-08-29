package org.grsl.repositories;

import org.grsl.models.Report;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("SELECT * FROM report LIMIT :offset,:limit")
    public List<Report> findReportByPage(int limit, int offset);

    class ReportNotExistException extends RuntimeException {}
}
