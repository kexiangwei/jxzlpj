package com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.shenheSet.mapper.ShenHeMapper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.domian.Jsgrcgj;
import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.mapper.JsgrcgjMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教师个人成果奖
 */
@Service
public class JsgrcgjServiceImpl implements JsgrcgjService {

    @Autowired
    private JsgrcgjMapper jsgrcgjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Jsgrcgj jsgrcgj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jsgrcgj.getPageIndex(), jsgrcgj.getPageSize());
        List<Jsgrcgj> pageList = jsgrcgjMapper.getPageList(jsgrcgj);
        //获取未审核数
        if(StringUtils.isNotEmpty(jsgrcgj.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxjc_jsgrcgj_shenhe", jsgrcgj.getShenHeUserId()));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Jsgrcgj jsgrcgj) {
        return jsgrcgjMapper.insert(jsgrcgj);
    }

    @Override
    public boolean update(Jsgrcgj jsgrcgj) {
        return jsgrcgjMapper.update(jsgrcgj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jsgrcgjMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
