package com.mta.portfolio.contact.service.impl;

import com.mta.portfolio.common.exception.ResourceNotFoundException;
import com.mta.portfolio.contact.dto.ContactRequest;
import com.mta.portfolio.contact.entity.ContactMessage;
import com.mta.portfolio.contact.repository.ContactMessageRepository;
import com.mta.portfolio.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactMessageRepository repository;

    @Override
    public ContactMessage createContact(ContactRequest request) {
        ContactMessage message = new ContactMessage();
        message.setName(request.getName());
        message.setEmail(request.getEmail());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        return repository.save(message);
    }

    @Override
    public List<ContactMessage> getAllContacts() {
        return repository.findAll();
    }

    @Override
    public ContactMessage getContactById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactMessage", "id", id));
    }

    @Override
    public void deleteContact(Long id) {
        ContactMessage message = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactMessage", "id", id));
        repository.delete(message);
    }
}
