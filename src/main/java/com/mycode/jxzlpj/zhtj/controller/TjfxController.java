package com.mycode.jxzlpj.zhtj.controller;

import com.mycode.jxzlpj.zhtj.domain.Tjfx;
import com.mycode.jxzlpj.zhtj.service.TjfxService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TjfxController {

    @Autowired
    private TjfxService tjfxService;

    @GetMapping("/getTjfxPageList.do")
    public JsonResult<Object> getTjfxPageList(Tjfx tjfx){
        Map<String,Object> resultMap = tjfxService.getTjfxPageList(tjfx);
        return JsonResult.success(resultMap);
    }
}
