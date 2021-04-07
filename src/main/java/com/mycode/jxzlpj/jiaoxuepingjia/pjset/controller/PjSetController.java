package com.mycode.jxzlpj.jiaoxuepingjia.pjset.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 教学评价-评教设置
 */
@CrossOrigin
@Controller
public class PjSetController {

    @Autowired
    private PjSetService pjSetService;

}
