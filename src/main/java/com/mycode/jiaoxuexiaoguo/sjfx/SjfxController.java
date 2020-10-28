package com.mycode.jiaoxuexiaoguo.sjfx;

import com.alibaba.fastjson.JSON;
import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.commonset.shenheSet.service.ShenHeService;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import com.mycode.jiaoxuexiaoguo.sjfx.service.SjfxService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/sjfx")
public class SjfxController {

    @Autowired
    private SjfxService sjfxService;
    @Autowired
    private ShenHeService shenHeService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Sjfx sjfx){
        Map<String, Object> resultMap = sjfxService.getPageList(sjfx);
        return JsonResult.success(resultMap);
    }

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Sjfx sjfx){
        boolean bool = sjfxService.insert(sjfx);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(Sjfx sjfx){
        boolean bool = sjfxService.update(sjfx);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = sjfxService.delete(code);
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
        List<Sjfx> sjfxList = JSON.parseArray(jsonStr, Sjfx.class);
        boolean bool = sjfxService.toSubimt(activeShenheCode,sjfxList);
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
        List<Sjfx> sjfxList = JSON.parseArray(jsonStr, Sjfx.class);
        boolean bool = sjfxService.toShenhe(item,sjfxList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }
}
