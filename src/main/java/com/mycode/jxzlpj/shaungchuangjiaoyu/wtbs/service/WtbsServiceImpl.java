package com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.mapper.WtbsMapper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-文、体类比赛
 */
@Service
public class WtbsServiceImpl implements WtbsService {


    @Resource
    private WtbsMapper wtbsMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Wtbs wtbs) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(wtbs.getPageIndex(), wtbs.getPageSize());
        List<Wtbs> pageList = wtbsMapper.getPageList(wtbs);
        //获取未审核数
        if(StringUtils.isNotEmpty(wtbs.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_scjy_wtbs_shenhe, wtbs));
        }
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(Wtbs wtbs) {
        return wtbsMapper.insert(wtbs);
    }

    @Override
    public boolean update(Wtbs wtbs) {
        return wtbsMapper.update(wtbs);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = wtbsMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
