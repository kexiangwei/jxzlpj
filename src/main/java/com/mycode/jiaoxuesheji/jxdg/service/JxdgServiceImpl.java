package com.mycode.jiaoxuesheji.jxdg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.jxdg.domain.Course;
import com.mycode.jiaoxuesheji.jxdg.mapper.JxdgMapper;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-教学大纲
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Service
public class JxdgServiceImpl implements JxdgService {

    @Autowired
    private JxdgMapper jxdgMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getCourseList(Course course) {
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(course.getShenHeUserId())){
            resultMap.put("unShenHeNum", jxdgMapper.getNotShenHeNum(course.getShenHeUserId())); //获取未审核数
        }
        Page<Object> pageInfo = PageHelper.startPage(course.getPageIndex(), course.getPageSize());
        List<Course> pageList = jxdgMapper.getCourseList(course);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Course> courseList) {
        for (Course course : courseList) {
            course.setShenheCode(activeShenheCode);
            course.setBatchNum(StringUtils.isEmpty(course.getBatchNum())?1:course.getBatchNum()+1);//提交批次，每提交一次加1
            course.setStatus("审核中");
        }
        return jxdgMapper.batchSubimt(courseList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Course> courseList) {
        boolean bool = false;
        for (Course course : courseList) {
            item.setRelationCode(course.getCode());
            item.setBatchNum(course.getBatchNum());
            ShenHeNode node = jxdgMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = jxdgMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
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
