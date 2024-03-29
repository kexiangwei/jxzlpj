package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.mapper.JiaoGaiLunWenMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改论文
 */
@Service
public class JiaoGaiLunWenServiceImpl implements JiaoGaiLunWenService {

    @Resource
    private JiaoGaiLunWenMapper jiaoGaiLunWenMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiLunWen.getPageIndex(), jiaoGaiLunWen.getPageSize());
        List<JiaoGaiLunWen> pageList = jiaoGaiLunWenMapper.getPageList(jiaoGaiLunWen);
        //获取未审核数
        if(StringUtils.isNotEmpty(jiaoGaiLunWen.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxyj_jglw_shenhe, jiaoGaiLunWen));
        }
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(JiaoGaiLunWen jiaoGaiLunWen) {
        return jiaoGaiLunWenMapper.insert(jiaoGaiLunWen);
    }

    @Override
    public boolean update(JiaoGaiLunWen jiaoGaiLunWen) {
        return jiaoGaiLunWenMapper.update(jiaoGaiLunWen);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoGaiLunWenMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
