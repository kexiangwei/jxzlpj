package com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;
import com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.mapper.BkssqzlMapper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-本科生申请专利
 */
@Service
public class BkssqzlServiceImpl implements BkssqzlService {

    @Autowired
    private BkssqzlMapper bkssqzlMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Bkssqzl bkssqzl) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(bkssqzl.getPageIndex(), bkssqzl.getPageSize());
        List<Bkssqzl> pageList = bkssqzlMapper.getPageList(bkssqzl);
        //获取未审核数
        if(StringUtils.isNotEmpty(bkssqzl.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_scjy_bkssqzl_shenhe", bkssqzl.getShenHeUserId()));
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Bkssqzl bkssqzl) {
        return bkssqzlMapper.insert(bkssqzl);
    }

    @Override
    public boolean update(Bkssqzl bkssqzl) {
        return bkssqzlMapper.update(bkssqzl);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = bkssqzlMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
