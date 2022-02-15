package com.intrum.demo.domain.model.payout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@RequiredArgsConstructor
public class PayoutRequest {
    private String companyIdentityNumber;
    private String paymentDate;
    private Double paymentAmount;

    @Override
    public String toString() {
        return "Payout{" +
                "companyIdentityNumber='" + companyIdentityNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }

    public static class Builder {

        private final PayoutRequest object;

        public Builder() {
            object = new PayoutRequest();
        }

        public PayoutRequest.Builder withCompanyIdentityNumber(String value) {
            object.companyIdentityNumber = value;
            return this;
        }

        public PayoutRequest.Builder withPaymentDate(String value) {
            object.paymentDate = value;
            return this;
        }

        public PayoutRequest.Builder withPaymentAmount(Double value) {
            object.paymentAmount = value;
            return this;
        }

        public PayoutRequest build() {
            return object;
        }
    }
}
