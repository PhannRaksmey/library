package com.wu.library.controllers;

import com.wu.library.repositories.admin.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/admin/report")
    public String dashboard(ModelMap modelMap){
        Long totalBook = this.bookRepository.countBook();
        Long countAvailable = this.bookRepository.countAvailableBook();
        Long countMember = this.bookRepository.countMember();
        Long countUser = this.bookRepository.countUser();
        Long borrow = this.bookRepository.countBorrow();
        modelMap.addAttribute("totalBook",totalBook);
        modelMap.addAttribute("stock",countAvailable);
        modelMap.addAttribute("member",countMember);
        modelMap.addAttribute("user",countUser);
        modelMap.addAttribute("borrow", borrow);
        return "admin/Report/menu";
    }
}
