package com.intrum.demo.rest;

import com.intrum.demo.domain.model.file.FileLoaderService;
import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutStatus;
import com.intrum.demo.domain.model.payout.ProcessPayoutResponse;
import com.intrum.demo.domain.model.payout.ProcessPayoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PayoutControllerITTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProcessPayoutService processPayoutService;

    @MockBean
    private FileLoaderService fileLoaderService;

    @LocalServerPort
    private int port;

    @Test
    public void testController() {

        // Given
        doReturn(getTestPayoutList()).when(fileLoaderService).loadFile(anyString());

        // When
        String url = "http://localhost:" + this.port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/test-data-processing").build().toUri();
        ResponseEntity<ProcessPayoutResponse> result = this.restTemplate.getForEntity(uri, ProcessPayoutResponse.class);


        // Then
        assertThat(result, is(not(nullValue())));
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        ProcessPayoutResponse response = result.getBody();
        assertThat(response, is(not(nullValue())));
        assertThat(response.getPayoutResponses().size(), is(3));
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
