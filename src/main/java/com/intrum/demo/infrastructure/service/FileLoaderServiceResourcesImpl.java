package com.intrum.demo.infrastructure.service;

import com.intrum.demo.domain.model.file.FileLoaderService;
import com.intrum.demo.domain.model.payout.Payout;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileLoaderServiceResourcesImpl implements FileLoaderService {
    private static final Logger logger = LoggerFactory.getLogger(FileLoaderServiceResourcesImpl.class);

    @Override
    public List<Payout> loadFile(String filename) {
        File file = new File(filename);
        List<Payout> payoutList = new ArrayList<>();
        try {
            payoutList = new CsvToBeanBuilder<Payout>(new FileReader(file))
                    .withType(Payout.class)
                    .withSeparator(';')
                    .withSkipLines(0)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            logger.error("Error while loading file: " + e.getMessage());
        }

        return payoutList;
    }


}
