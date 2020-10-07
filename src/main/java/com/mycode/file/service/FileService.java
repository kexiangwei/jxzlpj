package com.mycode.file.service;

import com.mycode.file.domain.FileInfo;

import java.util.List;

public interface FileService {

    List<FileInfo> getFileListByRelationCode(String relationCode);

    boolean saveFileInfo(FileInfo fileInfo);

    boolean deleteFileInfo(String code, String relationCode);
}
