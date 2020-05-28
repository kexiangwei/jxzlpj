package com.mycode.common.file.mapper;

import com.mycode.common.file.domain.FileInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    List<FileInfo> getFileListByRelationCode(@Param("relationCode") String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(@Param("code") String code, @Param("relationCode") String relationCode);
}
