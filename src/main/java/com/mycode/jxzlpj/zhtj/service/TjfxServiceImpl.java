package com.mycode.jxzlpj.zhtj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.zhtj.domain.Tjfx;
import com.mycode.jxzlpj.zhtj.mapper.TjfxMapper;
import com.mycode.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TjfxServiceImpl implements TjfxService {

    @Autowired
    private TjfxMapper tjfxMapper;

    @Override
    public Map<String, Object> getTjfxPageList(Tjfx tjfx) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(tjfx.getPageIndex(), tjfx.getPageSize());
        List<Map<String,Object>> pageList = tjfxMapper.getTjfxPageList(tjfx);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }
}
