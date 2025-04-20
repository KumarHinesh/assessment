package com.assessment.controller;


import com.assessment.dto.BillResponse;
import com.assessment.model.Bill;
import com.assessment.service.BillCalculationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class BillCalculationController {


    private final BillCalculationService billCalculationService;

    public BillCalculationController(BillCalculationService billCalculationService) {
        this.billCalculationService = billCalculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<BillResponse> calculatePayableAmount(@Valid @RequestBody Bill billRequest) {
        BillResponse billResponse = billCalculationService.calculateBillDetails(billRequest);
        return ResponseEntity.ok(billResponse);
    }

}
