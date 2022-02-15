package com.intrum.demo.domain.model.payout;

import com.intrum.demo.domain.converter.PayoutToPayoutResponseConverter;
import com.intrum.demo.domain.model.file.FileLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProcessPayoutServiceImpl implements ProcessPayoutService {

    @Value("${fileLoader.path}")
    private String filePath;
    @Value("${fileLoader.prefix}")
    private String filePrefix;
    @Value("${fileLoader.suffix}")
    private String fileNameSuffix;

    private final FileLoaderService fileLoaderService;
    private final SendPayoutService sendPayoutService;
    private final PayoutRepository repository;
    private final PayoutToPayoutResponseConverter converter;

    @Override
    public ProcessPayoutResponse process() {
        List<Payout> payoutList = fileLoaderService.loadFile(getFilePath());
        List<PayoutResponse> payoutResponses = payoutList.stream()
                .map(payout -> {
                    payout.setSent(HttpStatus.OK.equals(sendPayoutService.sendPayout(payout)));
                    repository.save(payout);
                    return converter.convert(payout);
                })
                .collect(Collectors.toList());

        return ProcessPayoutResponse.builder()
                .filename(getFileName())
                .payoutResponses(payoutResponses)
                .build();
    }

    @Override
    public String getFilePath() {
        return filePath + getFileName();
    }

    private String getFileName() {
        return filePrefix + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + fileNameSuffix;
    }
}
