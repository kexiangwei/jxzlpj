package com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.mapper.JiXuJiaoYuMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-继续教育
 */
@Service
public class JiXuJiaoYuServiceImpl implements JiXuJiaoYuService {

    @Resource
    private JiXuJiaoYuMapper jiXuJiaoYuMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiXuJiaoYu jiXuJiaoYu) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiXuJiaoYu.getPageIndex(), jiXuJiaoYu.getPageSize());
        List<JiXuJiaoYu> pageList = jiXuJiaoYuMapper.getPageList(jiXuJiaoYu);
        //获取未审核数
        if(StringUtils.isNotEmpty(jiXuJiaoYu.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxyj_jxjy_shenhe, jiXuJiaoYu));
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
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
