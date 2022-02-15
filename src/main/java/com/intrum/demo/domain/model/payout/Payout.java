package com.intrum.demo.domain.model.payout;

import com.intrum.demo.domain.converter.CSVDoubleConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payout")
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_payout", updatable = false)
    private long idPayout;

    @Column(name = "company_name", nullable = false)
    @CsvBindByName(column = "Company name")
    private String companyName;

    @Column(name = "company_tax_number", nullable = false)
    @CsvBindByName(column = "Company tax number")
    private String companyTaxNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @CsvBindByName(column = "Status")
    private PayoutStatus status;

    @Column(name = "payment_date", nullable = false)
    @CsvBindByName(column = "Payment Date")
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    @CsvCustomBindByName(column = "Amount", converter = CSVDoubleConverter.class)
    private Double amount;

    @Column(name = "sent", nullable = false)
    private Boolean sent;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {

        private final Payout object;

        public Builder() {
            object = new Payout();
        }

        public Builder withId(long value) {
            object.idPayout = value;
            return this;
        }

        public Builder withCompanyName(String value) {
            object.companyName = value;
            return this;
        }

        public Builder withCompanyTaxNumber(String value) {
            object.companyTaxNumber = value;
            return this;
        }

        public Builder withStatus(PayoutStatus value) {
            object.status = value;
            return this;
        }

        public Builder withPaymentDate(LocalDate value) {
            object.paymentDate = value;
            return this;
        }

        public Builder withAmount(Double value) {
            object.amount = value;
            return this;
        }

        public Builder withSent(boolean value) {
            object.sent = value;
            return this;
        }

        public Payout build() {
            return object;
        }
    }
}
