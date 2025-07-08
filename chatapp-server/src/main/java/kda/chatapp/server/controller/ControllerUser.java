package kda.chatapp.server.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kda.chatapp.server.model.User;

@RestController
public class ControllerUser {

	@GetMapping("/user/list")
	public List<User> getListUser() {
		return List.of(
			new User(1, "user1"),
			new User(2, "user2"),
			new User(3, "user3")
		);
	}
	
	
}
