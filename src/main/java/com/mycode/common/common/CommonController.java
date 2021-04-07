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
    @RequestMapping("/getXyList.do")
    public JsonResult<Object> getXyList(){
        List<Map<String, Object>> maps = commonService.getXyList();
        return JsonResult.success(maps);
    }

    @ResponseBody
    @RequestMapping("/getZyList.do")
    public JsonResult<Object> getZyList(@RequestParam(value = "xyCode",required = false) String xyCode){
        List<Map<String, Object>> maps = commonService.getZyList(xyCode);
        return JsonResult.success(maps);
    }

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

}
