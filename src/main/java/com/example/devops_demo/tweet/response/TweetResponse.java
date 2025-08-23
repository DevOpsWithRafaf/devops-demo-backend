package com.example.devops_demo.tweet.response;

import com.example.devops_demo.tweet.Tweet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TweetResponse {
    private String message;
    private Tweet tweet;
}
