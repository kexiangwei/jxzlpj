package com.mycode.jiaoxueyanjiu.jixujiaoyu;

import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.service.JiXuJiaoYuService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-继续教育
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jxjy")
public class JiXuJiaoYuController {

    @Autowired
    private JiXuJiaoYuService jiXuJiaoYuService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiXuJiaoYu jiXuJiaoYu){
        Map<String, Object> resultMap = jiXuJiaoYuService.getPageList(jiXuJiaoYu);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiXuJiaoYu jiXuJiaoYu){
        boolean bool = jiXuJiaoYuService.insert(jiXuJiaoYu);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiXuJiaoYu jiXuJiaoYu){
        boolean bool = jiXuJiaoYuService.update(jiXuJiaoYu);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiXuJiaoYuService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
