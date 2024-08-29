package org.grsl.controllers;

import org.grsl.models.Report;
import org.grsl.schema.generics.PaginationResponse;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.services.ReportGenService;
import org.grsl.utils.Paginator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"})
@RequestMapping("report")
public class ReportController {

    private final ReportGenService reportGenService;

    public ReportController(ReportGenService reportGenService) {
        this.reportGenService = reportGenService;
    }

    @GetMapping("/")
    public BaseHttpResponse getReportByPage(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer perPage) {
        long totalCount = this.reportGenService.getTotalCount();
        Paginator paginator = new Paginator(page, perPage);
        List<Report> reports  = this.reportGenService.getReportByPage(paginator.page());

        return new PaginationResponse<>(reports, paginator.pagination(totalCount));
    }

    @PostMapping("/create")
    public BaseHttpResponse createReport() {
        //todo
    }

}
