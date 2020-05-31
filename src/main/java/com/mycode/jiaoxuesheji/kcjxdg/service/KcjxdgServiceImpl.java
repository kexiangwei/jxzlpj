package com.mycode.jiaoxuesheji.kcjxdg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.jiaoxuesheji.kcjxdg.mapper.KcjxdgMapper;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.mapper.JiaoCaiJianSheMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
@Service
public class KcjxdgServiceImpl implements KcjxdgService {

    @Autowired
    private KcjxdgMapper kcjxdgMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kcjxdg kcjxdg) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取未审核数
        if(StringUtils.isNotEmpty(kcjxdg.getShenHeUserId())){
            resultMap.put("unShenHeNum", kcjxdgMapper.getNotShenHeNum(kcjxdg.getShenHeUserId()));
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(kcjxdg.getPageIndex(), kcjxdg.getPageSize());
        List<Kcjxdg> pageList = kcjxdgMapper.getPageList(kcjxdg);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kcjxdg kcjxdg) {
        return kcjxdgMapper.insert(kcjxdg);
    }

    @Override
    public boolean update(Kcjxdg kcjxdg) {
        return kcjxdgMapper.update(kcjxdg);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = kcjxdgMapper.delete(code);
        if(bool){
            List<FileInfo> fileList = fileMapper.getFileListByRelationCode(code);
            if(!fileList.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Kcjxdg> kcjxdgList) {
        for (Kcjxdg kcjxdg : kcjxdgList) {
            kcjxdg.setShenheCode(activeShenheCode);
            kcjxdg.setBatchNum(StringUtils.isEmpty(kcjxdg.getBatchNum())?1:kcjxdg.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(kcjxdgList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Kcjxdg> kcjxdgList) {
        boolean bool = false;
        for (Kcjxdg kcjxdg : kcjxdgList) {
            item.setRelationCode(kcjxdg.getCode());
            item.setBatchNum(kcjxdg.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXSJ_KCJXDG_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXSJ_KCJXDG_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
