package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.model.ContactMessage;
import com.supremesolutions.backend.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactMessageRepository repository;

    public ContactController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ContactMessage saveMessage(@Valid @RequestBody ContactMessage message) {
        return repository.save(message);
    }

    @GetMapping
    public List<ContactMessage> getMessages() {
        return repository.findAll();
    }
}
