package com.contact.contact_service.services;

import com.contact.contact_service.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    // Dummy list of contacts
    List<Contact> list = List.of(
            new Contact(1L, "ashish@gmail.com", "Ashish", 123L),
            new Contact(2L, "avinash@gmail.com", "Avinash", 123L),
            new Contact(3L, "yash@gmail.com", "Yash", 124L),
            new Contact(4L, "rohit@gmail.com", "Rohit", 124L),
            new Contact(5L, "arvind@gmail.com", "Arvind", 124L),
            new Contact(6L, "ashish@gmail.com", "Ashish", 125L)
    );

    @Override
    public List<Contact> getContactsOfUser(Long userId) {
        return list
                .stream()
                .filter(contact -> contact.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
