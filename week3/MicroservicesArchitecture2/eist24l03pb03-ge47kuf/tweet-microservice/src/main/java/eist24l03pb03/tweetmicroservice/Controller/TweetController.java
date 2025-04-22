package eist24l03pb03.tweetmicroservice.Controller;

import eist24l03pb03.tweetmicroservice.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/tweet")
public class TweetController {

    @DeleteMapping("/delete")
    public String deleteTweet(@RequestBody Tweet tweet){
        return "The tweet: \n'''"+tweet.getBody()+"'''\nis deleted";
    }

    @PostMapping(value = "/send")
    public CompletableFuture<String> sendTweet(@RequestBody Tweet tweet){
        //TODO: implement an asynchronous call
        return CompletableFuture.supplyAsync(() -> { // execute asynchron den lambda ausdruck aus
            saveToDb();
            return "tweet is sent";
        });
    }

    // Database functions will be implemented by another team. Database operations will take time to process.
    // So controller method should work async.
    public void saveToDb(){
    // Method body will be implemented.
    }
}

