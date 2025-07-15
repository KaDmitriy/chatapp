package kda.chatapp.server.controller;

import java.util.List;
import java.util.UUID;

import kda.chatapp.server.model.ChatModel;
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

	@GetMapping("/user/chat")
	public ChatModel getChat(int fromUserID, int toUserID) {
		ChatModel cm = new ChatModel();
		cm.setUid(UUID.fromString("8ee290eb-6fa9-40c3-bc87-d196ac028b31"));
		cm.setFromUSer(new User(fromUserID, "user"+fromUserID));
		cm.setToUSer(new User(toUserID, "user"+toUserID));
		return cm;
	}

}
