//package net.engineeringdigest.journalApp.Controller;
//
//import net.engineeringdigest.journalApp.Repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testFindByUsername(){
//        assertEquals(4,2+2);
//
//        assertNotNull(userRepository.findByUsername("ram"));
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//      "ram",
//      "rishabhu",
//
//    })
//    public void testFindBylistofUsername(String name){
//
//
//        assertNotNull(userRepository.findByUsername(name),"the username kit is falied for: "+ name);
//    }
//}
