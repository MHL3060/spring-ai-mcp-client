package com.example.demo;

import io.modelcontextprotocol.client.McpAsyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class AiAssistant {

    private final ChatClient chatClient;
    private final List<McpAsyncClient> mcpAsyncClientList;

    public AiAssistant(ChatClient.Builder chatClientBuilder, List<McpAsyncClient> mcpAsyncClientList) {

        this.mcpAsyncClientList = mcpAsyncClientList;

        AsyncMcpToolCallbackProvider asyncMcpToolCallbackProvider = new AsyncMcpToolCallbackProvider(mcpAsyncClientList);
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(asyncMcpToolCallbackProvider)
                .build();
    }

    public Flux<String> chat(String userMessage) {
        return chatClient.prompt()
                .user(userMessage)
                .stream()
                .content();
    }
}
