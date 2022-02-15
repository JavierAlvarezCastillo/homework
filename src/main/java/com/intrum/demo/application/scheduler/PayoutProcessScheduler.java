package com.intrum.demo.application.scheduler;


import com.intrum.demo.domain.model.payout.ProcessPayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PayoutProcessScheduler {
    private final ProcessPayoutService processPayoutService;

    @Scheduled(cron="0 0 4 * * *", zone="Europe/Madrid")
    public void schedulePayoutProcess() {
        processPayoutService.process();
    }
}
