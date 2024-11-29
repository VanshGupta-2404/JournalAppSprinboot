package net.engineeringdigest.journalApp.Contoller;

import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entry.JournalEntry2; // Use JournalEntry2 class
import net.engineeringdigest.journalApp.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/_journal")
public class JournalEnteryController {
    @Autowired
    private UserService userService;
    @Autowired
    private JournalEntryService journalEntryService;

    // Get all journal entries
    @GetMapping()
    public ResponseEntity<?> getAllJournalEnteries() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        try {
            List<JournalEntry2> entries =user.getJournalEnteries();
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new journal entry
    @PostMapping()
    public ResponseEntity<JournalEntry2> createEntry(@RequestBody JournalEntry2 myentry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try {

            journalEntryService.saveEntry(myentry,username); // Persist the entry in MongoDB
            return new ResponseEntity<>(myentry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get a journal entry by ID
    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry2> getById(@PathVariable String myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // Find entry by ID in MongoDB
        User user = userService.findByUsername(username);
        List<JournalEntry2> collect = user.getJournalEnteries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry2> journalEntry2 = Optional.ofNullable(journalEntryService.getEntryById(myid));
            if(journalEntry2.isPresent()) {
                return new ResponseEntity<>(journalEntry2.get(), HttpStatus.OK);
            }
        }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a journal entry by ID
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            journalEntryService.deleteEntry(myid,username); // Delete the entry from MongoDB
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a journal entry by ID
    @PutMapping("/id/{myid}")
    public ResponseEntity<JournalEntry2> updateEntry(@PathVariable String myid, @RequestBody JournalEntry2 myentry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry2> collect = user.getJournalEnteries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry2> journalEntry2 = Optional.ofNullable(journalEntryService.getEntryById(myid));
            if(journalEntry2.isPresent()) {
                Optional<JournalEntry2> updatedEntry = Optional.ofNullable(journalEntryService.updateEntry(myid, myentry));
            }
        }

        Optional<JournalEntry2> updatedEntry = Optional.ofNullable(journalEntryService.updateEntry(myid, myentry));
        return updatedEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
