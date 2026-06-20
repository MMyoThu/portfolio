package com.mta.portfolio.contact.controller;

import com.mta.portfolio.common.response.ApiResponse;
import com.mta.portfolio.contact.dto.ContactRequest;
import com.mta.portfolio.contact.entity.ContactMessage;
import com.mta.portfolio.contact.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContactMessage>> createContact(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", contactService.createContact(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContactMessage>>> listContacts() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", contactService.getAllContacts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactMessage>> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", contactService.getContactById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", null));
    }
}
