//package com.wu.library.controllers;
//
//import com.wu.library.models.User;
//
//import com.wu.library.repositories.admin.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.ModelMap;
//@Controller
//public class AdminController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/admin")
//    public String dba(ModelMap model){
////        User user = this.userRepository.loadUserByUsername("admin");
////
////        System.out.println("===============get============"+user);
////        model.addAttribute("user",user);
//        User user = this.userRepository.loadUserByUsername("admin");
//        model.addAttribute("user",user);
//        return "/home";
//    }
//
//}
