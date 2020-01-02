package com.mycode.jiaoxuexiaoguo.sjfx.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import com.mycode.jiaoxuexiaoguo.sjfx.service.SjfxService;
import com.mycode.util.ExcelUtil;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 教学效果-试卷分析
 * @auther kexiangwei
 * @date 2019/11/13
 */
@CrossOrigin
@Controller
@RequestMapping("/sjfx")
public class SjfxController {

    @Autowired
    private SjfxService sjfxService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Sjfx sjfx){
        Map<String, Object> resultMap = sjfxService.getPageList(sjfx);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Sjfx sjfx){
        boolean bool = sjfxService.insert(sjfx);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping(value="/import.do",method= RequestMethod.POST)
    public JsonResult<Object> insert(@RequestParam("upFile") MultipartFile upFile
            ,@RequestParam("userId") String userId,@RequestParam("userName") String userName) throws Exception {
        boolean isExcel2007 = true;
        String fileName = upFile.getOriginalFilename();
        //首先判断是不是excel文件
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return JsonResult.error("请使用系统提供的模板上传！");
        }else{
            if (fileName.matches("^.+\\.(?i)(xls)$")) {
                isExcel2007 = false;
            }
        }
        //批量插入数据
        String[] titleArr = {
                "testName","remark"
        };
        List<Map<String, Object>> mapList = ExcelUtil.getData(isExcel2007, upFile.getInputStream(), titleArr);
        if(mapList !=null && mapList.size() >0){
            boolean bool = sjfxService.batchImport(mapList,userId,userName);
            if(!bool){
                return JsonResult.error();
            }
        }
        return JsonResult.success(mapList.size());
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Sjfx sjfx){
        boolean bool = sjfxService.update(sjfx);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = sjfxService.delete(code);
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
        //
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        //
        List<Sjfx> sjfxList = JSON.parseArray(jsonStr, Sjfx.class);
        boolean bool = sjfxService.toSubimt(activeShenheCode,sjfxList);
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
        List<Sjfx> sjfxList = JSON.parseArray(jsonStr, Sjfx.class);
        boolean bool = sjfxService.toShenhe(item,sjfxList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
