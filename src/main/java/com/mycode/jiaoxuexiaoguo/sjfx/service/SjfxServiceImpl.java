package com.mycode.jiaoxuexiaoguo.sjfx.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import com.mycode.jiaoxuexiaoguo.sjfx.mapper.SjfxMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SjfxServiceImpl implements SjfxService {

    @Autowired
    private SjfxMapper sjfxMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Sjfx sjfx) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取未审核数
        if(StringUtils.isNotEmpty(sjfx.getShenHeUserId())){
            resultMap.put("unShenHeNum", sjfxMapper.getNotShenHeNum(sjfx.getShenHeUserId()));
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(sjfx.getPageIndex(), sjfx.getPageSize());
        List<Sjfx> pageList = sjfxMapper.getPageList(sjfx);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Sjfx sjfx) {
        return sjfxMapper.insert(sjfx);
    }

    @Override
    public boolean update(Sjfx sjfx) {
        return sjfxMapper.update(sjfx);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = sjfxMapper.delete(code);
        if(bool){
            List<FileInfo> fileList = fileMapper.getFileListByRelationCode(code);
            if(!fileList.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Sjfx> sjfxList) {
        for (Sjfx sjfx : sjfxList) {
            sjfx.setShenheCode(activeShenheCode);
            sjfx.setBatchNum(StringUtils.isEmpty(sjfx.getBatchNum())?1:sjfx.getBatchNum()+1);//提交批次，每提交一次加1
            sjfx.setStatus("审核中");
        }
        return shenHeMapper.batchSubimt(sjfxList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Sjfx> sjfxList) {
        boolean bool = false;
        for (Sjfx sjfx : sjfxList) {
            item.setRelationCode(sjfx.getCode());
            item.setBatchNum(sjfx.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXXG_SJFX_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXXG_SJFX_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
