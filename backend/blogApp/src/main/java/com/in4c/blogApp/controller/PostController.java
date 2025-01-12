package com.in4c.blogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.ResultWrapper;
import com.in4c.blogApp.service.PostService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> getPosts(){
        List<Post> posts = postService.getPosts();
        // returning 200 for both empty and non-empty lists
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable("id") long userId){
        Result<?> res = postService.createPost(post, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@RequestBody Post updatedPost, @PathVariable("id") long postId){
        Result<?> res = postService.updatePost(postId, updatedPost);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
