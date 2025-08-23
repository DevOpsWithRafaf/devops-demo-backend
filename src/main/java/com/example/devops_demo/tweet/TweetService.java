package com.example.devops_demo.tweet;

import com.example.devops_demo.tweet.response.AllTweetResponse;
import com.example.devops_demo.tweet.response.TweetResponse;
import com.example.devops_demo.user.User;
import com.example.devops_demo.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public List<AllTweetResponse> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();

        return tweets.stream()
                .map((Tweet tweet) -> AllTweetResponse.builder()
                        .id(tweet.getId())
                        .content(tweet.getContent())
                        .username(tweet.getUser().getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    public TweetResponse createTweet(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet = Tweet.builder()
                .content(content)
                .user(user)
                .build();

        Tweet savedTweet = tweetRepository.save(tweet);

        return new TweetResponse("Tweet has been posted successfully!", savedTweet);
    }
}
