package com.wu.library.controllers;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wu.library.models.Category;
import com.wu.library.repositories.admin.CateRepository;
import com.wu.library.services.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/")
public class CateController {

    @Autowired
    private CateService cateService;

    @GetMapping("/cate")
    public String getAll(ModelMap modelMap){
        List<Category> categories = this.cateService.getAll();
        modelMap.addAttribute("cates",categories);
        return "/admin/Category/index";
    }

    @GetMapping("/category/update/{id}")
    public String update(ModelMap modelMap, @PathVariable("id") String id){
        Category category = this.cateService.selectCategoryById(id);
        System.out.println("==========Cate"+category);
        modelMap.addAttribute("cate",category);
        return "admin/Category/update";
    }

    @PostMapping("/cate/update/submit")
    public String updateSubmit(@ModelAttribute Category _cate)
    {
        System.out.println(_cate);
        this.cateService.update(_cate,_cate.getId());
        return"redirect:/admin/cate";
    }
}
