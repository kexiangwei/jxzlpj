package com.mycode.jiaoxuesheji.kcjxdg;

import com.mycode.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.jiaoxuesheji.kcjxdg.service.KcjxdgService;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import com.mycode.shaungchuangjiaoyu.wtbs.service.WtbsService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
@CrossOrigin
@Controller
@RequestMapping("/jxsj_kcjxdg")
public class KcjxdgController {

    @Autowired
    private KcjxdgService kcjxdgService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Kcjxdg kcjxdg){
        Map<String, Object> resultMap = kcjxdgService.getPageList(kcjxdg);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Kcjxdg kcjxdg){
        boolean bool = kcjxdgService.insert(kcjxdg);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Kcjxdg kcjxdg){
        boolean bool = kcjxdgService.update(kcjxdg);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = kcjxdgService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
