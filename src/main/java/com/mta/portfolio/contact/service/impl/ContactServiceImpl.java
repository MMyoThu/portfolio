package com.mta.portfolio.contact.service.impl;

import com.mta.portfolio.common.exception.ResourceNotFoundException;
import com.mta.portfolio.contact.dto.ContactRequest;
import com.mta.portfolio.contact.entity.ContactMessage;
import com.mta.portfolio.contact.repository.ContactMessageRepository;
import com.mta.portfolio.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
    @Transactional(readOnly = true)
    public List<ContactMessage> getAllContacts() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactMessage getContactById(Long id) {
        return findContact(id);
    }

    @Override
    public void deleteContact(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ContactMessage", "id", id);
        }
        repository.deleteById(id);
    }

    private ContactMessage findContact(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactMessage", "id", id));
    }
}
