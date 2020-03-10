package com.wu.library.controllers;

import com.wu.library.LibraryApplication;
import com.wu.library.models.User;
import com.wu.library.repositories.admin.UserRepository;
import com.wu.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/user/")
public class UserController {

    UserRepository userRepository;
    UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAll(ModelMap modelMap)
    {
        List<User> userList = this.userService.getAll();
        System.out.println("======user list====="+userList);
        modelMap.addAttribute("users",userList);
        return "admin/User/index";
    }

    @GetMapping("create")
    public String create(ModelMap model){
        model.addAttribute("user", new User());
        return "admin/User/create";
    }

    @PostMapping("create/submit")
    public String createSubmit(@ModelAttribute @Valid User user, MultipartFile file, @RequestParam("role") String role, ModelMap modelMap){

        System.out.println("User"+user);
        for (User admin: this.userRepository.getAllUser()) {
            if (admin.getUsername().equals(user.getUsername()) ){
                modelMap.addAttribute("error", true);
                modelMap.addAttribute("errorType","email");
                return "admin/user/create";
            }

        }

        if(file == null)
             return null;

         File path = new File("/Users/smey/Documents/wu");

         if(!path.exists())
             path.mkdir();

         String filename = file.getOriginalFilename();
         String extension = filename.substring(filename.lastIndexOf('.')+1);;

         filename = UUID.randomUUID() + "." + extension;

        try {
            Files.copy(file.getInputStream(), Paths.get("/Users/smey/Documents/wu",filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setProfileImg("/image-wu/"+filename);
        String pass = LibraryApplication.getEncoder().encode(user.getPassword());
        user.setPassword(pass);

        if (this.userService.insert(user) == 0){
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("errorType","idcard");
            return "admin/user/create";
        }
        System.out.printf("=======upload image====== "+ user);
//        this.userService.insert(user);
        this.userRepository.insertUserRole(role,user.getId());
        return "redirect:/admin/user/all";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") String id, ModelMap model){
        List<User> users = this.userService.getAll();
        User user = null;
        for(User user1 : users){
            if (id.equals(user1.getId())){
                user=user1;
                break;
            }
        }

        model.addAttribute("user",user);
        return "admin/User/update";
    }

    @PostMapping("update/submit")
    public String updateSubmit(@ModelAttribute @Valid User user, MultipartFile file, @RequestParam("role") String role, ModelMap modelMap){

        System.out.println("User"+user);
        for (User admin: this.userRepository.getAllUserUpdate(user.getId())) {
            if (admin.getUsername().equals(user.getUsername()) ){
                modelMap.addAttribute("error", true);
                modelMap.addAttribute("errorType","email");
                return "admin/user/update";
            }

        }

        if(file == null)
            return null;

        File path = new File("/Users/smey/Documents/wu");

        if(!path.exists())
            path.mkdir();

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf('.')+1);;

        filename = UUID.randomUUID() + "." + extension;

        try {
            Files.copy(file.getInputStream(), Paths.get("/Users/smey/Documents/wu",filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (filename.contains("PNG") || filename.contains(".jpg")){
            user.setProfileImg("/image-wu/"+filename);

            this.userService.update(user,user.getId());
        }else {
           this.userService.updateNopicture(user,user.getId());
        }

        System.out.println("======file name======" + filename);

//        String pass = LibraryApplication.getEncoder().encode(user.getPassword());
//        user.setPassword(pass);

        this.userRepository.updateUserRole(user.getId(),role);
        return "redirect:/admin/user/all";
    }

}
