package com.mycode.jiaoxuesheji.kcjxdg;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.jiaoxuesheji.kcjxdg.service.KcjxdgService;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.service.JiaoCaiJianSheService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
@CrossOrigin
@RestController
@RequestMapping("/kcjxdg")
public class KcjxdgController {

    @Autowired
    private KcjxdgService kcjxdgService;
    @Autowired
    private ShenHeService shenHeService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Kcjxdg kcjxdg){
        Map<String, Object> resultMap = kcjxdgService.getPageList(kcjxdg);
        return JsonResult.success(resultMap);
    }

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Kcjxdg kcjxdg){
        boolean bool = kcjxdgService.insert(kcjxdg);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(Kcjxdg kcjxdg){
        boolean bool = kcjxdgService.update(kcjxdg);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = kcjxdgService.delete(code);
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
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId
            , @RequestParam("jsonStr") String jsonStr){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        List<Kcjxdg> kcjxdgList = JSON.parseArray(jsonStr, Kcjxdg.class);
        boolean bool = kcjxdgService.toSubimt(activeShenheCode,kcjxdgList);
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
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item
            , @RequestParam("jsonStr") String jsonStr){
        List<Kcjxdg> kcjxdgList = JSON.parseArray(jsonStr, Kcjxdg.class);
        boolean bool = kcjxdgService.toShenhe(item,kcjxdgList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }
}
