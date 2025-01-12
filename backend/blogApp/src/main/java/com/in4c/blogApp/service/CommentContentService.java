package com.in4c.blogApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in4c.blogApp.model.CommentContent;
import com.in4c.blogApp.model.User;
import com.in4c.blogApp.model.Post;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.repository.CommentContentRepository;
import com.in4c.blogApp.repository.UserRepository;
import com.in4c.blogApp.repository.PostRepository;

@Service
public class CommentContentService {

	@Autowired
	private CommentContentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;

	public List<CommentContent> getComments() {
		return commentRepo.findAll();
	}

	public Result<?> createComment(CommentContent comment, long postId, long userId) {

		if (comment.getContent() == null || comment.getContent().isEmpty()) {
			return new Result<>(false, null, "Error: Comment content not found");
		}

		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return new Result<>(false, null, "Error: User not found");
		}

		Optional<Post> post = postRepo.findById(postId);
		if (post.isEmpty()) {
			return new Result<>(false, null, "Error: Post not found");
		}
		boolean isCommentFoundById = false;
		if (post.get().getComments() != null) {
			isCommentFoundById = post.get().getComments().stream()
			.anyMatch(c -> c.getId() == comment.getId());
			
		}
		
		if (!isCommentFoundById) {
			post.get().getComments().add(comment);
			postRepo.save(post.get());
			comment.setUser(user.get());
			commentRepo.save(comment);
			return new Result<>(true, post.get(), "Success: Comment was added successfully!");
		}
		return new Result<>(false, null, "Error: same comment cannot br created twise!");
	}

	public Result<?> deleteComment(long commentId, long postId, long userId) {

		Optional<CommentContent> comment = commentRepo.findById(commentId);
		if (comment.isEmpty()) {
			return new Result<>(false, null, "Error: Comment not found");
		}

		if (comment.get().getUser().getId() != userId) {
			return new Result<>(false, null, "Error: Comment does not belong to user id: %s".formatted(userId));
		}

		Optional<Post> post = postRepo.findById(postId);
		if (post.isEmpty()) {
			return new Result<>(false, null, "Error: Post not found");
		}

		post.get().getComments().removeIf(c -> c.getId() == comment.get().getId());
		postRepo.save(post.get());

		commentRepo.deleteById(comment.get().getId());
		commentRepo.flush();
		return new Result<>(true, null, "Success: comment was deleted!");
	}

}
