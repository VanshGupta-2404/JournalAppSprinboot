//package net.engineeringdigest.journalApp.Contoller;
//
//import net.engineeringdigest.journalApp.Service.JournalEntryService;
//import net.engineeringdigest.journalApp.entry.JournalEntry2; // Use JournalEntry2 class
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/_journal")
//public class myContoller2 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    // Get all journal entries
//    @GetMapping
//    public ResponseEntity<List<JournalEntry2>> getAll() {
//        try {
//            List<JournalEntry2> entries = journalEntryService.getAllEntries();
//            return new ResponseEntity<>(entries, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Create a new journal entry
//    @PostMapping
//    public ResponseEntity<JournalEntry2> createEntry(@RequestBody JournalEntry2 myentry) {
//        try {
//            journalEntryService.saveEntry(myentry); // Persist the entry in MongoDB
//            return new ResponseEntity<>(myentry, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Get a journal entry by ID
//    @GetMapping("/id/{myid}")
//    public ResponseEntity<JournalEntry2> getById(@PathVariable String myid) {
//        // Find entry by ID in MongoDB
//        Optional<JournalEntry2> journalEntry = Optional.ofNullable(journalEntryService.getEntryById(myid)); // Call service method directly
//        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Delete a journal entry by ID
//    @DeleteMapping("/id/{myid}")
//    public ResponseEntity<HttpStatus> delete(@PathVariable String myid) {
//        try {
//            journalEntryService.deleteEntry(myid); // Delete the entry from MongoDB
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Update a journal entry by ID
//    @PutMapping("/id/{myid}")
//    public ResponseEntity<JournalEntry2> updateEntry(@PathVariable String myid, @RequestBody JournalEntry2 myentry) {
//        Optional<JournalEntry2> updatedEntry = Optional.ofNullable(journalEntryService.updateEntry(myid, myentry));
//        return updatedEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//}
