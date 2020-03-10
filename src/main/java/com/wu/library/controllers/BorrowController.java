package com.wu.library.controllers;

import com.wu.library.models.Borrow;
import com.wu.library.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BorrowController {

    @Autowired
    BorrowService borrowService;
    @GetMapping("/borrow")
    public String borrow(ModelMap modelMap)
    {
        List<Borrow> borrows = this.borrowService.getAll();
        modelMap.addAttribute("borrows",borrows);
        return "admin/borrow/index";
    }
}
