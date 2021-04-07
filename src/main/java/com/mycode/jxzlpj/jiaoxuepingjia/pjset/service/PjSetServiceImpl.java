package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教学评价-评教设置
 */
@Service
public class PjSetServiceImpl implements PjSetService {

    @Autowired
    private PjSetMapper pjSetMapper;

}
