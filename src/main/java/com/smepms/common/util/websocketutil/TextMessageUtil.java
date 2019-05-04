package com.smepms.common.util.websocketutil;

import org.springframework.web.socket.TextMessage;

public class TextMessageUtil {
  public static TextMessage parseTextMessage(String msg){
    return new TextMessage(msg);
  }
}
