package kda.chatapp.server.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import kda.chatapp.server.dto.UserInfo;
import kda.chatapp.server.model.ChatModel;
import kda.chatapp.server.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kda.chatapp.server.model.User;

@RestController
public class ControllerUser {

	@Autowired
	private UserServise userServise;

	@GetMapping("/user/list")
	public List<User> getListUser() {
		return userServise.getListUSer();
	}

	@GetMapping("/user/chat")
	public ChatModel getChat(int fromUserID, int toUserID) {
		ChatModel cm = new ChatModel();
		if((fromUserID==1&&toUserID==2) || (fromUserID==2&&toUserID==1) ){
			cm.setUid(UUID.fromString("1ee290eb-6fa9-40c3-bc87-d196ac028b31"));
			cm.setFromUSer(userServise.findById(fromUserID));
			cm.setToUSer(userServise.findById(toUserID));
		}
		if((fromUserID==1&&toUserID==3) || (fromUserID==3&&toUserID==1) ){
			cm.setUid(UUID.fromString("2ee290eb-6fa9-40c3-bc87-d196ac028b31"));
			cm.setFromUSer(userServise.findById(fromUserID));
			cm.setToUSer(userServise.findById(toUserID));
		}
		if((fromUserID==3&&toUserID==2) || (fromUserID==2&&toUserID==3) ){
			cm.setUid(UUID.fromString("3ee290eb-6fa9-40c3-bc87-d196ac028b31"));
			cm.setFromUSer(userServise.findById(fromUserID));
			cm.setToUSer(userServise.findById(toUserID));
		}
		return cm;
	}

	@GetMapping("/user/info")
	public UserInfo getUserInfo(Principal principal) {
		User curentUser = userServise.findByName(principal.getName());
		return new UserInfo(curentUser.getId(), curentUser.getName());
	}

}
