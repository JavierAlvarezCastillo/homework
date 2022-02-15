package com.intrum.demo.domain.converter;

import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutRequest;
import com.intrum.demo.domain.model.payout.PayoutResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PayoutToPayoutResponseConverter implements Converter<Payout, PayoutResponse> {
    
    @Override
    public PayoutResponse convert(Payout source) {
        return PayoutResponse.builder()
                .companyIdentityNumber(source.getCompanyTaxNumber())
                .paymentDate(source.getPaymentDate().toString())
                .paymentAmount(source.getAmount())
                .sent(source.getSent())
                .build();
    }
}
