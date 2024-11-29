package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.JournalRepository;
import net.engineeringdigest.journalApp.entry.JournalEntry2;
import net.engineeringdigest.journalApp.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private  UserService userService;
    @Autowired
    private JournalRepository journalRepository; // No need for 'static'
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    @Transactional// iska use se sab kaam ek saath hoga jo bhi iske  aandar likha hai...
    public void saveEntry(JournalEntry2 journalEntry2, String username) {
        User user = userService.findByUsername(username);
        JournalEntry2 saved = journalRepository.save(journalEntry2);
        user.getJournalEnteries().add(saved); // We have added in List the saved data.
        userService.savenewentry(user);//Idhar humne user save karadiya hai of journlEntery, basically save karde hai entry in database
        logger.info("Chal tere Makai");
    }

    public List<JournalEntry2> getAllEntries() {
        return journalRepository.findAll();
    }

    public JournalEntry2 getEntryById(String id) {
        return journalRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteEntry(String id, String username) {
        User user = userService.findByUsername(username);
        boolean removed = user.getJournalEnteries().removeIf(x -> x.getId().equals(id));
        if(removed){
            userService.savenewentry(user);
            journalRepository.deleteById(id);
        }

    }

    public JournalEntry2 updateEntry(String id, JournalEntry2 updatedEntry) {
        if (journalRepository.existsById(id)) {
            updatedEntry.setId(id);  // Ensure the ID is preserved
            return journalRepository.save(updatedEntry);
        }
        return null;
    }
}
