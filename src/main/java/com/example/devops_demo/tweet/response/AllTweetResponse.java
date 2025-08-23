package com.example.devops_demo.tweet.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllTweetResponse {
    private Long id;
    private String content;
    private String username;
}
