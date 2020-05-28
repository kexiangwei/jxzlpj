package com.mycode.common.file.service;

import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileInfo> getFileListByRelationCode(String relationCode) {
        return fileMapper.getFileListByRelationCode(relationCode);
    }

    @Override
    public boolean saveFileInfo(FileInfo fileInfo) {
        return fileMapper.saveFileInfo(fileInfo);
    }

    @Override
    public boolean deleteFileInfo(String code, String relationCode) {
        return fileMapper.deleteFileInfo(code,relationCode);
    }
}
