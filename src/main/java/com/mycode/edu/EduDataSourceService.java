package com.mycode.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

/**
 * @auther kexiangwei
 * @date 2020/6/20
 */
@Service
public class EduDataSourceService {

    @Autowired
    private EduMapper eduMapper;
    @Autowired
    private EduDataSource eduDataSource;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

//    @Scheduled(cron = "0 * * * * ?") //每分钟执行一次
//    @Scheduled(cron = "0 0/5 * * * ?") //5分钟执行一次
    @Scheduled(cron = "0 0 0 1 * ?") //每月1日00:00执行一次
    public boolean resetEduDataInfo(){
        boolean bool = false;
        final String[] tabs = {"DATA_COLLEGE","DATA_MAJOR","DATA_CLASS","DATA_COURSE","DATA_TEACHER","DATA_STUDENT","DATA_TEACHER_COURSE","DATA_STUDENT_COURSE"};
        final String[] sql = {"select jg_id,jgmc from jw_user.v_xy"
                ,"select zyh_id,zymc,xz,null,zy.jg_id,jgmc from jw_user.v_zy zy left join jw_user.v_xy xy on xy.jg_id = zy.jg_id"
                ,"select bh_id,bj,null,zxrs,njdm_id,bj.zyh_id,zymc,jgmc from jw_user.v_bj bj left join jw_user.v_zy zy on zy.zyh_id = bj.zyh_id left join jw_user.v_xy xy on xy.jg_id = bj.jg_id"
                ,"select kch_id,kcmc,kcxzmc,zxs,xf,null,null,jgmc from jw_user.v_kc kc left join jw_user.v_kcxz kcxz on kcxz.kcxzdm = kc.kch_id left join jw_user.v_xy xy on xy.jg_id = kc.kkbm_id"
                ,"select jgh_id,xm,null,csrq,null,zcm,null,null,jgmc,null from jw_user.v_jsxx js left join jw_user.v_xy xy on xy.jg_id = js.jg_id"
                ,"select xh_id,xm,null,csrq,xs.bh_id,bj,xs.njdm_id,xs.zyh_id,zymc,jgmc from jw_user.v_xsxx xs left join jw_user.v_bj bj on bj.bh_id = xs.bh_id left join jw_user.v_zy zy on zy.zyh_id = xs.zyh_id left join jw_user.v_xy xy on xy.jg_id = xs.jg_id"
                ,"select a.jgh_id,b.kch_id from jw_user.v_jsrk a left join jw_user.v_jxrw b on a.jxb_id = b.jxb_id where b.jxb_id is not null and b.xnm = Extract(year from sysdate)"
                ,"select a.xh_id,b.kch_id from jw_user.v_xsxk a left join jw_user.v_jxrw b on a.jxb_id = b.jxb_id where b.jxb_id is not null and b.xnm = Extract(year from sysdate)"};

        try {
            conn = eduDataSource.getConnect();
            for (int i = 0; i < tabs.length; i++) {
                //清空表
                bool = eduMapper.deleteEduDataInfo(tabs[i]);
                //同步数据
                ps = conn.prepareStatement(sql[i]);
                rs = ps.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount(); //获取列数
                List<LinkedHashMap<String,Object>> mapList = new ArrayList<>();
                int rowCount = 0;
                while (rs.next()){
                    LinkedHashMap<String,Object> map = new LinkedHashMap<>();
                    for (int j = 1; j <= columnCount; j++) {
//                        map.put(metaData.getColumnName(j),rs.getString(j)); //后台报错java.sql.SQLSyntaxErrorException: ORA-00947: 没有足够的值，原因是LinkedHashMap允许存储null值，键不可以重复，值可以重复。
                        map.put("col_"+j,rs.getString(j)); //随便定义一个key做占位符就可以了
                    }
                    mapList.add(map);
                    if(mapList.size() == 500){
                        bool = eduMapper.resetEduDataInfo(tabs[i],mapList);
                        if(!bool){
                            System.out.println("====== "+tabs[i]+" 更新失败 ======");
                        }
                        System.out.println("======正在更新"+tabs[i]+" 表数据。。。 ======");
                        mapList = new ArrayList<>();
                    }
                    ++rowCount;
                }
                if(mapList.size() >0){
                    bool = eduMapper.resetEduDataInfo(tabs[i],mapList);
                }
                System.out.println("====== "+tabs[i]+" 更新成功，总计："+rowCount+" 条记录 ======");
            }
            //最后更新SYS_USER,SYS_USER_ROLE 表数据
            if(bool){
                System.out.println("====== 更新用户表数据 ======");
                bool = eduMapper.resetSysUserTeacherInfo();
                bool = eduMapper.resetSysUserStudentInfo();
                if(bool){
                    System.out.println("====== 更新权限表数据 ======");
                    bool = eduMapper.resetSysUserRoleInfo();
                }
                System.out.println("====== 更新完毕！ ======");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            eduDataSource.close(conn,ps,rs);
        }
        return bool;
    }
}
