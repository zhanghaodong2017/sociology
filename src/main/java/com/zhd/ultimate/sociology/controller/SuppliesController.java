package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.Supplies;
import com.zhd.ultimate.sociology.service.SuppliesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-28 17:17
 */
@Controller
@RequestMapping("/supplies")
public class SuppliesController {

    @Autowired
    private SuppliesService suppliesService;


    @InitBinder("supplies")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("supplies.");
    }

    @RequestMapping("/queryAllSupplies")
    public String queryAllSupplies(Model model) {
        List<Supplies> suppliesList = suppliesService.queryAllSupplies();
        model.addAttribute("suppliesList", suppliesList);
        return "supplies/supplies-query";
    }

    @RequestMapping("/showAdd")
    public String showAdd() {
        return "supplies/supplies-add";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("guid") String guid, Model model) {
        Supplies supplies = suppliesService.querySupplies(guid);
        model.addAttribute("supplies", supplies);
        return "supplies/supplies-update";
    }

    @RequestMapping("/add")
    public String add(Supplies supplies, Model model) {
        suppliesService.add(supplies);
        return queryAllSupplies(model);
    }

    @RequestMapping("/update")
    public String update(Supplies supplies, Model model) {
        if (StringUtils.isNotBlank(supplies.getGuid())) {
            suppliesService.update(supplies);
        }
        return queryAllSupplies(model);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("guid") String guid, Model model) {
        suppliesService.delete(guid);
        return queryAllSupplies(model);
    }

}
