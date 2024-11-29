package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entry.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.util.Arrays;
import java.util.List;
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository; // Remove static
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean saveNewuser(User journalEntry2) {
        try {

                journalEntry2.setPassword(passwordEncoder.encode(journalEntry2.getPassword()));


        journalEntry2.setRoles(Arrays.asList("USER"));
        userRepository.save(journalEntry2);
        return true;
    }catch(Exception e) {
            logger.info("Already Exist");
            return false;
        }

    }
    public void saveAdmin(User journalEntry2) {
        if (journalEntry2.getPassword() != null) {
            journalEntry2.setPassword(passwordEncoder.encode(journalEntry2.getPassword()));
        }
        journalEntry2.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(journalEntry2);
    }
    public void savenewentry(User journalEntry2) {
        userRepository.save(journalEntry2);
    }

    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    public User getEntryById(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteEntry(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User updateEntry(ObjectId id, User updatedEntry) {
        if (userRepository.existsById(id)) {
            updatedEntry.setId(id);  // Ensure the ID is preserved
            return userRepository.save(updatedEntry);
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
