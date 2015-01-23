package com.my.biz.sm.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.biz.sm.commons.page.Page;
import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.commons.token.Token;
import com.my.biz.sm.entity.UserInfo;
import com.my.biz.sm.service.IUserCacheManager;
import com.my.biz.sm.service.UserService;

@Controller
public class UserInfoController
{
    private final static Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private IUserCacheManager cacheManager;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(Page p,UserInfo userInfo, Model model)
    {
        PageParam<UserInfo> param = new PageParam<UserInfo>(userInfo,p.getPageNo(), p.getPageSize());
        PageData<UserInfo> data = userService.findUserInfo(param);
        model.addAttribute("data", data);
        return "index";
    }
    
    @RequestMapping(value = "/addUser")
    @Token(save=true) 
    public String addUser(Model model)
    {
        return "user/add_user";
    }
    
    @RequestMapping(value = "/saveUserInfo")
    @Token(remove=true)
    public String saveUserInfo(Model model,UserInfo userInfo)
    {
        System.out.println("---->"+userInfo.getUserName());
        userService.addUserInfo(userInfo);
        return "redirect:/index "; 
    }
    
    @RequestMapping(value = "/viewUserInfo")
    public String viewUserInfo(Integer id, Model model)
    {
        model.addAttribute("user", userService.getUserInfoById(id));
        model.addAttribute("count", cacheManager.getUserLoginCount(id)) ;
        model.addAttribute("hincr", cacheManager.getUserReportCount(Long.valueOf(id)));
        return "user/user_view";
    }
    
    @RequestMapping(value = "/user/hincrCount")
    @ResponseBody
    public Object hincrCount(Integer id, Model model)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        int count =cacheManager.increUserReportCount(Long.valueOf(id), 1);
        dataMap.put("msg", "计数成功！");
        dataMap.put("count", count);
        return dataMap;
    }
    @RequestMapping(value = "/user/delUserInfo")
    @ResponseBody
    public Object delUserInfo(Integer id, Model model)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        UserInfo userInfo=userService.getUserInfoById(id);
        cacheManager.flushUser(userInfo, true);
        userService.delUserInfo(id);
        dataMap.put("msg", "删除成功！");
        return dataMap;
    }
    
    
}
