package EIST24L03PB03.Client;

import EIST24L03PB03.Tweet;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import EIST24L03PB03.User;


public class Client {
    private RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();
    private static final String loginMicroserviceURL = "http://localhost:8080/login";
    private static final String tweetMicroserviceURL = "http://localhost:8081/tweet";
    private static final String followMicroserviceURL = "http://localhost:8082/follow";
    private static final String messageBrokerURL = "http://localhost:8083/mb";


    public Client() {
        this.restTemplate = new RestTemplate();
    }

    public String performLogin(User user){

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(loginMicroserviceURL+"/performLogin", requestEntity, String.class);

        return responseEntity.getBody();
    }
    public String sendTweet(Tweet tweet){

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Tweet> requestEntity = new HttpEntity<>(tweet, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(messageBrokerURL+"/tweet/send", requestEntity, String.class);

        return responseEntity.getBody();
    }
    public String deleteTweet(Tweet tweet){

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Tweet> requestEntity = new HttpEntity<>(tweet, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(tweetMicroserviceURL+"/delete", HttpMethod.DELETE, requestEntity, String.class);

        return responseEntity.getBody();

    }

    public String follow(User user){

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(followMicroserviceURL+"/follow", requestEntity, String.class);

        return responseEntity.getBody();
    }
    public String unfollow(User user){

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(followMicroserviceURL+"/unfollow", HttpMethod.DELETE, requestEntity, String.class);

        return responseEntity.getBody();
    }

}
