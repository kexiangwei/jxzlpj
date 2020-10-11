package com.mycode.jiaoxuejiangcheng.zyjscgj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.jiaoxuejiangcheng.zyjscgj.domian.Zyjscgj;
import com.mycode.jiaoxuejiangcheng.zyjscgj.mapper.ZyjscgjMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-专业建设成果奖
 */
@Service
public class ZyjscgjServiceImpl implements ZyjscgjService {

    @Autowired
    private ZyjscgjMapper zyjscgjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Zyjscgj zyjscgj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(zyjscgj.getPageIndex(), zyjscgj.getPageSize());
        List<Zyjscgj> pageList = zyjscgjMapper.getPageList(zyjscgj);
        //获取未审核数
        if(StringUtils.isNotEmpty(zyjscgj.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxjc_zyjscgj_shenhe", zyjscgj.getShenHeUserId()));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Zyjscgj zyjscgj) {
        return zyjscgjMapper.insert(zyjscgj);
    }

    @Override
    public boolean update(Zyjscgj zyjscgj) {
        return zyjscgjMapper.update(zyjscgj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = zyjscgjMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
