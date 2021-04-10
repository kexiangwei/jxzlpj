package com.mycode.jxzlpj.zhtj.mapper;

import com.mycode.jxzlpj.zhtj.domain.Tjfx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TjfxMapper {

    List<Map<String, Object>> getTjfxPageList(Tjfx tjfx);
}
