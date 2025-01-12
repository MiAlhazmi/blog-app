package com.in4c.blogApp.controller;

import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.model.Profile;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.ResultWrapper;
import com.in4c.blogApp.service.PostService;
import com.in4c.blogApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/get")
    public ResponseEntity<?> getProfiles(){
        List<Profile> profiles = profileService.getProfiles();
        // returning 200 for both empty and non-empty lists
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createProfile(@RequestBody Profile profile, @PathVariable("id") long userId){
        Result<?> res = profileService.createProfile(profile, userId);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile, @PathVariable("id") long id){
        Result<?> res = profileService.updateProfile(profile, id);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProfile(@PathVariable("id") long id) {
        Result<?> res = profileService.getProfileByUserId(id);
        return res.isSuccess()
                ? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
    }
}