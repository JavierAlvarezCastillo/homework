package com.intrum.demo.domain.converter;

import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PayoutToPayoutRequestConverter implements Converter<Payout, PayoutRequest> {
    
    @Override
    public PayoutRequest convert(Payout source) {
        return new PayoutRequest.Builder()
                .withCompanyIdentityNumber(source.getCompanyTaxNumber())
                .withPaymentDate(source.getPaymentDate().toString())
                .withPaymentAmount(source.getAmount())
                .build();
    }
}
