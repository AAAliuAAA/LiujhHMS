package com.smepms.jobmanagement.service.impl;

import com.smepms.jobmanagement.mapper.PositionMapper;
import com.smepms.jobmanagement.pojo.Position;
import com.smepms.jobmanagement.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
@Service
public class PositionServiceImpl implements PositionService{
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> queryAllPositions() {
        return  positionMapper.selectByExample(null);
    }

}
