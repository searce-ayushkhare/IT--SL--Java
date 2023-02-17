package com.jwt.jwtauth.controllers;

import com.jwt.jwtauth.entities.JwtRequest;
import com.jwt.jwtauth.entities.JwtResponse;
import com.jwt.jwtauth.helper.JwtUtil;
import com.jwt.jwtauth.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private CustomUserDetailsService customUserDetailsService;
    @Autowired private JwtUtil jwtUtil;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    jwtRequest.getUsername(),
                                    jwtRequest.getPassword()
                            )
                    );
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Username not found...");
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Bad credentials...");
        }

        // User authenticated
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT token :: " + token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
