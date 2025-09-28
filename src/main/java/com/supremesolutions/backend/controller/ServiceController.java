package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.model.Service;
import com.supremesolutions.backend.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // GET all services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // POST new service
    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
