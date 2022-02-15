package com.intrum.demo.infrastructure.repository;

import com.intrum.demo.domain.model.payout.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutJPARepository extends JpaRepository<Payout, Long> {

}
