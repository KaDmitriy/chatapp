package kda.chatapp.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class Page {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/chat")
    public String getChat() {
        return "chat";
    }

}
