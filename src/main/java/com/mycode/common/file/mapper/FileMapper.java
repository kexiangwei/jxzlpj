package com.mycode.common.file.mapper;

import com.mycode.common.file.domain.FileInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM COMMON_FILE WHERE relation_code = #{relationCode}")
    List<FileInfo> getFileListByRelationCode(@Param("relationCode") String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(@Param("code") String code, @Param("relationCode") String relationCode);
}
