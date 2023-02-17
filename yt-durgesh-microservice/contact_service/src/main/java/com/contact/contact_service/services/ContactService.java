package com.contact.contact_service.services;

import com.contact.contact_service.entities.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> getContactsOfUser(Long userId);
}
