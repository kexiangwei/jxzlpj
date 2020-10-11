package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.CountryTeam;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper.CountryTeamMapper;
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
public class CountryTeamServiceImpl implements CountryTeamService {

    @Autowired
    private CountryTeamMapper countryTeamMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(CountryTeam countryTeam) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(countryTeam.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isJXTD_ZjAccount(countryTeam.getShenHeUserId());
            countryTeam.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(countryTeam.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JXTD_COUNTRY_SHENHE"
                    , countryTeam.getShenHeUserId(),isZjshAccount,jwcGly,countryTeam.getMenuId()));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(countryTeam.getPageIndex(), countryTeam.getPageSize());
        List<CountryTeam> pageList = countryTeamMapper.getPageList(countryTeam);
        if(pageList !=null && pageList.size() > 0){
            for (CountryTeam team : pageList) {
                //
                if(StringUtils.isNotEmpty(team.getShenheCode())){ //若数据未提交，则不执行此查询
                    team.setZjshItemList(shenHeMapper.getZjshProcess(team.getCode(),team.getBatchNum())); //专家审核意见
                    if(jwcGly == 1){
                        team.setIsZjshAll(shenHeMapper.isZjshAll(team.getCode(),team.getBatchNum(),"asdf"));
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(CountryTeam countryTeam) {
        return countryTeamMapper.insert(countryTeam);
    }

    @Override
    public boolean update(CountryTeam countryTeam) {
        return countryTeamMapper.update(countryTeam);
    }

    @Override
    public boolean delete(String code) {
        return countryTeamMapper.delete(code);
    }

}
