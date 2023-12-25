package com.example.websocketstudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsocketController {

    @GetMapping("/")
    public String init() {
        return "websocket";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chatRoom";
    }
}

