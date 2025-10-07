package kda.chatapp.server.controller;

import kda.chatapp.server.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class Page {

    @Autowired
    UserServise userServise;

    @GetMapping("/")
    public String getIndex(Model model, Principal principal) {
        model.addAttribute("username", userServise.findByName(principal.getName()).getName());
        return "index";
    }

    @GetMapping("/info")
    public String getInfo(Model model, Principal principal) {
        model.addAttribute("username", userServise.findByName(principal.getName()).getName());
        return "info";
    }

    @GetMapping("/chatfirst")
    public String getChatFirst() {
        return "chatfirst";
    }

    @GetMapping("/chat")
    public String getChat() {
        return "chat";
    }

}
