package com.mycode.jiaoxueyanjiu.jiaocaijianshe.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.service.JiaoCaiJianSheService;
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
 * 教学研究-教材建设
 * @auther kexiangwei
 * @date 2019/11/13
 */
@CrossOrigin
@Controller
@RequestMapping("/jiaoCaiJianShe")
public class JiaoCaiJianSheController {

    @Autowired
    private JiaoCaiJianSheService jiaoCaiJianSheService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe){
        Map<String, Object> resultMap = jiaoCaiJianSheService.getPageList(jiaoCaiJianShe);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoCaiJianShe jiaoCaiJianShe){
        boolean bool = jiaoCaiJianSheService.insert(jiaoCaiJianShe);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoCaiJianShe jiaoCaiJianShe){
        boolean bool = jiaoCaiJianSheService.update(jiaoCaiJianShe);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoCaiJianSheService.delete(code);
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
        List<JiaoCaiJianShe> jiaoCaiJianSheList = JSON.parseArray(jsonStr, JiaoCaiJianShe.class);
        boolean bool = jiaoCaiJianSheService.toSubimt(activeShenheCode,jiaoCaiJianSheList);
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
        List<JiaoCaiJianShe> jiaoCaiJianSheList = JSON.parseArray(jsonStr, JiaoCaiJianShe.class);
        boolean bool = jiaoCaiJianSheService.toShenhe(item,jiaoCaiJianSheList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
