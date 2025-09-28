package com.supremesolutions.backend.repository;

import com.supremesolutions.backend.model.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
}
