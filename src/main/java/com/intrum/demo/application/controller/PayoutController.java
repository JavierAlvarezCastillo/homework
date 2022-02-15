package com.intrum.demo.application.controller;

import com.intrum.demo.domain.model.payout.ProcessPayoutResponse;
import com.intrum.demo.domain.model.payout.ProcessPayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PayoutController {
    private final ProcessPayoutService processPayoutService;

    @GetMapping("/test-data-processing")
    public ProcessPayoutResponse testDataProcessing() {
        return processPayoutService.process();
    }
}
