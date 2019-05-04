package com.smepms.smepmschats.configure;

import com.smepms.smepmschats.websocket.handler.IndexWebsocketHandler;
import com.smepms.smepmschats.websocket.websocketinterceptor.HandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    //注册websocket
    String websocket_url = "/websocket/chats.do";
    webSocketHandlerRegistry.addHandler(webSocketHandler(),websocket_url).addInterceptors(new HandshakeInterceptor());
  }
  @Bean
  public TextWebSocketHandler webSocketHandler(){
    return new IndexWebsocketHandler();
  }

}
