package com.intrum.demo.services;

import com.intrum.demo.domain.model.file.FileLoaderService;
import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutStatus;
import com.intrum.demo.infrastructure.service.FileLoaderServiceResourcesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FileLoaderServiceTest {

    @Autowired
    private FileLoaderService fileLoaderService;

    @Test
    public void testObtainFileFromResources() {
        // Given
        fileLoaderService = new FileLoaderServiceResourcesImpl();

        // When
        List<Payout> payoutFromFile = fileLoaderService.loadFile("src/test/resources/payout_files/test.csv");

        // Then
        assertThat(payoutFromFile.size(), is(3));
        assertThat(payoutFromFile.get(1).getCompanyTaxNumber(), is(getTestPayoutList().get(1).getCompanyTaxNumber()));
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
