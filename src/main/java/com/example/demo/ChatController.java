package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final AiAssistant aiAssistant;

    public ChatController(AiAssistant aiAssistant) {
        this.aiAssistant = aiAssistant;
    }

    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody String message) {

        return aiAssistant.chat(message);
    }
}
