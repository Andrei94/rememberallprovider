package de.projects.rememberallprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getPassword().equals(userDetailsService.loadUserByUsername(user.getUsername()).getPassword());
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}

