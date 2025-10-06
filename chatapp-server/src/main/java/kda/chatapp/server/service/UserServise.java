package kda.chatapp.server.service;

import kda.chatapp.server.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServise {

    public List<User> getListUSer(){
        return List.of(
                new User(1, "user1", "1", "USER"),
                new User(2, "user2", "2", "USER"),
                new User(3, "user3", "3", "USER")
        );
    }

    public User findById(int id){
        return getListUSer().stream().filter(u -> u.getId()==id).findFirst().orElse(null);
    }

}
