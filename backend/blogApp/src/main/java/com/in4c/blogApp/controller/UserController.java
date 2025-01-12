package com.in4c.blogApp.controller;

import com.in4c.blogApp.model.Hashtag;
import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.model.Profile;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.ResultWrapper;
import com.in4c.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{userId}/follow/profile/{profileId}")
    public ResponseEntity<?> followProfile(@PathVariable("profileId") long profileId, @PathVariable("userId") long userId) {
        Result<?> res = userService.followProfile(profileId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{userId}/unfollow/profile/{profileId}")
    public ResponseEntity<?> unfollowProfile(@PathVariable("profileId") long profileId, @PathVariable("userId") long userId) {
        Result<?> res = userService.unfollowProfile(profileId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@PostMapping("/{userId}/like/post/{postId}")
    public ResponseEntity<?> likePost(@PathVariable("postId") long postId, @PathVariable("userId") long userId) {
        Result<?> res = userService.likePost(postId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@PostMapping("/{userId}/dislike/post/{postId}")
    public ResponseEntity<?> dislikePost(@PathVariable("postId") long postId, @PathVariable("userId") long userId) {
        Result<?> res = userService.dislikePost(postId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@PostMapping("/{userId}/follow/hashtag/{tagId}")
    public ResponseEntity<?> followHashtag(@PathVariable("tagId") long tagId, @PathVariable("userId") long userId) {
        Result<?> res = userService.followHashtag(tagId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@PostMapping("/{userId}/unfollow/hashtag/{tagId}")
    public ResponseEntity<?> unfollowHashtag(@PathVariable("tagId") long tagId, @PathVariable("userId") long userId) {
        Result<?> res = userService.unfollowHashtag(tagId, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@GetMapping("/{userId}/followedProfiles")
    public ResponseEntity<?> getFollowedProfiles(@PathVariable("userId") long userId) {
        List<Profile> profiles = userService.getAllFollowedProfiles(userId);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

	@GetMapping("/{userId}/likedPosts")
    public ResponseEntity<?> getLikedPosts(@PathVariable("userId") long userId) {
        List<Post> likedPosts = userService.getAllLikedPosts(userId);
        return new ResponseEntity<>(likedPosts, HttpStatus.OK);
    }

	@GetMapping("/{userId}/followedTags")
    public ResponseEntity<?> getFollowedTags(@PathVariable("userId") long userId) {
        List<Hashtag> followedTags = userService.getAllFollowedTags(userId);
        return new ResponseEntity<>(followedTags, HttpStatus.OK);
    }
}