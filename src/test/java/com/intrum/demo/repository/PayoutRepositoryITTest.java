package com.intrum.demo.repository;

import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutRepository;
import com.intrum.demo.domain.model.payout.PayoutStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayoutRepositoryITTest {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private PayoutRepository payoutRepository;

    @Test
    public void givenNewPayoutSavedThenPayoutRetrieved() {
        // Given
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);

        Payout payout = new Payout.Builder()
                .withId(1L)
                .withCompanyName("Iron suites")
                .withCompanyTaxNumber("156-5562415")
                .withStatus(PayoutStatus.PENDING)
                .withPaymentDate(LocalDate.of(2023, 11, 17))
                .withAmount(7000.10)
                .withSent(true)
                .build();

        payoutRepository.save(payout);

        // When
        transactionTemplate.execute(s -> payoutRepository.save(payout));

        Optional<Payout> optionalPayout = transactionTemplate.execute(s -> payoutRepository.findById(1L));

        // Then
        Payout result = optionalPayout.orElse(null);
        assertThat(result, is(not(nullValue())));
        assertThat(result.getIdPayout(), is(1L));
        assertThat(result.getCompanyName(), is("Iron suites"));
        assertThat(result.getCompanyTaxNumber(), is("156-5562415"));
        assertThat(result.getStatus(), is(PayoutStatus.PENDING));
        assertThat(result.getPaymentDate(), is(LocalDate.of(2023, 11, 17)));
        assertThat(result.getAmount(), is(7000.10));
        assertThat(result.getSent(), is(true));
    }
}