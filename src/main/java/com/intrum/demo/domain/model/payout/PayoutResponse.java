package com.intrum.demo.domain.model.payout;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayoutResponse {
    private String companyIdentityNumber;
    private String paymentDate;
    private Double paymentAmount;
    private boolean sent;
}
