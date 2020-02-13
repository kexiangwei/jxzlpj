package com.mycode.jiaoxuejiangcheng.jxsg.controller;

import com.mycode.jiaoxuejiangcheng.jxsg.domian.Jxsg;
import com.mycode.jiaoxuejiangcheng.jxsg.service.JxsgService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-教学事故
 */
@CrossOrigin
@Controller
@RequestMapping("/jxsg")
public class JxsgController {

    @Autowired
    private JxsgService jxsgService;

    /**
     * 根据userId 判断该用户是否为教务处管理员
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/isAdmin.do")
    public JsonResult<Object> isAdmin(@RequestParam("userId") String userId){
        int isAdmin = jxsgService.isAdmin(userId);
        return JsonResult.success(isAdmin);
    }

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Jxsg obj){
        Map<String, Object> resultMap = jxsgService.getPageList(obj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Jxsg obj){
        boolean bool = jxsgService.insert(obj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Jxsg obj){
        boolean bool = jxsgService.update(obj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("objCode") String objCode){
        boolean bool = jxsgService.delete(objCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
