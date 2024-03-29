package com.mycode.common.file.controller;

import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.service.FileService;
import com.mycode.util.JsonResult;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 根据关联数据编号获取文件列表
     * @param relationCode 关联的信息编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getFileListByRelationCode.do")
    public JsonResult<Object> getFileListByRelationCode(@RequestParam(value="relationCode")String relationCode) {
        List<FileInfo> fileList = fileService.getFileListByRelationCode(relationCode);
        return JsonResult.success(fileList);
    }

    @ResponseBody
    @RequestMapping(value="/uploadFileInfo.do",method= RequestMethod.POST)
    public JsonResult<Object> uploadFileInfo(FileInfo fileInfo, @RequestParam("file") MultipartFile[] multipartFiles) throws Exception {
        String code = null;
        if(multipartFiles.length>0){
            StringBuffer filePath = new StringBuffer("/jxzlpj/files/upfile/"+fileInfo.getFileCategory()+"/"+fileInfo.getRelationCode()+"/"/*+fileInfo.getFileType()+"/"*/);
            File file = new File(filePath.toString());
            if(!file.exists()){
                file.mkdirs();
            }
            for (MultipartFile multipartFile : multipartFiles) {
                // 写入文件到服务器
                code = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
                StringBuffer newFileName = new StringBuffer(code);
                newFileName.append(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")));
                filePath.append(newFileName);
                //判断文件是否是图片，如果是且图片尺寸大于600*450，则进行压缩
                BufferedImage image = ImageIO.read(multipartFile.getInputStream());
                if (image != null && image.getWidth() > 600 && image.getHeight() > 450) {
                    Thumbnails.of(image).size(600,500).toFile(new File(filePath.toString()));
                } else { //保存文件
                   /* FileOutputStream fos = new FileOutputStream(filePath.toString());
                    fos.write(multipartFile.getBytes());
                    fos.flush();
                    fos.close();*/
                    multipartFile.transferTo(new File(filePath.toString()));
                }
                // 保存文件信息到数据库
                fileInfo.setCode(code);
                fileInfo.setFileName(multipartFile.getOriginalFilename());
                fileInfo.setFilePath(new StringBuffer("/files/upfile/"+fileInfo.getFileCategory()+"/"+fileInfo.getRelationCode()+"/").append(newFileName).toString());
                boolean bool = fileService.saveFileInfo(fileInfo);
                if(!bool){
                    return JsonResult.error("上传失败");
                }
            }
        }
        return JsonResult.success("上传成功",fileInfo);
    }

    @ResponseBody
    @RequestMapping(value="/downloadFileInfo.do")
    public JsonResult<Object> downloadFileInfo(@RequestParam(value="fileName") String fileName
            ,@RequestParam(value="filePath") String filePath
            ,HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("/jxzlpj"+filePath);
        if(!file.exists()){
            return JsonResult.error("文件不存在！");
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//        response.setContentType("multipart/form-data");
        /*BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = bufferedInputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0, length);
        }
        bufferedInputStream.close();*/
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        outputStream.write(bytes);
        //
        outputStream.flush();
        outputStream.close();
        return JsonResult.success("下载成功");
    }

    /**
     * 删除文件
     * @param relationCode 根据关联的信息编号删除
     * @param code 根据文件编号删除
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/deleteFileInfo.do")
    public JsonResult<Object> deleteFileInfo(@RequestParam(value="relationCode",required = false) String relationCode
            ,@RequestParam(value="code",required = false) String code) throws Exception {
        boolean bool = fileService.deleteFileInfo(relationCode, code);
        if(!bool){
            JsonResult.error("删除失败！");
        }
        return JsonResult.success("删除成功",null);
    }
}
