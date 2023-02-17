package com.user.user_service.services;

import com.user.user_service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // Dummy user list
    List<User> list = List.of(
            new User(123L, "John", "9876543210"),
            new User(124L, "Sam", "9876543201"),
            new User(125L, "Peter", "9876543211")
    );

    @Override
    public User getUser(Long uId) {
        return this.list
                .stream()
                .filter(user -> user.getUserId().equals(uId))
                .findAny()
                .orElse(null);
    }
}
