package com.intrum.demo.domain.model.payout;

import com.intrum.demo.domain.converter.PayoutToPayoutRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendPayoutServiceImpl implements SendPayoutService {
    private final PayoutToPayoutRequestConverter converter;

    private final RestTemplate restTemplate;

    @Value("${sendPayout.url}")
    private String url;

    @Autowired
    public SendPayoutServiceImpl(PayoutToPayoutRequestConverter converter) {
        this.converter = converter;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public HttpStatus sendPayout(Payout payout) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, converter.convert(payout), String.class);
        return response.getStatusCode();
    }
}
