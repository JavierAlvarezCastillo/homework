package com.intrum.demo.services;

import com.intrum.demo.domain.model.file.FileLoaderService;
import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutRepository;
import com.intrum.demo.domain.model.payout.PayoutStatus;
import com.intrum.demo.domain.model.payout.ProcessPayoutResponse;
import com.intrum.demo.domain.model.payout.ProcessPayoutService;
import com.intrum.demo.domain.model.payout.SendPayoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProcessPayoutServiceTest {

    @Autowired
    private ProcessPayoutService processPayoutService;

    @MockBean
    private FileLoaderService fileLoaderService;

    @MockBean
    private SendPayoutService sendPayoutService;

    @MockBean
    private PayoutRepository payoutRepository;

    @Test
    public void doProcessAndThatWeSaveThreePayouts() {
        // Given
        when(fileLoaderService.loadFile(anyString())).thenReturn(getTestPayoutList());
        when(sendPayoutService.sendPayout(any(Payout.class))).thenReturn(HttpStatus.OK);

        // When
        processPayoutService.process();

        // Then
        ArgumentCaptor<Payout> payoutCaptor = ArgumentCaptor.forClass(Payout.class);
        verify(payoutRepository,  times(3)).save(payoutCaptor.capture());
    }

    private List<Payout> getTestPayoutList() {
        List<Payout> list = new ArrayList<>();
        Payout payout1 = new Payout.Builder()
                .withCompanyName("Iron suites")
                .withCompanyTaxNumber("156-5562415")
                .withStatus(PayoutStatus.PENDING)
                .withPaymentDate(LocalDate.of(2023, 11, 17))
                .withAmount(7000.10)
                .build();
        Payout payout2 = new Payout.Builder()
                .withCompanyName("Shield factory")
                .withCompanyTaxNumber("557-3562662")
                .withStatus(PayoutStatus.PAID)
                .withPaymentDate(LocalDate.of(2022, 5, 1))
                .withAmount(9999.0)
                .build();
        Payout payout3 = new Payout.Builder()
                .withCompanyName("Car parts")
                .withCompanyTaxNumber("988-5561635")
                .withStatus(PayoutStatus.PENDING)
                .withPaymentDate(LocalDate.of(2022, 1, 11))
                .withAmount(7000.10)
                .build();
        list.add(payout1);
        list.add(payout2);
        list.add(payout3);

        return list;
    }
}
