package com.smepms.smepmschats.websocket.websocketinterceptor;


import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 握手拦截器，为了了解到什么时候握手以及是否成功握手
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
  private final static Logger logger = Logger.getLogger(HandshakeInterceptor.class);


  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    //在握手发生之前
    logger.info("即将开始进行握手");
    return super.beforeHandshake(request,response,wsHandler,attributes);
  }

  @Override
  public void afterHandshake(ServerHttpRequest request,ServerHttpResponse response,WebSocketHandler wsHandler,Exception ex){
    //在握手发生之后
    logger.info("握手完成");
    super.afterHandshake(request,response,wsHandler,ex);
  }
}
