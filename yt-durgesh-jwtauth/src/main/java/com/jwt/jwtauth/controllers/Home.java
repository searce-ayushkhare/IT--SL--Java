package com.jwt.jwtauth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {
        return "This is a private page & is not allowed to unauthenticated users";
    }
}
