package kda.chatapp.server.service;

import kda.chatapp.server.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServise {

    private List<User> users;

    public List<User> getListUSer(){
        if(users == null || users.isEmpty()){
            users = List.of(
                        new User(1, "user1", "1", "ADMIN"),
                        new User(2, "user2", "2", "USER"),
                        new User(3, "user3", "3", "USER")
                    );
        }
        return users;

    }

    public User findById(int id){
        return getListUSer().stream().filter(u -> u.getId()==id).findFirst().orElse(null);
    }

    public User findByName(String username){
        return getListUSer().stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
    }

}
