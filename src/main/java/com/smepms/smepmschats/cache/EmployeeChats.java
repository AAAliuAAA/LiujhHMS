package com.smepms.smepmschats.cache;

import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/**
 * 存放当前大厅中在线的所有用户的缓存，包括用户信息和websocket会话
 */
@Component
public class EmployeeChats {
  @Autowired
  private EmployeeService employeeService;

  private Map<Integer, WebSocketSession> socketSessionMap = new HashMap<Integer, WebSocketSession>();
  private Set<EmployeeWithBLOBs> employeeChatsOnlineSet = new HashSet<EmployeeWithBLOBs>();
  private Integer totalOnlineEmployee;

  public void add(EmployeeWithBLOBs logEmployee,WebSocketSession webSocketSession) {
    if (this.employeeChatsOnlineSet.contains(logEmployee)) {
      return;
    }
    this.employeeChatsOnlineSet.add(logEmployee);
    this.totalOnlineEmployee = this.employeeChatsOnlineSet.size();
    socketSessionMap.put(logEmployee.getEmployeeId(),webSocketSession);
  }

  public void remove(EmployeeWithBLOBs logEmployee) {
    this.employeeChatsOnlineSet.remove(logEmployee);
    this.totalOnlineEmployee = this.employeeChatsOnlineSet.size();
    socketSessionMap.remove(logEmployee.getEmployeeId());
  }

  public EmployeeWithBLOBs getOnlineEmployeeById(Integer employeeId) {
    for (EmployeeWithBLOBs employeeWithBLOBs : this.employeeChatsOnlineSet) {
      if (employeeWithBLOBs.getEmployeeId().equals(employeeId)) {
        return employeeWithBLOBs;
      }
    }
    return null;
  }
  public List<WebSocketSession> getAllWebSocketSession(){
    List<WebSocketSession> list = new ArrayList<WebSocketSession>();
    Set<Integer> employeeIds = socketSessionMap.keySet();
    for(Integer id:employeeIds){
      list.add(socketSessionMap.get(id));
    }
    return list;
  }

  public WebSocketSession getTargetSessionById(Integer employeeId){
    return this.socketSessionMap.get(employeeId);
  }

}
