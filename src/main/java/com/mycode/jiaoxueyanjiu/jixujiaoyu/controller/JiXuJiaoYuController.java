package com.mycode.jiaoxueyanjiu.jixujiaoyu.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.service.JiXuJiaoYuService;
import com.mycode.common.shenhe.domain.ShenHeItem;
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
 * 教学研究-继续教育
 */
@CrossOrigin
@Controller
@RequestMapping("/jiXuJiaoYu")
public class JiXuJiaoYuController {

    @Autowired
    private JiXuJiaoYuService jiXuJiaoYuService;
    @Autowired
    private ShenHeService shenHeService;

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

    /**
     * 提交,支持批量提交
     * @param menuId
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId,@RequestParam("jsonStr") String jsonStr){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        List<JiXuJiaoYu> jiXuJiaoYuList = JSON.parseArray(jsonStr, JiXuJiaoYu.class);
        boolean bool = jiXuJiaoYuService.toSubimt(activeShenheCode,jiXuJiaoYuList);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功",null);
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item,@RequestParam("jsonStr") String jsonStr){
        List<JiXuJiaoYu> jiXuJiaoYuList = JSON.parseArray(jsonStr, JiXuJiaoYu.class);
        boolean bool = jiXuJiaoYuService.toShenhe(item,jiXuJiaoYuList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }
}
