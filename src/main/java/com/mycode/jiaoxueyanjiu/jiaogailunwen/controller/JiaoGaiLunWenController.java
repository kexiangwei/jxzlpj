package com.mycode.jiaoxueyanjiu.jiaogailunwen.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.service.JiaoGaiLunWenService;
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
 * 教学研究-教改论文
 * @auther kexiangwei
 * @date 2019/7/13
 */
@CrossOrigin
@Controller
@RequestMapping("/jiaoGaiLunWen")
public class JiaoGaiLunWenController {

    @Autowired
    private JiaoGaiLunWenService jiaoGaiLunWenService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen){
        Map<String, Object> resultMap = jiaoGaiLunWenService.getPageList(jiaoGaiLunWen);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/get.do")
    public JsonResult<Object> get(JiaoGaiLunWen jiaoGaiLunWen){
        jiaoGaiLunWen = jiaoGaiLunWenService.get(jiaoGaiLunWen);
        return JsonResult.success(jiaoGaiLunWen);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiLunWen jiaoGaiLunWen){
        boolean bool = jiaoGaiLunWenService.insert(jiaoGaiLunWen);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiLunWen jiaoGaiLunWen){
        boolean bool = jiaoGaiLunWenService.update(jiaoGaiLunWen);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiLunWenService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 提交，支持批量提交
     * @param menuId 用于获取当前处于激活状态的审核流程编号
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
        List<JiaoGaiLunWen> jiaoGaiLunWenList = JSON.parseArray(jsonStr, JiaoGaiLunWen.class);
        boolean bool = jiaoGaiLunWenService.toSubimt(activeShenheCode,jiaoGaiLunWenList);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success();
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item,@RequestParam("jsonStr") String jsonStr){
        List<JiaoGaiLunWen> jiaoGaiLunWenList = JSON.parseArray(jsonStr, JiaoGaiLunWen.class);
        boolean bool = jiaoGaiLunWenService.toShenhe(item,jiaoGaiLunWenList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
