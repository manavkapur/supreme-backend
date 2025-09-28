package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.model.QuoteRequest;
import com.supremesolutions.backend.repository.QuoteRequestRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteRequestRepository repository;

    public QuoteController(QuoteRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public QuoteRequest saveQuote(@Valid @RequestBody QuoteRequest quote) {
        return repository.save(quote);
    }

    @GetMapping
    public List<QuoteRequest> getQuotes() {
        return repository.findAll();
    }
}
