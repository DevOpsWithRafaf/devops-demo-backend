package com.example.devops_demo.tweet;

import org.springframework.web.bind.annotation.*;

import com.example.devops_demo.security.JwtRequestUtil;
import com.example.devops_demo.security.JwtUtil;
import com.example.devops_demo.tweet.response.AllTweetResponse;
import com.example.devops_demo.tweet.response.TweetResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final JwtRequestUtil jwtRequestUtil;
    private final JwtUtil jwtUtil;

    public TweetController(TweetService tweetService, JwtRequestUtil jwtRequestUtil, JwtUtil jwtUtil) {
        this.tweetService = tweetService;
        this.jwtRequestUtil = jwtRequestUtil;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<AllTweetResponse> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PostMapping
    public TweetResponse createTweet(@RequestParam String content, HttpServletRequest request) {
        String token = jwtRequestUtil.extractToken(request);
        Long userId = jwtUtil.extractUserId(token);
        return tweetService.createTweet(userId, content);
    }

}
