package net.engineeringdigest.journalApp.Contoller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getallusers(){
        List<?> user = userService.getAllEntries();

        if(user != null && !user.isEmpty()){
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save-admin")
    public void saveAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }

}
