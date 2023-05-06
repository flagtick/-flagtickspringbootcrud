/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flagtick.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagtick.entity.User;
import com.flagtick.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
		User existedUser = userRepository.findOne(userId);
		if(existedUser != null) {
			existedUser.setName(user.getName());
			userRepository.save(existedUser);
			return new ResponseEntity<>(existedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
		User existedUser = userRepository.findOne(userId);
		if (null != existedUser) {
			userRepository.delete(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public ResponseEntity<String> getAllUsers() {
		List<User> users = userRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(users);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
