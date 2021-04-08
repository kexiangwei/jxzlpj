package com.mycode.jxzlpj.zhtj.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface XyMapper {
    public List<Map<String, Object>> getList();
}
