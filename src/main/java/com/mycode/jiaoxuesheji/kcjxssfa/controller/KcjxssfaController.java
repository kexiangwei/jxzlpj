package com.mycode.jiaoxuesheji.kcjxssfa.controller;

import com.mycode.jiaoxuesheji.kcjxssfa.domian.Course;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;
import com.mycode.jiaoxuesheji.kcjxssfa.service.KcjxssfaService;
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
 * 教学设计-课程教学实施方案
 */
@CrossOrigin
@Controller
@RequestMapping("/jxsj_kcjxssfa")
public class KcjxssfaController {

    @Autowired
    private KcjxssfaService kcjxssfaService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Kcjxssfa kcjxssfa){
        Map<String, Object> resultMap = kcjxssfaService.getPageList(kcjxssfa);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getItemListByRelationCode.do")
    public JsonResult<Object> getItemListByRelationCode(@RequestParam("relationCode") String relationCode){
        List<KcjxssfaItem> itemList = kcjxssfaService.getItemListByRelationCode(relationCode);
        return JsonResult.success(itemList);
    }

    @ResponseBody
    @RequestMapping("/getCourseListByUserId.do")
    public JsonResult<Object> getCourseListByUserId(@RequestParam("userId") String userId){
        List<Course> courseList = kcjxssfaService.getCourseListByUserId(userId);
        return JsonResult.success(courseList);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(KcjxssfaItem item){
        boolean bool = kcjxssfaService.insert(item);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    /*@ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Kcjxssfa kcjxssfa){
        boolean bool = kcjxssfaService.update(kcjxssfa);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = kcjxssfaService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }*/

}
