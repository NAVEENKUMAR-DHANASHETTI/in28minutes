package com.naveen.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	private UserDaoServices userDaoServices;

	public UserResource(UserDaoServices userDaoServices) {
		this.userDaoServices = userDaoServices;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoServices.findAll();
		}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable Integer id) {
		return userDaoServices.finOne(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		
		User saved = userDaoServices.save(user);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(saved.getId())	
							.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
