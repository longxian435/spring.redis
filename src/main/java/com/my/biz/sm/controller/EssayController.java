package com.my.biz.sm.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.biz.sm.commons.page.Page;
import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.entity.EssayInfo;
import com.my.biz.sm.entity.UserInfo;
import com.my.biz.sm.service.IEssayCacheManager;
import com.my.biz.sm.service.IEssayService;

@Controller
@RequestMapping(value = "/essay")
public class EssayController
{
    @Autowired
    private IEssayCacheManager essayCacheManager;
    
    @Autowired
    private IEssayService essayService;

    @RequestMapping(value = "/list")
    public String index(Page p,EssayInfo essayInfo, Model model)
    {
        PageParam<EssayInfo> param = new PageParam<EssayInfo>(essayInfo,p.getPageNo(), p.getPageSize());
        PageData<EssayInfo> data = essayService.findEssayInfo(param);
        model.addAttribute("data", data);
        return "essay/list";
    }
    
    @RequestMapping(value = "/addEssay")
    public String addEssay(Model model)
    {
        return "essay/add_essay";
    }
    
    @RequestMapping(value = "/saveEssayInfo")
    public String saveEssayInfo(Model model,EssayInfo essayInfo)
    {
        System.out.println("---->"+essayInfo.getTitle());
        essayService.addEssayInfo(essayInfo);
        return "redirect:/essay/list ";
    }
    @RequestMapping(value = "/cacheEssayList")
    public String cacheEssayList(Model model,EssayInfo essayInfo,Page p)
    {
        PageParam<EssayInfo> param = new PageParam<EssayInfo>(essayInfo,p.getPageNo(), p.getPageSize());
        Set<Long> setIds=essayCacheManager.getEssayInfoSetId(1L, param, false);
        for(Long s : setIds)
        {
            System.out.println(s);
        }
        return "essay/cache_list"; 
    }
    
    
    
    
}
