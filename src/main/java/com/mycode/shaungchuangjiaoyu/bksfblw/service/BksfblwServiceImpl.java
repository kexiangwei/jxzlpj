package com.mycode.shaungchuangjiaoyu.bksfblw.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;
import com.mycode.shaungchuangjiaoyu.bksfblw.mapper.BksfblwMapper;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-本科生发表论文
 */
@Service
public class BksfblwServiceImpl implements BksfblwService {

    @Autowired
    private BksfblwMapper bksfblwMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Bksfblw bksfblw) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(bksfblw.getPageIndex(), bksfblw.getPageSize());
        List<Bksfblw> pageList = bksfblwMapper.getPageList(bksfblw);
        //获取未审核数
        if(StringUtils.isNotEmpty(bksfblw.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_scjy_bksfblw_shenhe", bksfblw.getShenHeUserId()));
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Bksfblw bksfblw) {
        return bksfblwMapper.insert(bksfblw);
    }

    @Override
    public boolean update(Bksfblw bksfblw) {
        return bksfblwMapper.update(bksfblw);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = bksfblwMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
