package com.smepms.jobmanagement.service;

import com.smepms.jobmanagement.mapper.StatusMapper;
import com.smepms.jobmanagement.pojo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
public interface StatusService {
    public List<Status> queryAllStatuses();
}
