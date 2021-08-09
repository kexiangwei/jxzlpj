package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiSchool;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.mapper.JiaoXueTuanDuiSchoolMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoXueTuanDuiSchoolServiceImpl implements JiaoXueTuanDuiSchoolService {

    @Resource
    private JiaoXueTuanDuiSchoolMapper jiaoXueTuanDuiSchoolMapper;
    @Resource
    private ShenHeMapper shenHeMapper;
    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoXueTuanDuiSchool.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoXueTuanDuiSchool.getShenHeUserId());
            jiaoXueTuanDuiSchool.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoXueTuanDuiSchool.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_jxyj_jxtd_school_shenhe
                    , jiaoXueTuanDuiSchool));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoXueTuanDuiSchool.getPageIndex(), jiaoXueTuanDuiSchool.getPageSize());
        List<JiaoXueTuanDuiSchool> pageList = jiaoXueTuanDuiSchoolMapper.getPageList(jiaoXueTuanDuiSchool);
        if(jwcGly == 1){
            if(pageList !=null && pageList.size() > 0){
                for (JiaoXueTuanDuiSchool jiaoXueTuanDui : pageList) {
                    //若数据未提交，则不执行此查询
                    if(StringUtils.isNotEmpty(jiaoXueTuanDui.getShenheCode())){
                        jiaoXueTuanDui.setIsZjshAll(shenHeMapper.isZjshAll(jiaoXueTuanDui.getCode(),jiaoXueTuanDui.getBatchNum()));
                        jiaoXueTuanDui.setZjshItemList(shenHeMapper.getZjshProcess(jiaoXueTuanDui.getCode(),jiaoXueTuanDui.getBatchNum())); //专家审核意见
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool) {
        return jiaoXueTuanDuiSchoolMapper.insert(jiaoXueTuanDuiSchool);
    }

    @Override
    public boolean update(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool) {
        return jiaoXueTuanDuiSchoolMapper.update(jiaoXueTuanDuiSchool);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoXueTuanDuiSchoolMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
