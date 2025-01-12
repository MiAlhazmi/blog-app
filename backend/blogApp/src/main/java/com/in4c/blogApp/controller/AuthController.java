package com.in4c.blogApp.controller;

import com.in4c.blogApp.model.helper.LoginRequest;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.model.helper.ResultWrapper;
import com.in4c.blogApp.model.helper.SignupRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in4c.blogApp.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // tells the backend to accept api calls from http://localhost:4200
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest request) {
		Result<?> res = userService.login(request);
		return res.isSuccess()
				? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
				: new ResponseEntity<>(res.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<?> userSignup(@RequestBody SignupRequest request) {
		Result<?> res = userService.signUp(request);
		return res.isSuccess()
				? new ResponseEntity<>(new ResultWrapper<>(res.getData(), res.getMessage()), HttpStatus.OK)
				: new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
	}
}