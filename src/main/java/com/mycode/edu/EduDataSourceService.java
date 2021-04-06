package com.mycode.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

/**
 * @auther kexiangwei
 * @date 2020/6/20
 */
//@EnableScheduling
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
//    @Scheduled(cron = "0 0/10 * * * ?") //10分钟执行一次
    @Scheduled(cron = "0 0 0 1 * ?") //每月1日00:00执行一次
    public boolean resetEduDataInfo(){
        boolean bool = false;
        final String[] tabs = {
                "SYS_DATA_XY","SYS_DATA_ZY","SYS_DATA_KC","SYS_DATA_JS","SYS_DATA_JSRK","SYS_DATA_XS","SYS_DATA_XSXK"
        };
        final String[] sql = {
                "select * from jw_user.V_JXZLPJ_XY","select * from jw_user.V_JXZLPJ_ZY","select * from jw_user.V_JXZLPJ_KC"
                ,"select * from jw_user.V_JXZLPJ_JS","select * from jw_user.V_JXZLPJ_JSRK"
                ,"select * from jw_user.V_JXZLPJ_XS","select * from jw_user.V_JXZLPJ_XSXK"
        };

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
                    if(mapList.size() == 2000){
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
