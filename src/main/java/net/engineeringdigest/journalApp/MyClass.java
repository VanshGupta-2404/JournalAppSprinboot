package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // It is used as RestfulAPI that can be used anywhere
public class MyClass {

    @GetMapping("abc")//This is used for mapping the website
    public String sayHello(){
        return "Hello Gupta";
    }
}
