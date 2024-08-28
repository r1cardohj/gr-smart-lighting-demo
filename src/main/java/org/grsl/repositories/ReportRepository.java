package org.grsl.repositories;

import org.grsl.models.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    class ReportNotExistException extends RuntimeException {}
}
