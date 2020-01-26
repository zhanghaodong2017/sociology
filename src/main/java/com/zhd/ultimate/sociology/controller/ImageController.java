//package com.zhd.ultimate.sociology.controller;
//
//import com.zhd.ultimate.sociology.utils.FileUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//
//
//@Slf4j(topic = "error")
//@Controller
//public class ImageController {
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//
//    @RequestMapping("/image/{imageId}")
//    public void show(@PathVariable String imageId, HttpServletResponse response) {
//        try {
//            ServletOutputStream out = response.getOutputStream();
//            File file = new File(uploadDir, imageId);
//            byte[] bytes = FileUtils.toByteArrayNIO(file);
//            out.write(bytes);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            log.error("获取图片异常！{}", e);
//        }
//    }
//
//}
