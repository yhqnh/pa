package com.ikamobile.pa.clientsapi.controller;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.clientsapi.controller.param.ClientCrashLogParam;
import com.ikamobile.pa.clientsapi.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by guest on 16/7/28.
 */
@RestController
@Slf4j
public class ClientCrashLogController {

    @Value("${crash.log.upload.dir}")
    private String crashLogDir;

    @RequestMapping("/crashLog/upload")
    public BaseResponse uploadCrashFile(@RequestParam(value = "file" ,required = false) MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();
        String saveFileName = UUID.randomUUID().toString()+"@"+((CommonsMultipartFile) file).getFileItem().getName();
        String saveFilePrentPath = crashLogDir.endsWith(File.separator) ? crashLogDir : crashLogDir+File.separator;
        File fileTmp = new File(saveFilePrentPath+saveFileName);

        FileOutputStream fileOutputStream = new FileOutputStream(fileTmp);

        boolean flag = true;
        byte[] buff = new byte[1024];
        while (flag){

            int readByteNum  = inputStream.read(buff);
            if(readByteNum > 0){
                fileOutputStream.write(buff,0,readByteNum);
            }
            flag = readByteNum > 0;
        }
        return new BaseResponse();
    }

    @RequestMapping(value = "/crashLog/log" ,method = RequestMethod.POST)
    public BaseResponse uploadCrashFile(@Validated ClientCrashLogParam crashLogParam) throws IOException {
        log.error("client crashed ,the crashInfo==>\n{}", JSON.toJSONString(crashLogParam));
        return new BaseResponse();
    }
}
