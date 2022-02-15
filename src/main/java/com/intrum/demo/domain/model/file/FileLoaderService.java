package com.intrum.demo.domain.model.file;

import com.intrum.demo.domain.model.payout.Payout;

import java.util.List;

public interface FileLoaderService {
    List<Payout> loadFile(String filename);
}
