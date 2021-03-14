package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiProvince;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.mapper.JiaoXueTuanDuiProvinceMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoXueTuanDuiProvinceServiceImpl implements JiaoXueTuanDuiProvinceService {

    @Autowired
    private JiaoXueTuanDuiProvinceMapper jiaoXueTuanDuiProvinceMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoXueTuanDuiProvince.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoXueTuanDuiProvince.getShenHeUserId());
            jiaoXueTuanDuiProvince.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoXueTuanDuiProvince.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_jxyj_jxtd_province_shenhe"
                    , jiaoXueTuanDuiProvince.getShenHeUserId(),isZjshAccount,jwcGly));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoXueTuanDuiProvince.getPageIndex(), jiaoXueTuanDuiProvince.getPageSize());
        List<JiaoXueTuanDuiProvince> pageList = jiaoXueTuanDuiProvinceMapper.getPageList(jiaoXueTuanDuiProvince);
        if(jwcGly == 1){
            if(pageList !=null && pageList.size() > 0){
                for (JiaoXueTuanDuiProvince jiaoXueTuanDui : pageList) {
                    //若数据未提交，则不执行此查询
                    if(StringUtils.isNotEmpty(jiaoXueTuanDui.getShenheCode())){
                        jiaoXueTuanDui.setZjshItemList(shenHeMapper.getZjshProcess(jiaoXueTuanDui.getCode(),jiaoXueTuanDui.getBatchNum())); //专家审核意见
                        jiaoXueTuanDui.setIsZjshAll(shenHeMapper.isZjshAll(jiaoXueTuanDui.getCode(),jiaoXueTuanDui.getBatchNum()));
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince) {
        return jiaoXueTuanDuiProvinceMapper.insert(jiaoXueTuanDuiProvince);
    }

    @Override
    public boolean update(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince) {
        return jiaoXueTuanDuiProvinceMapper.update(jiaoXueTuanDuiProvince);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoXueTuanDuiProvinceMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
