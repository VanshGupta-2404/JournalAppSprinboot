package net.engineeringdigest.journalApp.entry;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
@Data
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)// isse kya hoga basically hum index bana nhi rahe means har baar unique nhi hoga, so isse theek karne hum gaye application.properties ke passs jahan pe sahi se hojaega.
    @NonNull
    private String username;

    @NonNull
    private String password;

    @DBRef// This is use asDatabase Reference bole to ys refer karega user database ko journalEntryDatabase se..
    private List<JournalEntry2> JournalEnteries = new ArrayList<>();
    private List<String> roles;
}
