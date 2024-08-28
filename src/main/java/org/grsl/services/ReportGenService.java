package org.grsl.services;

import org.grsl.models.Report;
import org.grsl.models.ReportDetail;
import org.grsl.repositories.ReportDetailRepository;
import org.grsl.repositories.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportGenService {

    private final ReportRepository reportRepository;
    private final ReportDetailRepository reportDetailRepository;

    public ReportGenService(ReportRepository reportRepository,
                            ReportDetailRepository reportDetailRepository) {
        this.reportRepository = reportRepository;
        this.reportDetailRepository = reportDetailRepository;
    }

    @Transactional
    public Report createReport(Date beginDt, Date endDt, List<Long> deviceIds) {
        Report report = new Report();
        report.setStatus(0);
        report.setBeginDt(beginDt);
        report.setEndDt(endDt);
        report.setCreatedDt(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        report.setReportName("rp" + sdf.format(report.getCreatedDt()));
        this.reportRepository.save(report);

        List<ReportDetail> reportDetails = deviceIds.stream().map((elem) -> {
            ReportDetail reportDetail = new ReportDetail();
            reportDetail.setReportId(report.getId());
            reportDetail.setDeviceId(elem);
            return reportDetail;
        }).collect(Collectors.toList());

        this.reportDetailRepository.saveAll(reportDetails);
        return report;
    }

    public List<ReportDetail> getAllReportDetailByReportId(long reportId) {
        return this.reportDetailRepository.findAllReportDetailByReportId(reportId);
    }

    public Report getReportById(long reportId) {
        return this.reportRepository.findById(reportId)
                .orElseThrow(ReportRepository.ReportNotExistException::new);
    }

    public long getTotalCount() {
        return this.reportRepository.count();
    }
}
