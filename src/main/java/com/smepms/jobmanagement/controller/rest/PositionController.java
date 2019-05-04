package com.smepms.jobmanagement.controller.rest;

import com.smepms.jobmanagement.pojo.Position;
import com.smepms.jobmanagement.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
@Controller
@RequestMapping("/Position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Position> queryAllPostion(){
        return positionService.queryAllPositions();
    }
}
