package com.intrum.demo.domain.model.payout;

import org.springframework.http.HttpStatus;

public interface SendPayoutService {
    HttpStatus sendPayout(Payout payout);
}
