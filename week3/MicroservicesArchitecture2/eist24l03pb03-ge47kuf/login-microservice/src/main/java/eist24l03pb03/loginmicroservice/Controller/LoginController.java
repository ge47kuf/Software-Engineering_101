package eist24l03pb03.loginmicroservice.Controller;

import eist24l03pb03.loginmicroservice.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @PostMapping("/performLogin")
    public String authenticateUser(@RequestBody User user){
        if("user".equals(user.getUserName()) && "passw".equals(user.getPassword())){
            return "Login was succesful!";
        } else {
            return "Login unsuccesful! Invalid credentials."; // maybe add http return statuscodes as returnentity
        }
    }
}
