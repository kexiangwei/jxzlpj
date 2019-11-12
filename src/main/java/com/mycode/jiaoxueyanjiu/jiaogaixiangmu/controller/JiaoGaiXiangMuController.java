package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/8/19
 */
@CrossOrigin
@Controller
@RequestMapping("/JiaoGaiXiangMu")
public class JiaoGaiXiangMuController {

    @Autowired
    private JiaoGaiXiangMuService jiaoGaiXiangMuService;

    @ResponseBody
    @RequestMapping("/getList.do")
    public JsonResult<Object> getList(JiaoGaiXiangMu jiaoGaiXiangMu){
        Map<String, Object> map = jiaoGaiXiangMuService.getList(jiaoGaiXiangMu);
        return JsonResult.success(map);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiXiangMu jiaoGaiXiangMu){
        boolean bool = jiaoGaiXiangMuService.insert(jiaoGaiXiangMu);
        if(!bool){
            return JsonResult.error("添加失败");
        }
        return JsonResult.success("添加成功");
    }
}
