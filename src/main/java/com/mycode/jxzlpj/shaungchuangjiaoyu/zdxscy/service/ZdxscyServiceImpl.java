package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;
import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.mapper.ZdxscyMapper;
import com.mycode.shenheSet.mapper.ShenHeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生创业
 */
@Service
public class ZdxscyServiceImpl implements ZdxscyService {

    @Autowired
    private ZdxscyMapper zdxscyMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Zdxscy zdxscy) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(zdxscy.getPageIndex(), zdxscy.getPageSize());
        List<Zdxscy> pageList = zdxscyMapper.getPageList(zdxscy);
        //获取未审核数
        if(com.mycode.util.StringUtils.isNotEmpty(zdxscy.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_scjy_zdxscy_shenhe", zdxscy.getShenHeUserId()));
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Zdxscy zdxscy) {
        return zdxscyMapper.insert(zdxscy);
    }

    @Override
    public boolean update(Zdxscy zdxscy) {
        return zdxscyMapper.update(zdxscy);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = zdxscyMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
