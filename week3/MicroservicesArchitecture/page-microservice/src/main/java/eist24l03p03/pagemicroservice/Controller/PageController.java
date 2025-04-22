package eist24l03p03.pagemicroservice.Controller;

import eist24l03p03.pagemicroservice.User;
import eist24l03p03.pagemicroservice.Tweet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {
    private static final String getActivityURL = "http://localhost:8084/activity/getActivity/";
    private static final String getFollowedListURL = "http://localhost:8084/activity/getFollowedList/";
    HttpHeaders headers = new HttpHeaders();
    // TODO: implement the methods of this class. Make a use of the provided data structures
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getTimeLine/{id}")
    // page of one user
    public List<Tweet> getTimeLine(@PathVariable("id") int userID) {
        List<Tweet> timeLine = new ArrayList<>();

        ResponseEntity<List<Tweet>> response = restTemplate.exchange(
                getActivityURL + userID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tweet>>() {}
        );
        if (response.getBody() != null) {
            timeLine.addAll(response.getBody());
        }
/*
        Tweet[] tweetsArray = restTemplate.getForObject(getActivityURL + userID, Tweet[].class);

        if (tweetsArray != null) {
            timeLine.addAll(List.of(tweetsArray));
        }

 */
        return timeLine;
    }

    @GetMapping("/getHomePage/{id}")
    // pages of the followed users, i.g. feed
    public List<Tweet> getHomePage(@PathVariable("id") int userID) {
        List<Tweet> homePage = new ArrayList<>();

        ResponseEntity<List<User>> followedUsersResponse = restTemplate.exchange(
                getFollowedListURL + userID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );

        List<User> followedUsers = followedUsersResponse.getBody();

        if (followedUsers != null) {
            for (int i = 0; i < followedUsers.size(); i++ ) {
                User user = followedUsers.get(i);

                List<Tweet> usertimeLine = getTimeLine(user.getUserID());

                if (usertimeLine != null) {
                    homePage.addAll(usertimeLine);
                }
            }
        }
        return homePage;
    }
}