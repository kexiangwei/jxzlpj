package com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.mapper.KcjxdgMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
@Service
public class KcjxdgServiceImpl implements KcjxdgService {


    @Autowired
    private KcjxdgMapper kcjxdgMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kcjxdg kcjxdg) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(kcjxdg.getPageIndex(), kcjxdg.getPageSize());
        List<Kcjxdg> pageList = kcjxdgMapper.getPageList(kcjxdg);
        //获取未审核数
        if(StringUtils.isNotEmpty(kcjxdg.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxsj_kcjxdg_shenhe, kcjxdg));
        }
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(Kcjxdg kcjxdg) {
        return kcjxdgMapper.insert(kcjxdg);
    }

    @Override
    public boolean update(Kcjxdg kcjxdg) {
        return kcjxdgMapper.update(kcjxdg);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = kcjxdgMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
