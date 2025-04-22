package eist24l03p03.activitymicroservice.Controller;

import eist24l03p03.activitymicroservice.FollowRequest;
import eist24l03p03.activitymicroservice.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import eist24l03p03.activitymicroservice.User;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    // TODO: implement the methods of this class. Make a use of the provided data structures
    private Map<Integer, List<Tweet>> userActivityMap = new HashMap<>();
    private Map<Integer, List<User>> userFollowedMap = new HashMap<>();

    @GetMapping("/getActivity/{id}")
    public List<Tweet> getActivity(@PathVariable("id") int userID) {
        List<Tweet> defaultTweetList = new ArrayList<>();

        defaultTweetList = userActivityMap.getOrDefault(userID, new ArrayList<>());
/*
        if (userActivityMap.containsKey(userID)) {
            defaultTweetList = userActivityMap.get(userID);
        }

 */
        return defaultTweetList;
    }

    @PostMapping("/addActivity")
    public void addActivity(@RequestBody Tweet tweet) {
        int userId = tweet.getUser().getUserID();
        userActivityMap.putIfAbsent(userId, new ArrayList<>());
        userActivityMap.get(userId).add(tweet);
    }

    @PostMapping("/addFollower")
    public void addFollower(@RequestBody FollowRequest followRequest) {
        int followerId = followRequest.getFollower().getUserID();
        userFollowedMap.putIfAbsent(followerId, new ArrayList<>());
        userFollowedMap.get(followerId).add(followRequest.getFollowed());
    }

    @DeleteMapping("/deleteActivity")
    public void deleteActivity(@RequestBody Tweet tweet) {
        int userID = tweet.getUser().getUserID();
        List<Tweet> activities = userActivityMap.get(userID);

        if (activities != null) {
            activities.removeIf(t -> t.getTweetID() == tweet.getTweetID());
        }
    }

    @DeleteMapping("/deleteFollower")
    public void deleteFollower(@RequestBody FollowRequest followRequest) {
        int followedId = followRequest.getFollowed().getUserID();
        int followerId = followRequest.getFollower().getUserID();
        List<User> followedUser = userFollowedMap.get(followerId);
        if (followedUser != null) {
            followedUser.removeIf(user -> user.getUserID() == followedId);
        }
    }

    @GetMapping("/getFollowedList/{id}")
    public List<User> getFollowedList(@PathVariable("id") int userID) {
        // TODO: this method is also not complete, so you have to implement it accordingly :)
        List<User> defaultUserList = new ArrayList<>();
        if (userFollowedMap.containsKey(userID)) {
            defaultUserList = userFollowedMap.get(userID);
        }
        return defaultUserList;
    }
}