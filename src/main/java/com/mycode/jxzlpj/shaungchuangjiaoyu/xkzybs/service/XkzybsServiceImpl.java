package com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.mapper.XkzybsMapper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-学科专业比赛
 */
@Service
public class XkzybsServiceImpl implements XkzybsService {

    @Resource
    private XkzybsMapper xkzybsMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Xkzybs xkzybs) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(xkzybs.getPageIndex(), xkzybs.getPageSize());
        List<Xkzybs> pageList = xkzybsMapper.getPageList(xkzybs);
        //获取未审核数
        if(StringUtils.isNotEmpty(xkzybs.getShenHeUserId())){
            /*long count = pageList.stream().filter(m -> m.getShenheStatus().equals("shenheStatus")).count();
            map.put("unShenHeNum", count);*/
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_scjy_xkzybs_shenhe, xkzybs));
        }
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(Xkzybs xkzybs) {
        return xkzybsMapper.insert(xkzybs);
    }

    @Override
    public boolean update(Xkzybs xkzybs) {
        return xkzybsMapper.update(xkzybs);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = xkzybsMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
