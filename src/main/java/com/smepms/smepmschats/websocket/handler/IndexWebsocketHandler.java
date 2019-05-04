package com.smepms.smepmschats.websocket.handler;

import com.smepms.common.consts.EmployeeConstants;
import com.smepms.common.exception.BizRuntimeException;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.smepmschats.cache.ChatMessage;
import com.smepms.smepmschats.cache.EmployeeChats;
import com.smepms.smepmschats.websocket.consts.SysMsgConsts;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;

/**
 * websocket处理基类(单实例)
 * 每当客户端发送消息过来，都会由这个函数接收和处理
 * 需要注意这里是文本处理器，如果要进行文件上传处理等需要继承AbstractWebSocket?Handler
 *
 * 从业务角度考虑，如果要每一次都可以直接拿到登录的用户，可以考虑写一个aop来对当前登录的用户进行注入
 */
public class IndexWebsocketHandler extends TextWebSocketHandler{
  private final static Logger logger = Logger.getLogger(IndexWebsocketHandler.class);

  @Autowired
  private EmployeeChats employeeChatsSet;

  /**
   * 当链接建立成功时
   * @param session
   * @throws Exception
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    EmployeeWithBLOBs logEmployee = getLogEmployeeFromSession(session);
    //将当前用户添加到在线列表
    employeeChatsSet.add(logEmployee,session);
    logger.info("用户["+logEmployee.getEmployeeName()+"]加入聊天");
    sendMessageToAllUser(new TextMessage(SysMsgConsts.SYSTEMINFO+"用户["+logEmployee.getEmployeeName()+"]加入聊天"));
  }


  /**
   *获取前端发送的文本信息
   */
  @Override
  protected void  handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
    EmployeeWithBLOBs employeeWithBLOBs = getLogEmployeeFromSession(session);
    //在这里进行消息回传
    logger.info("前端发送过来的信息"+message.getPayload());
    //贴到浏览器的内容包括 时间 名字 头像路径
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setMsg(message.getPayload());
    chatMessage.setName(employeeWithBLOBs.getEmployeeName());
    chatMessage.setHeadSculpture(employeeWithBLOBs.getEmployeeHeadSculpture());
    chatMessage.setEmployeeWorkId(employeeWithBLOBs.getEmployeeWorkId());
    chatMessage.setNow(new Date());
    String params = JSONObject.fromObject(chatMessage).toString();
    sendMessageToAllUser(new TextMessage(params));
  }

  /**
   * 传输关闭时调用
   * @param session
   * @param status
   * @throws Exception
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
    EmployeeWithBLOBs logEmployee = (EmployeeWithBLOBs) session.getAttributes().get("logEmployee");
    employeeChatsSet.remove(logEmployee);
    logger.info("当前用户"+logEmployee.getEmployeeName()+"退出登录");
  }

  /**
   * 传输错误时调用
   * @param session
   * @param exception
   * @throws Exception
   */
  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception){
    if(session.isOpen()){
      try {
        session.sendMessage(new TextMessage(SysMsgConsts.SYSTEMINFO+"出现错误，链接关闭"));
        session.close();
      } catch (IOException e) {
        logger.error("出现错误，与服务器连接关闭",e);
        throw new BizRuntimeException("ws连接错误");
      }
    }

// exception.printStackTrace(); 可选，当用户关闭了当前浏览器窗口时，会触发websocket.close()方法，导致服务器获取异常
    EmployeeWithBLOBs logEmployee = (EmployeeWithBLOBs) session.getAttributes().get("logEmployee");
    employeeChatsSet.remove(logEmployee);
  }

  public void sendMessageToAllUser(TextMessage textMessage){
    for (WebSocketSession webSocketSession:employeeChatsSet.getAllWebSocketSession()){
      try {
        if (webSocketSession.isOpen()) {
          webSocketSession.sendMessage(textMessage);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void sendMessageToSingleUser(TextMessage textMessage,Integer employeeId){
    try {
      employeeChatsSet.getTargetSessionById(employeeId).sendMessage(textMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private EmployeeWithBLOBs getLogEmployeeFromSession(WebSocketSession session){
    EmployeeWithBLOBs logEmployee = (EmployeeWithBLOBs) session.getAttributes().get(EmployeeConstants.logEmployee);
    return logEmployee;
  }

}
