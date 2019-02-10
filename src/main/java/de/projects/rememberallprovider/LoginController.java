package de.projects.rememberallprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class LoginController {
    final private UserDetailsService userDetailsService;

    @Autowired
    public LoginController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getPassword().equals(userDetailsService.loadUserByUsername(user.getUsername()).getPassword());
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}