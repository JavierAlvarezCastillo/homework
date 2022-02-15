package com.intrum.demo.domain.model.payout;

public interface ProcessPayoutService {
    ProcessPayoutResponse process();
    String getFilePath();
}
