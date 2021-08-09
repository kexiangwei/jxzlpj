package com.mycode.jxzlpj.jiaoxuejiangcheng.kcjscgj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.jxzlpj.jiaoxuejiangcheng.kcjscgj.domian.Kcjscgj;
import com.mycode.jxzlpj.jiaoxuejiangcheng.kcjscgj.mapper.KcjscgjMapper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-课程建设成果奖
 */
@Service
public class KcjscgjServiceImpl implements KcjscgjService {

    @Autowired
    private KcjscgjMapper kcjscgjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kcjscgj kcjscgj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(kcjscgj.getPageIndex(), kcjscgj.getPageSize());
        List<Kcjscgj> pageList = kcjscgjMapper.getPageList(kcjscgj);
        //获取未审核数
        if(StringUtils.isNotEmpty(kcjscgj.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxjc_kcjscgj_shenhe, kcjscgj));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kcjscgj kcjscgj) {
        return kcjscgjMapper.insert(kcjscgj);
    }

    @Override
    public boolean update(Kcjscgj kcjscgj) {
        return kcjscgjMapper.update(kcjscgj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = kcjscgjMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
