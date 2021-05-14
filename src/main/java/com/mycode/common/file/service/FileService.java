package com.mycode.common.file.service;

import com.mycode.common.file.domain.FileInfo;

import java.util.List;

public interface FileService {

    List<FileInfo> getFileListByRelationCode(String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(String relationCode, String code);
}
