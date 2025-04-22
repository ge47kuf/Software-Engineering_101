package eist24l03pb01.loginmicroservice.Controller;

import eist24l03pb01.loginmicroservice.Tweet;
import eist24l03pb01.loginmicroservice.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @PostMapping("/performLogin")
    public String authenticateUser(@RequestBody User user) {
        if ("user".equals(user.getUserName()) && "passw".equals(user.getPassword())) {
            return "Login was successful!";
        } else {
            return "Login unsuccessful! Invalid credentials.";
        }
    }

    //TODO: Move the functionalities to their respective Microservices.
    // solution: remove REST stuff from this controller //
    /*
    @PostMapping(value = "/send")
    public String sendTweet(@RequestBody Tweet tweet) {
        return "The tweet is sent";
    }

    @DeleteMapping("/delete")
    public String deleteTweet(@RequestBody Tweet tweet) {
        return "The tweet: \n'''" + tweet.getBody() + "'''\nis deleted";
    }

    @PostMapping("/follow")
    public String follow(@RequestBody User user) {
        return user.getUserName() + " is followed!";
    }

    @DeleteMapping("/unfollow")
    public String unfollow(@RequestBody User user) {
        return user.getUserName() + " is unfollowed!";
    }
     */
}
