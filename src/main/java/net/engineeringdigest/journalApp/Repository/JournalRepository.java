package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entry.JournalEntry2;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry2, String> {
    // Custom query methods can be added here if needed
}
