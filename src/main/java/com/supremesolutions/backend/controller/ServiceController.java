package com.supremesolutions.backend.controller;

import com.supremesolutions.backend.exception.ServiceNotFoundException;
import com.supremesolutions.backend.model.Service;
import com.supremesolutions.backend.repository.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // CREATE
    @PostMapping
    public Service createService(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }

    // READ (all)
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // READ (by id)
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Service updateService(@PathVariable Long id, @Valid @RequestBody Service updatedService) {
        return serviceRepository.findById(id).map(service -> {
            service.setName(updatedService.getName());
            service.setDescription(updatedService.getDescription());
            service.setPriceRange(updatedService.getPriceRange());
            return serviceRepository.save(service);
        }).orElseThrow(() -> new ServiceNotFoundException(id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        return serviceRepository.findById(id).map(service -> {
            serviceRepository.delete(service);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ServiceNotFoundException(id));
    }
}
