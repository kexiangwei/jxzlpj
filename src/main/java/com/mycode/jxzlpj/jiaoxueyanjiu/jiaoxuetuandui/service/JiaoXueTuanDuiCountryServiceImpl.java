package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiCountry;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.mapper.JiaoXueTuanDuiCountryMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队-国家级团队
 */
@Service
public class JiaoXueTuanDuiCountryServiceImpl implements JiaoXueTuanDuiCountryService {

    @Autowired
    private JiaoXueTuanDuiCountryMapper jiaoXueTuanDuiCountryMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoXueTuanDuiCountry.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoXueTuanDuiCountry.getShenHeUserId());
            jiaoXueTuanDuiCountry.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoXueTuanDuiCountry.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JXTD_COUNTRY_SHENHE"
                    , jiaoXueTuanDuiCountry.getShenHeUserId(),isZjshAccount,jwcGly));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoXueTuanDuiCountry.getPageIndex(), jiaoXueTuanDuiCountry.getPageSize());
        List<JiaoXueTuanDuiCountry> pageList = jiaoXueTuanDuiCountryMapper.getPageList(jiaoXueTuanDuiCountry);
        if(jwcGly == 1){
            if(pageList !=null && pageList.size() > 0){
                for (JiaoXueTuanDuiCountry team : pageList) {
                    //若数据未提交，则不执行此查询
                    if(StringUtils.isNotEmpty(team.getShenheCode())){
                        team.setZjshItemList(shenHeMapper.getZjshProcess(team.getCode(),team.getBatchNum())); //专家审核意见
                        team.setIsZjshAll(shenHeMapper.isZjshAll(team.getCode(),team.getBatchNum()));
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry) {
        return jiaoXueTuanDuiCountryMapper.insert(jiaoXueTuanDuiCountry);
    }

    @Override
    public boolean update(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry) {
        return jiaoXueTuanDuiCountryMapper.update(jiaoXueTuanDuiCountry);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoXueTuanDuiCountryMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(code);
            }
        }
        return bool;
    }

}
