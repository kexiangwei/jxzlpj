package com.mycode.jiaoxuexiaoguo.sybg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.jiaoxuexiaoguo.sybg.domian.Sybg;
import com.mycode.jiaoxuexiaoguo.sybg.mapper.SybgMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SybgServiceImpl implements SybgService {

    @Autowired
    private SybgMapper sybgMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Sybg sybg) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(sybg.getPageIndex(), sybg.getPageSize());
        List<Sybg> pageList = sybgMapper.getPageList(sybg);
        //获取未审核数
        if(StringUtils.isNotEmpty(sybg.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxxg_sybg_shenhe", sybg.getShenHeUserId()));
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Sybg sybg) {
        return sybgMapper.insert(sybg);
    }

    @Override
    public boolean update(Sybg sybg) {
        return sybgMapper.update(sybg);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = sybgMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
