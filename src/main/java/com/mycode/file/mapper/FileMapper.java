package com.mycode.file.mapper;

import com.mycode.file.domain.FileInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    List<FileInfo> getFileListByRelationCode(@Param("relationCode") String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(@Param("relationCode") String relationCode, @Param("code") String code);

    default boolean deleteFileInfo(@Param("relationCode") String relationCode){
        return this.deleteFileInfo(relationCode,null);
    }
}
