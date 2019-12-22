package com.mycode.jiaoxuesheji.jxdg.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxuesheji.jxdg.domain.Course;
import com.mycode.jiaoxuesheji.jxdg.service.JxdgService;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
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

/**
 * 教学设计-教学大纲
 * @auther kexiangwei
 * @date 2019/10/8
 */
@CrossOrigin
@Controller
@RequestMapping("/jxdg")
public class JxdgController {

    @Autowired
    private JxdgService jxdgService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getCourseList.do")
    public JsonResult<Object> getCourseList(Course course){
        Map<String, Object> resultMap = jxdgService.getCourseList(course);
        return JsonResult.success(resultMap);
    }

    /**
     * 提交，支持批量提交
     * @param menuId 用于获取当前处于激活状态的审核流程编号
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId, @RequestParam("jsonStr") String jsonStr){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        List<Course> courseList = JSON.parseArray(jsonStr, Course.class);
        boolean bool = jxdgService.toSubimt(activeShenheCode,courseList);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功");
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item, @RequestParam("jsonStr") String jsonStr){
        List<Course> courseList = JSON.parseArray(jsonStr, Course.class);
        boolean bool = jxdgService.toShenhe(item,courseList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
