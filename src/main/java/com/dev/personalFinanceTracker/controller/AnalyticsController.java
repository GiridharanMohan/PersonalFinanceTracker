package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.dto.TransactionResponseDto;
import com.dev.personalFinanceTracker.service.TransactionService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/analytics")
public class AnalyticsController {

    @Autowired
    private TransactionService transactionService;

    //Used to fetch the monthly transactions of the user as a list
    @GetMapping(path = "/summary")
    public ResponseEntity<Page<TransactionResponseDto>> getMonthlySummary(@RequestParam @Min(1) @Max(12) int month,
                                                                          @RequestParam @Min(1) @Max(3000) int year,
                                                                          @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(transactionService.getMonthlySummary(month, year, page, size), HttpStatus.OK);
    }

    /*used to group the transactions along with the details and show to the user
    in a diagrammatical format such as pie chart, bar chart, etc.*/
    @GetMapping(path = "/break-down")
    public ResponseEntity<List<TransactionResponseDto>> getFinanceBreakdown(@RequestParam int month, @RequestParam int year,
                                                                            @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(transactionService.getFinanceBreakdown(month, year, page, size), HttpStatus.ACCEPTED);
    }
}
