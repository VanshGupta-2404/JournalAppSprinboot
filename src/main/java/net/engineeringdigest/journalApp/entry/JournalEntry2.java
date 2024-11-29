package net.engineeringdigest.journalApp.entry;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
// It is equivalent to getter,setter,RequiredArgs and toString, bole to ye sab banadega alagse nhi likhne ki jarrorat..
@Document(collection = "journal_enteries")//ye kya karta hai jo bhi JornalEntry2 mein likha hai usko vo mongoDB ke ek row ke barabar hoga
@NoArgsConstructor//ye use hota hai for decerialisation jo use hota hai json to pojo karne keliya.. basically default constructor banata hai
@Data
public class JournalEntry2{
    private String id;
    private String title;
    private String content;
    private Date date;



}