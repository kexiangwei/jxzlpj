package com.mycode.jxzlpj.jiaoxuejiangcheng.qtbkjxjl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxuejiangcheng.qtbkjxjl.domian.Qtbkjxjl;
import com.mycode.jxzlpj.jiaoxuejiangcheng.qtbkjxjl.mapper.QtbkjxjlMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-其他本科教学奖励
 */
@Service
public class QtbkjxjlServiceImpl implements QtbkjxjlService {

    @Autowired
    private QtbkjxjlMapper qtbkjxjlMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Qtbkjxjl qtbkjxjl) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(qtbkjxjl.getPageIndex(), qtbkjxjl.getPageSize());
        List<Qtbkjxjl> pageList = qtbkjxjlMapper.getPageList(qtbkjxjl);
        //获取未审核数
        if(StringUtils.isNotEmpty(qtbkjxjl.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxjc_qtbkjxjl_shenhe", qtbkjxjl.getShenHeUserId()));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Qtbkjxjl qtbkjxjl) {
        return qtbkjxjlMapper.insert(qtbkjxjl);
    }

    @Override
    public boolean update(Qtbkjxjl qtbkjxjl) {
        return qtbkjxjlMapper.update(qtbkjxjl);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = qtbkjxjlMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
