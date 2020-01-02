package com.mycode.jiaoxuexiaoguo.sjfx.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import com.mycode.jiaoxuexiaoguo.sjfx.mapper.SjfxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学效果-试卷分析
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class SjfxServiceImpl implements SjfxService {

    @Autowired
    private SjfxMapper sjfxMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(Sjfx sjfx) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(sjfx.getPageIndex(), sjfx.getPageSize());
        List<Sjfx> pageList = sjfxMapper.getPageList(sjfx);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        //
        if(!StringUtils.isEmpty(sjfx.getShenHeUserId())){
            int unShenHeNum = sjfxMapper.getNotShenHeNum(sjfx.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        //
        return resultMap;
    }

    @Override
    public boolean insert(Sjfx sjfx) {
        return sjfxMapper.insert(sjfx);
    }

    @Override
    public boolean batchImport(List<Map<String, Object>> mapList, String userId, String userName) {
        return sjfxMapper.batchImport(mapList,userId,userName);
    }

    @Override
    public boolean update(Sjfx sjfx) {
        return sjfxMapper.update(sjfx);
    }

    @Override
    public boolean delete(String code) {
        return sjfxMapper.delete(code);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Sjfx> sjfxList) {
        for (Sjfx sjfx : sjfxList) {
            sjfx.setShenheCode(activeShenheCode);
            sjfx.setBatchNum(StringUtils.isEmpty(sjfx.getBatchNum())?1:sjfx.getBatchNum()+1);//提交批次，每提交一次加1
            sjfx.setStatus("审核中");
        }
        return sjfxMapper.batchSubimt(sjfxList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Sjfx> sjfxList) {
        boolean bool = false;
        for (Sjfx sjfx : sjfxList) {
            item.setRelationCode(sjfx.getCode());
            item.setBatchNum(sjfx.getBatchNum());
            ShenHeNode node = sjfxMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = sjfxMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"通过");
                    }
                }else if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                }
            }
        }
        return bool;
    }

}
