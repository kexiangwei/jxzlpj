package com.mycode.jiaoxueyanjiu.jixujiaoyu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.mapper.JiXuJiaoYuMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-继续教育
 */
@Service
public class JiXuJiaoYuServiceImpl implements JiXuJiaoYuService {

    @Autowired
    private JiXuJiaoYuMapper jiXuJiaoYuMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiXuJiaoYu jiXuJiaoYu) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiXuJiaoYu.getPageIndex(), jiXuJiaoYu.getPageSize());
        List<JiXuJiaoYu> pageList = jiXuJiaoYuMapper.getPageList(jiXuJiaoYu);
        //获取未审核数
        if(StringUtils.isNotEmpty(jiXuJiaoYu.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JXJY_SHENHE", jiXuJiaoYu.getShenHeUserId()));
        }
        map.put("totalNum",pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(JiXuJiaoYu jiXuJiaoYu) {
        return jiXuJiaoYuMapper.insert(jiXuJiaoYu);
    }

    @Override
    public boolean update(JiXuJiaoYu jiXuJiaoYu) {
        return jiXuJiaoYuMapper.update(jiXuJiaoYu);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiXuJiaoYuMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }
}
