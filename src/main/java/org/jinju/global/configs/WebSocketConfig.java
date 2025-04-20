package org.jinju.global.configs;

import org.jinju.message.websockets.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MessageHandler messageHandler;

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ws://localhost:8000/message
//        String profile = System.getenv("spring.profiles.active"); //운영체제의 환경 변수를 읽는 거

        registry.addHandler(messageHandler, "/msg")
                .setAllowedOrigins(profile.contains("prod") ? "https://togemory.jinilog.com" : "http://localhost:8000");
    }
}
