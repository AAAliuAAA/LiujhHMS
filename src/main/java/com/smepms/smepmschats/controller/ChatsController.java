package com.smepms.smepmschats.controller;


import com.smepms.smepmschats.websocket.handler.IndexWebsocketHandler;
import com.smepms.smepmschats.websocket.consts.SysMsgConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/Smepmschats")
@Controller
public class ChatsController {
  @Autowired
  IndexWebsocketHandler indexWebsocketHandler;

  @RequestMapping(value = "/sendToall",method = RequestMethod.GET)
  @ResponseBody
  public String send(HttpServletRequest request){
    indexWebsocketHandler.sendMessageToAllUser(new TextMessage(SysMsgConsts.SYSTEMINFO+"测试，当访问这个url时，会向所有用户发送信息"));
    return null;
  }
}
