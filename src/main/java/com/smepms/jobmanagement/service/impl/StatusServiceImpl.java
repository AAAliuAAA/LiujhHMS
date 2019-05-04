package com.smepms.jobmanagement.service.impl;

import com.smepms.jobmanagement.mapper.StatusMapper;
import com.smepms.jobmanagement.pojo.Status;
import com.smepms.jobmanagement.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusMapper statusMapper;

    public List<Status> queryAllStatuses() {
        List<Status> list = statusMapper.selectByExample(null);
        return list;
    }
}
