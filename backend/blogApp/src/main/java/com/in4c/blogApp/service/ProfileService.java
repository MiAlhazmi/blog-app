package com.in4c.blogApp.service;

import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.model.Profile;
import com.in4c.blogApp.model.User;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.repository.ProfileRepository;
import com.in4c.blogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRep;
    @Autowired
    private UserRepository userRepo;

    public Result<?> createProfile(Profile profile, long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return new Result<>(false, null, "Error: User not recognised");
        }
        if (profile.getUserTag() == null) {
            return new Result<>(false, null, "Error: Empty user tag content");
        }
        profile.setUser(user.get());
        profileRep.save(profile);
        return new Result<>(true, profile, "Success: Profile created!!");
    }

    public Result<?> updateProfile(Profile profile, long profileId) {
        Optional<Profile> _profile = profileRep.findById(profileId);
        if (_profile.isEmpty()) {
            return new Result<>(false, null, "Error: Profile not found");
        }
        Profile existed = _profile.get();
        if (profile.getUserTag() != null) {
            existed.setUserTag(profile.getUserTag());
        }

        if (profile.getInterests() != null) {
            existed.setInterests(profile.getInterests());
        }

        if (profile.getBio() != null) {
            existed.setBio(profile.getBio());
        }
        profileRep.save(existed);
        return new Result<>(true, existed, "Success: Profile updated!!");
    }

    public List<Profile> getProfiles() {
        return profileRep.findAll();
    }

    public Result<?> getProfileByUserId(long userId) {
        // TODO: replace this with custom sql query (yazeed)
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return new Result<>(false, null, "Error: User not recognised");
        }
        Optional<Profile> profile = profileRep.findAll()
                .stream().filter(p -> p.getUser().getId() == user.get().getId())
                .findAny();
        if (profile.isEmpty()) {
            return new Result<>(false, null, "Error: Profile not found");
        }
        return new Result<>(true, profile.get(), "Success: Profile found!!");
    }
}