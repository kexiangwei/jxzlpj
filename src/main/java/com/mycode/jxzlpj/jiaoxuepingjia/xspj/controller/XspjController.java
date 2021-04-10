package com.mycode.jxzlpj.jiaoxuepingjia.xspj.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.BjpjParams;
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
     * @param xspj [courseCode，templateCode，userId，userName]
     * @param jsonString 指标项
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Xspj xspj, @RequestParam("jsonString") String jsonString){
        boolean bool = xspjService.insert(xspj, jsonString);
        if(!bool){
            return JsonResult.error("新增失败！");
        }
        return JsonResult.success("新增成功！",null);
    }

    /**
     *
     * @param courseCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPjInfo.do")
    public JsonResult<Object> getPjInfo(@RequestParam("courseCode") String courseCode, @RequestParam("userId") String userId){
        Map<String,Object> resultMap = xspjService.getPjInfo(courseCode,userId);
        return JsonResult.success(resultMap);
    }

    /**
     * 学生评教-比较评价-穿梭框初始值（根据userId 获取本学期课程及授课教师信息）
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPjInfoTransferData.do")
    public JsonResult<Object> getPjInfoTransferData(@RequestParam("userId") String userId){
        List<Map<String,Object>> mapList = xspjService.getPjInfoTransferData(userId);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @RequestMapping("/insertBjpj.do")
    public JsonResult<Object> insertBjpj(BjpjParams params){
        boolean bool = xspjService.insertBjpj(params);
        if(!bool){
            return JsonResult.error("新增失败！");
        }
        return JsonResult.success("新增成功！",null);
    }

    @ResponseBody
    @RequestMapping("/insertBjpjSuggest.do")
    public JsonResult<Object> insertBjpjSuggest(@RequestParam("relationCode") String relationCode
            , @RequestParam("courseCode") String courseCode
            , @RequestParam("suggest") String suggest){
        boolean bool = xspjService.insertBjpjSuggest(relationCode, courseCode, suggest);
        if(!bool){
            return JsonResult.error("新增失败！");
        }
        return JsonResult.success("新增成功！",null);
    }

    @ResponseBody
    @RequestMapping("/selectBjpjSuggest.do")
    public JsonResult<Object> selectBjpjSuggest(@RequestParam("relationCode") String relationCode
            , @RequestParam("courseCode") String courseCode){
        String suggest = xspjService.selectBjpjSuggest(relationCode, courseCode);
        return JsonResult.success(suggest);
    }

    @ResponseBody
    @RequestMapping("/getBjpjPageList.do")
    public JsonResult<Object> getBjpjPageList(Xspj xspj){
        Map<String,Object> resultMap = xspjService.getBjpjPageList(xspj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getBjpjPjInfo.do")
    public JsonResult<Object> getBjpjPjInfo(@RequestParam("courseCode") String courseCode){
        Map<String,Object> resultMap = xspjService.getBjpjPjInfo(courseCode);
        return JsonResult.success(resultMap);
    }
}
