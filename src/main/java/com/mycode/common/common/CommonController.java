package com.mycode.common.common;

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
 * 教学质量评价-通用接口
 */
@CrossOrigin
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping("/getTableCols.do")
    public JsonResult<Object> getTableCols(@RequestParam("tableName") String tableName){
        List<Map<String, Object>> tableCols = commonService.getTableCols(tableName);
        return JsonResult.success(tableCols);
    }

    @ResponseBody
    @RequestMapping("/getTableDatas.do")
    public JsonResult<Object> getTeacherTabData(@RequestParam("viewName") String viewName, @RequestParam("userId") String userId){
        List<Map<String, Object>> tableDatas = commonService.getTableDatas(viewName, userId);
        return JsonResult.success(tableDatas);
    }

    @ResponseBody
    @RequestMapping("/getCollege.do")
    public JsonResult<Object> getCollege(){
        List<Map<String, Object>> maps = commonService.getCollege();
        return JsonResult.success(maps);
    }

    @ResponseBody
    @RequestMapping("/getMajor.do")
    public JsonResult<Object> getMajor(@RequestParam(value = "collegeCode",required = false) String collegeCode){
        List<Map<String, Object>> maps = commonService.getMajor(collegeCode);
        return JsonResult.success(maps);
    }

    @ResponseBody
    @RequestMapping("/getCourseListByUserId.do")
    public JsonResult<Object> getCourseListByUserId(@RequestParam("userId") String userId
            ,@RequestParam(value = "accountType", required = false, defaultValue = "teacher") String accountType
            ,@RequestParam(value = "courseName", required = false) String courseName){
        List<Course> courseList = commonService.getCourseListByUserId(userId, accountType, courseName);
        return JsonResult.success(courseList);
    }

}
