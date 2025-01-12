package com.in4c.blogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in4c.blogApp.model.CommentContent;
import com.in4c.blogApp.model.Profile;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.ResultWrapper;
import com.in4c.blogApp.service.CommentContentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/comment")
public class CommentContentController {
	@Autowired
	private CommentContentService commentService;

	@GetMapping("/get")
	public ResponseEntity<?> createComment() {
		List<CommentContent> list = commentService.getComments();
		return new  ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/{postId}/create/{userId}")
	public ResponseEntity<?> createComment(@RequestBody CommentContent comment, @PathVariable("userId") long userId,
			@PathVariable("postId") long postId) {
		Result<?> res = commentService.createComment(comment, postId, userId);
		return res.isSuccess()
				? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
				: new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{commentId}/{userId}/{postId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId,
			@PathVariable("userId") long userId,
			@PathVariable("postId") long postId) {
		Result<?> res = commentService.deleteComment(commentId, postId, userId);
		return res.isSuccess()
				? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
				: new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
