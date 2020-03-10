package com.wu.library.controllers;

import com.wu.library.models.ReturnBook;
import com.wu.library.services.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReturnController {
    @Autowired
    ReturnService returnService;

    @GetMapping("/return")
    public String borrow(ModelMap modelMap)
    {
        List<ReturnBook> returns = this.returnService.getAll();
        modelMap.addAttribute("returns",returns);
        return "admin/return/index";
    }
}
