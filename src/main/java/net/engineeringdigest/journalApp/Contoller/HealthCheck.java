package net.engineeringdigest.journalApp.Contoller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class HealthCheck {
    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthcheck(){
        return "Ok";
    }
    @PostMapping("/contoller")
    public ResponseEntity<User> createEntry(@RequestBody User user) {
        userService.saveNewuser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED); // Return 201 CREATED
    }
}
