package com.mycode.jiaoxuepingjia.xspj.controller;

import com.mycode.jiaoxuepingjia.xspj.domain.Xspj;
import com.mycode.jiaoxuepingjia.xspj.service.XspjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 */
@CrossOrigin
@Controller
@RequestMapping("/xspj")
public class XspjController {

    @Autowired
    private XspjService xspjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Xspj xspj){
        Map<String,Object> resultMap = xspjService.getPageList(xspj);
        return JsonResult.success(resultMap);
    }

}
