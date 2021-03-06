package com.mycode.jxzlpj.jiaoxuepingjia.xspj.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.Xspj;
import com.mycode.jxzlpj.jiaoxuepingjia.xspj.service.XspjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     *
     * @param xspj [xn, xq, courseCode，templateCode，userId，userName]
     * @param jsonString 指标项
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Xspj xspj, @RequestParam("jsonString") String jsonString){
        boolean bool = xspjService.insert(xspj, jsonString);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    /**
     *  教师查看学生评教
     * @param courseCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPjInfo.do")
    public JsonResult<Object> getPjInfo(@RequestParam("xn") String xn, @RequestParam("xq") String xq
            , @RequestParam("courseCode") String courseCode, @RequestParam("teacherCode") String teacherCode){
        Map<String,Object> resultMap = xspjService.getPjInfo(xn,xq,courseCode,teacherCode);
        return JsonResult.success(resultMap);
    }

    /**
     * 学生评教-比较评价-穿梭框初始值（根据userId 获取本学期课程及授课教师信息）
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBjpjTransferData.do")
    public JsonResult<Object> getBjpjTransferData(@RequestParam("userId") String userId){
        List<Map<String,Object>> mapList = xspjService.getBjpjTransferData(userId);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @RequestMapping("/insertBjpj.do")
    public JsonResult<Object> insertBjpj(Xspj xspj){
        boolean bool = xspjService.insertBjpj(xspj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/getBjpjPjSuggestList.do")
    public JsonResult<Object> getBjpjPjSuggestList(@RequestParam("userId") String userId, @RequestParam("templateCode") String templateCode){
        List<Map<String,Object>> mapList = xspjService.getBjpjPjSuggestList(userId,templateCode);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @RequestMapping("/getBjpjPageList.do")
    public JsonResult<Object> getBjpjPageList(Xspj xspj){
        Map<String,Object> resultMap = xspjService.getBjpjPageList(xspj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getBjpjPjInfo.do")
    public JsonResult<Object> getBjpjPjInfo(@RequestParam("xn") String xn, @RequestParam("xq") String xq
            , @RequestParam("courseCode") String courseCode, @RequestParam("teacherCode") String teacherCode){
        Map<String,Object> resultMap = xspjService.getBjpjPjInfo(xn,xq,courseCode,teacherCode);
        return JsonResult.success(resultMap);
    }
}
