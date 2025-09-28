package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.model.QuoteRequest;
import com.supremesolutions.backend.repository.QuoteRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteRequestRepository quoteRepository;

    public QuoteController(QuoteRequestRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createQuote(@Valid @RequestBody QuoteRequest quoteRequest) {
        quoteRepository.save(quoteRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Quote submitted successfully");
        response.put("data", quoteRequest);

        return ResponseEntity.ok(response);
    }


    // ✅ New endpoint: fetch all quotes
    @GetMapping
    public ResponseEntity<List<QuoteRequest>> getAllQuotes() {
        return ResponseEntity.ok(quoteRepository.findAll());
    }

    // Delete a quote by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteQuote(@PathVariable Long id) {
        if (!quoteRepository.existsById(id)) {
            return ResponseEntity.status(404).body(Map.of(
                    "success", false,
                    "message", "Quote not found with id " + id
            ));
        }

        quoteRepository.deleteById(id);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Quote deleted successfully"
        ));
    }

}
