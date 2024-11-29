package net.engineeringdigest.journalApp.Contoller;

import net.engineeringdigest.journalApp.entry.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/journal")
public class myContoller {

    private Map<Long, JournalEntry> map = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAl(){
        return new ArrayList<>(map.values());
    }
    @PostMapping
    public boolean createentry(@RequestBody JournalEntry myentry){
        map.put(myentry.getId(),myentry);
        return true;

    }
    @GetMapping("/id/{myid}")
    public JournalEntry getId(@PathVariable Long myid){
        return map.get(myid);
    }

    @DeleteMapping("/id/{myid}")
    public JournalEntry delete(@PathVariable Long myid){
        return map.remove(myid);
    }

    @PutMapping("/id/{myid}")
    public JournalEntry updateEntry(@PathVariable Long myid, @RequestBody JournalEntry myentry){
        return map.put(myid,myentry);
    }
}
