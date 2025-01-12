package com.in4c.blogApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in4c.blogApp.model.*;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.repository.BlogContentRepository;
import com.in4c.blogApp.repository.PostRepository;
import com.in4c.blogApp.repository.UserRepository;

@Service
public class PostService {
    @Autowired
    private BlogContentRepository contentRepository;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private HashtagService tagService;

    @Autowired
    private PostRepository postRepo;

    public Result<?> createPost(Post post, long userId) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty()) {
            return new Result<>(false, null, "Error: User not recognised");
        }

        if (post.getContent().getContent() == null) {
            return new Result<>(false, null, "Error: Empty post content");
        }

        if (post.getContent().getSubject() == null) {
            return new Result<>(false, null, "Error: Empty post subject");
        }
        post.setUser(user.get());
        // if (post.getHashTags() != null) {
        //     for (Hashtag t : post.getHashTags()) {
        //         Result<Hashtag> res = tagService.createHashTag(t);
        //         if (!res.isSuccess()) {
        //             return new Result<>(res.isSuccess(), null, res.getMessage());
        //         }
        //         if (res.getData() != null) {
        //             t = res.getData();
        //         }
        //     }
        // }
        contentRepository.save(post.getContent());
        postRepo.save(post);
        return new Result<>(true, post, "Success: Post created!!");
    }

    public Result<?> updatePost(long postId, Post updatedPost) {
        Optional<Post> _post = postRepo.findById(postId);
        if (_post.isEmpty()) {
            return new Result<>(false, null, "Error: Post not found");
        }
        Post post = _post.get();
        if (updatedPost.getContent().getContent() != null) {
            post.getContent().setContent(updatedPost.getContent().getContent());
        }
        if (updatedPost.getContent().getSubject() != null) {
            post.getContent().setSubject(updatedPost.getContent().getSubject());
        }
        if (updatedPost.getHashTags() != null) {
            List<Hashtag> updatedHashtags = updatedPost.getHashTags();
            updatedHashtags.stream()
                    .filter(t -> t.getTag() != null)
                    .forEach(t -> {
                        post.getHashTags().removeIf(
                                existingHashtag -> existingHashtag.getId() == t.getId());
                        post.getHashTags().add(t);
                    });
        }
        contentRepository.save(post.getContent());
        postRepo.save(post);
        return new Result<>(true, post, "Success: Post updated!!");
    }

    // TODO: create method to handle hashtag update, the above implementation isn't
    // sufficient
    public Result<?> updatePostHashtags(long postId, Post updatedPost) {
        return null;
    }

    public List<Post> getPosts() {
        return postRepo.findAll();
    }

    public boolean doesPostExistById(long postId) {
        return postRepo.findById(postId).isPresent();
    }

    public boolean doesPostExistByUserId(long userId) {
        // performance wise, this is bad because it iterates over all posts.
        // TODO: (Yazeed) write custom query to handle this
        return postRepo.findAll().stream()
                .anyMatch(p -> p.getUser().getId() == userId);
    }

    public Post findPostById(long postId) {
        return postRepo.findById(postId).isPresent()
                ? postRepo.findById(postId).get()
                : null;
    }

    public List<Post> findPostByUserId(long userId) {
        // performance wise, this is bad because it iterates over all posts.
        // TODO: (Yazeed) write custom query to handle this
        return postRepo.findAll().stream()
                .filter(p -> p.getUser().getId() == userId).collect(Collectors.toList());
    }
}