package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.UserInfo;
import com.zhd.ultimate.sociology.service.UserInfoService;
import com.zhd.ultimate.sociology.utils.LogUtils;
import com.zhd.ultimate.sociology.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Value("${file.upload-dir}")
    private String uploadDir;


    @InitBinder("userInfo")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userInfo.");
    }

    @RequestMapping("/queryAllUserInfo")
    public String queryAllUserInfo(Model model) {
        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        model.addAttribute("userInfoList", userInfoList);
        return "userInfo/userInfo-query";
    }

    @RequestMapping("/showAdd")
    public String showAdd() {
        return "userInfo/userInfo-add";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("guid") String guid, Model model) {
        UserInfo userInfo = userInfoService.queryUserInfo(guid);
        model.addAttribute("userInfo", userInfo);
        return "userInfo/userInfo-update";
    }

    @RequestMapping("/add")
    public String add(UserInfo userInfo, Model model) {
        try {
            if (userInfo.getHeadImgFile() != null) {
                userInfo.setHeadImg(Utils.uuid() + ".jpg");
                userInfo.getHeadImgFile().transferTo(new File(uploadDir, userInfo.getHeadImg()));
            }else{
                System.out.println("未选择图片");
            }
        } catch (IOException e) {
            LogUtils.ERROR.info("上传图片错误： ", e);
        }
        userInfoService.add(userInfo);
        return queryAllUserInfo(model);
    }

    @RequestMapping("/update")
    public String update(UserInfo userInfo, Model model) {
        userInfoService.update(userInfo);
        return queryAllUserInfo(model);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("guid") String guid, Model model) {
        userInfoService.delete(guid);
        return queryAllUserInfo(model);
    }

}
