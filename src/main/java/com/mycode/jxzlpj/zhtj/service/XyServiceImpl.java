package com.mycode.jxzlpj.zhtj.service;

import com.mycode.jxzlpj.zhtj.mapper.XyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XyServiceImpl implements XyService {

    @Autowired
    private XyMapper xyMapper;

    @Override
    public List<Map<String, Object>> getList() {
        return xyMapper.getList();
    }
}
