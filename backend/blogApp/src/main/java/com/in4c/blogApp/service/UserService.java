package com.in4c.blogApp.service;

import com.in4c.blogApp.model.Profile;
import com.in4c.blogApp.model.Role;
import com.in4c.blogApp.model.User;
import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.controller.ProfileController;
import com.in4c.blogApp.model.Hashtag;
import com.in4c.blogApp.model.helper.LoginRequest;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.SignupRequest;
import com.in4c.blogApp.model.helper.UserInfoResponse;

import com.in4c.blogApp.repository.PostRepository;
import com.in4c.blogApp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.in4c.blogApp.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProfileRepository profileRepo;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private HashtagService tagService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ProfileController profileController;

	public Result<?> signUp(SignupRequest request) {
		if (request.getUsername() == null || request.getPassword() == null) {
			return new Result<>(false, null, "Error: Missing username or password!");
		}
		if (userRepo.existsByUsername(request.getUsername())) {
			return new Result<>(false, null, "Error: Username is already taken!");
		}
		if (userRepo.existsByEmail(request.getEmail())) {
			return new Result<>(false, null, "Error: Email is already taken!");
		}

		User user = new User(0, request.getUsername(), request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				passwordEncoder.encode(request.getPassword()),
				request.getGender(),
				Role.USER_ROLE);
		userRepo.save(user);
		// Creating an empty profile after user is created to avoid any editing profile
		// issues.
		// note: userTag is initialized with username value to avoid userTag having a
		// null value
		profileController.createProfile(new Profile(0, user, user.getUsername(), "My interests", "My bio"),
				user.getId());
		return new Result<>(true, user, "Success: User was registered successfully!");
	}

	public Result<?> login(LoginRequest obj) {
		User user = userRepo.findByUsername(obj.getUsername());
		if (user == null) {
			return new Result<>(false, null, "Error: user not found!");
		}
		if (!passwordEncoder.matches(obj.getPassword(), user.getPassword())) {
			return new Result<>(false, null, "Error: password is wrong!");
		}
		String token = jwtService.generateToken(user);
		return new Result<>(true,
				new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), token),
				"Success");
	}

	public List<Profile> getAllFollowedProfiles(long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return Collections.emptyList();
		}
		return user.get().getFollowedProfiles();
	}

	public List<Post> getAllLikedPosts(long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return Collections.emptyList();
		}
		return user.get().getLikedPosts();
	}

	public List<Hashtag> getAllFollowedTags(long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return Collections.emptyList();
		}
		return user.get().getFollowedTags();
	}

	public Result<?> followProfile(long profileId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognised");
		}
		Optional<Profile> profile = profileRepo.findById(profileId);
		if (profile.isEmpty()) {
			return new Result<>(false, null, "Error: Profile not recognised");
		}
		// prevent multiple follow
		boolean isProfileFollowed = user.get().getFollowedProfiles().stream()
				.anyMatch(p -> p.getId() == profile.get().getId());

		if (!isProfileFollowed) {
			user.get().getFollowedProfiles().add(profile.get());
			userRepo.saveAndFlush(user.get());
			return new Result<>(true, null, "Success: Profile followed");
		}
		return new Result<>(false, null, "Error: Profile already followed");
	}

	public Result<?> unfollowProfile(long profileId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognized");
		}
		Optional<Profile> profile = profileRepo.findById(profileId);
		if (profile.isEmpty()) {
			return new Result<>(false, null, "Error: Profile not recognized");
		}

		boolean isProfileFollowed = user.get().getFollowedProfiles().stream()
				.anyMatch(p -> p.getId() == profile.get().getId());

		if (isProfileFollowed) {
			user.get().getFollowedProfiles().remove(profile.get());
			userRepo.saveAndFlush(user.get());
			return new Result<>(true, null, "Success: Profile unfollowed");
		}
		return new Result<>(false, null, "Error: Profile not followed");
	}

	public Result<?> likePost(long postId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognised");
		}
		Optional<Post> post = postRepo.findById(postId);
		if (post.isEmpty()) {
			return new Result<>(false, null, "Error: Post not recognised");
		}
		// prevent multiple likes
		boolean isLikedPost = user.get().getLikedPosts().stream()
				.anyMatch(p -> p.getId() == post.get().getId());

		if (!isLikedPost) {
			user.get().getLikedPosts().add(post.get());
			post.get().setLikes(post.get().getLikes() + 1);
			userRepo.saveAndFlush(user.get());
			postRepo.saveAndFlush(post.get());
			return new Result<>(true, null, "Success: Post liked");
		}
		return new Result<>(false, null, "Error: Post already Liked");
	}

	public Result<?> dislikePost(long postId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognized");
		}
		Optional<Post> post = postRepo.findById(postId);
		if (post.isEmpty()) {
			return new Result<>(false, null, "Error: Post not recognised");
		}
		boolean isLikedPost = user.get().getLikedPosts().stream()
				.anyMatch(p -> p.getId() == post.get().getId());

		if (isLikedPost) {
			user.get().getLikedPosts().remove(post.get());
			// this can be changed from list of integers to a single variable
			// that can be incremented/decremented.
			// TODO: figure this shit out
			post.get().setDislikes((post.get().getDislikes() + 1));
			userRepo.saveAndFlush(user.get());
			postRepo.saveAndFlush(post.get());
			return new Result<>(true, null, "Success: Post disliked");
		}
		return new Result<>(false, null, "Error: fuck!");
	}

	public Result<?> followHashtag(long tagId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognized");
		}
		Result<Hashtag> res = tagService.findById(tagId);
		if (!res.isSuccess()) {
			return new Result<>(false, null, "Error: hashtag not recognized");
		}

		boolean isFollowedHashTag = user.get().getFollowedTags().stream()
				.anyMatch(t -> t.getId() == res.getData().getId());

		if (!isFollowedHashTag) {
			user.get().getFollowedTags().add(res.getData());
			userRepo.saveAndFlush(user.get());
			return new Result<>(true, null, "Success: Hashtag followed");
		}
		return new Result<>(false, null, "Error: Hashtag not followed");
	}

	public Result<?> unfollowHashtag(long tagId, long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not recognized");
		}
		Result<Hashtag> res = tagService.findById(tagId);
		if (!res.isSuccess()) {
			return new Result<>(false, null, "Error: Hashtag not recognized");
		}

		boolean isFollowedHashtag = user.get().getFollowedTags().stream()
				.anyMatch(t -> t.getId() == res.getData().getId());

		if (isFollowedHashtag) {
			user.get().getFollowedTags().removeIf(t -> t.getId() == res.getData().getId());
			userRepo.saveAndFlush(user.get());
			return new Result<>(true, null, "Success: Hashtag unfollowed");
		}
		return new Result<>(false, null, "Error: Hashtag not followed");
	}
}