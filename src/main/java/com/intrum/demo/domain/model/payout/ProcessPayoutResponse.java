package com.intrum.demo.domain.model.payout;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProcessPayoutResponse {
    private String filename;
    private List<PayoutResponse> payoutResponses;
}
