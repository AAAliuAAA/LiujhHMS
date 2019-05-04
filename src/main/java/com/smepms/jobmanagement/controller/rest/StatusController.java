package com.smepms.jobmanagement.controller.rest;

import com.smepms.jobmanagement.pojo.Status;
import com.smepms.jobmanagement.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
@Controller
@RequestMapping(("/Status"))
public class StatusController {
    @Autowired
    private StatusService statusService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Status> queryAllStatuses(){
           return statusService.queryAllStatuses();
    }
}
