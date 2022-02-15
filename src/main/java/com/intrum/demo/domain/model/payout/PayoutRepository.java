package com.intrum.demo.domain.model.payout;

import java.util.Optional;

public interface PayoutRepository {
    Payout save(Payout payout);
    Optional<Payout> findById(Long id);
}
