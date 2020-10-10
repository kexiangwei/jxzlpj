package com.mycode.jiaoxuejiangcheng.jyjxcgj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.mapper.JyjxcgjMapper;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教育教学成果奖
 */
@Service
public class JyjxcgjServiceImpl implements JyjxcgjService {

    @Autowired
    private JyjxcgjMapper jyjxcgjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Jyjxcgj jyjxcgj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jyjxcgj.getPageIndex(), jyjxcgj.getPageSize());
        List<Jyjxcgj> pageList = jyjxcgjMapper.getPageList(jyjxcgj);
        //获取未审核数
        if(StringUtils.isNotEmpty(jyjxcgj.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxjc_jyjxcgj_shenhe", jyjxcgj.getShenHeUserId()));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Jyjxcgj jyjxcgj) {
        return jyjxcgjMapper.insert(jyjxcgj);
    }

    @Override
    public boolean update(Jyjxcgj jyjxcgj) {
        return jyjxcgjMapper.update(jyjxcgj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jyjxcgjMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
