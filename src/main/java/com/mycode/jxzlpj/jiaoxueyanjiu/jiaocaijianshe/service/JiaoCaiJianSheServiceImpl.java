package com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.mapper.JiaoCaiJianSheMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教材建设
 */
@Service
public class JiaoCaiJianSheServiceImpl implements JiaoCaiJianSheService {

    @Resource
    private JiaoCaiJianSheMapper jiaoCaiJianSheMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoCaiJianShe.getPageIndex(), jiaoCaiJianShe.getPageSize());
        List<JiaoCaiJianShe> pageList = jiaoCaiJianSheMapper.getPageList(jiaoCaiJianShe);
        //获取未审核数
        if(StringUtils.isNotEmpty(jiaoCaiJianShe.getShenHeUserId())){
            map.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxyj_jcjs_shenhe, jiaoCaiJianShe));
        }
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(JiaoCaiJianShe jiaoCaiJianShe) {
        return jiaoCaiJianSheMapper.insert(jiaoCaiJianShe);
    }

    @Override
    public boolean update(JiaoCaiJianShe jiaoCaiJianShe) {
        return jiaoCaiJianSheMapper.update(jiaoCaiJianShe);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoCaiJianSheMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }
}
