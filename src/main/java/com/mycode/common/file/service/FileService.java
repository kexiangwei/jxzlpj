package com.mycode.common.file.service;

import com.mycode.common.file.domain.FileInfo;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface FileService {

    List<FileInfo> getFileListByRelationCode(String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(String code, String relationCode);
}
