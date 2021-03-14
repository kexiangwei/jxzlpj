package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenheSet.domain.ShenHeItem;
import com.mycode.common.shenheSet.service.ShenHeService;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service.JiaoXueTuanDuiService;
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
 * 教学研究-教学团队
 */
@CrossOrigin
@Controller
@RequestMapping("/jiaoXueTuanDui")
public class JiaoXueTuanDuiController {

    @Autowired
    private JiaoXueTuanDuiService jiaoXueTuanDuiService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui){
        Map<String, Object> resultMap = jiaoXueTuanDuiService.getPageList(jiaoXueTuanDui);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoXueTuanDui jiaoXueTuanDui){
        boolean bool = jiaoXueTuanDuiService.insert(jiaoXueTuanDui);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoXueTuanDui jiaoXueTuanDui){
        boolean bool = jiaoXueTuanDuiService.update(jiaoXueTuanDui);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoXueTuanDuiService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
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
        List<JiaoXueTuanDui> jiaoXueTuanDuiList = JSON.parseArray(jsonStr, JiaoXueTuanDui.class);
        boolean bool = jiaoXueTuanDuiService.toSubimt(activeShenheCode,jiaoXueTuanDuiList);
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
        List<JiaoXueTuanDui> jiaoXueTuanDuiList = JSON.parseArray(jsonStr, JiaoXueTuanDui.class);
        boolean bool = jiaoXueTuanDuiService.toShenhe(item,jiaoXueTuanDuiList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }

    @ResponseBody
    @RequestMapping("/getPingShenTemplate.do")
    public JsonResult<Object> getPingShenTemplate(){
        List<PingShenTemplate> pingShenTemplate = jiaoXueTuanDuiService.getPingShenTemplate();
        return JsonResult.success(pingShenTemplate);
    }

    @ResponseBody
    @RequestMapping("/getPingShenInfo.do")
    public JsonResult<Object> getPingShen(@RequestParam("relationCode") String relationCode,@RequestParam("batchNum") Integer batchNum
            ,@RequestParam(value = "userId",required = false) String userId){
        List<PingShen> pingShenInfo = jiaoXueTuanDuiService.getPingShenInfo(relationCode, batchNum, userId);
        return JsonResult.success(pingShenInfo);
    }

    @ResponseBody
    @RequestMapping("/insertPingShenInfo.do")
    public JsonResult<Object> insertPingShenInfo(PingShen pingShen){
        boolean bool = jiaoXueTuanDuiService.insertPingShenInfo(pingShen);
        if(!bool){
            return JsonResult.error("保存失败");
        }
        return JsonResult.success("保存成功",null);
    }
}
