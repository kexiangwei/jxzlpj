package com.mycode.common.shenhe.controller;

import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class ShenHeController {

    @Autowired
    private ShenHeService shenHeService;

    /**
     * 根据relationCode 获取审核流程
     * @param relationCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShenheProcess.do")
    public JsonResult<Object> getShenheProcess(@RequestParam("relationCode") String relationCode){
        List<ShenHe> statusList = shenHeService.getShenheProcess(relationCode);
        return JsonResult.success(statusList);
    }
}
