package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.model.ContactMessage;
import com.supremesolutions.backend.repository.ContactMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    private final ContactMessageRepository contactRepository;

    public ContactController(ContactMessageRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMessage(@Valid @RequestBody ContactMessage contactMessage) {
        contactRepository.save(contactMessage);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Message submitted successfully");
        response.put("data", contactMessage);

        return ResponseEntity.ok(response);
    }

    // ✅ Fetch all messages
    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(contactRepository.findAll());
    }
    // Delete a contact message by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMessage(@PathVariable Long id) {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.status(404).body(Map.of(
                    "success", false,
                    "message", "Message not found with id " + id
            ));
        }

        contactRepository.deleteById(id);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Message deleted successfully"
        ));
    }

}

