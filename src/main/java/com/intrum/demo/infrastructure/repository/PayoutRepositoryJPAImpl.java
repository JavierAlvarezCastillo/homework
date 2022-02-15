package com.intrum.demo.infrastructure.repository;

import com.intrum.demo.domain.model.payout.Payout;
import com.intrum.demo.domain.model.payout.PayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PayoutRepositoryJPAImpl implements PayoutRepository {

    private final PayoutJPARepository payoutJPARepository;

    @Override
    public Payout save(Payout payout) {
        return payoutJPARepository.save(payout);
    }

    @Override
    public Optional<Payout> findById(Long id) {
        return payoutJPARepository.findById(id);
    }
}
