package net.engineeringdigest.journalApp.Contoller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Added @RestController to enable RESTful web service
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Get all users

    @GetMapping
    public List<User> getAll() {
        return userService.getAllEntries();
    }


    // Update user by username
    @PutMapping
    public ResponseEntity<?> updateEntry(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        // Fetch user by authenticated username (not the one in the request body)
        User existingUser = userService.findByUsername(authenticatedUsername);

        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Allow only the authenticated user to update their own data
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword()); // Assuming password needs updating

        // Save the updated user
        userService.saveNewuser(existingUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> DeleteByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

    // Additional endpoints can be added as needed, such as delete, get by ID, etc.

