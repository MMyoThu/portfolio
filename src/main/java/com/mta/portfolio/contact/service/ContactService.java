package com.mta.portfolio.contact.service;

import com.mta.portfolio.contact.dto.ContactRequest;
import com.mta.portfolio.contact.entity.ContactMessage;

import java.util.List;

public interface ContactService {

    ContactMessage createContact(ContactRequest request);

    List<ContactMessage> getAllContacts();

    ContactMessage getContactById(Long id);

    void deleteContact(Long id);
}
