package com.smepms.smepmschats.service;

import org.springframework.web.socket.TextMessage;

public interface EmployeeChatsService {
  public TextMessage receive();
}
