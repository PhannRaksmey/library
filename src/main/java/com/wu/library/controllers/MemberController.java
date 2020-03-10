package com.wu.library.controllers;

import com.wu.library.LibraryApplication;
import com.wu.library.models.Category;
import com.wu.library.models.Member;
import com.wu.library.models.User;
import com.wu.library.services.MemberService;
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
@RequestMapping("admin/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/all")
    public String getAll(ModelMap modelMap)
    {
        List<Member> memberList = this.memberService.getAll();
        modelMap.addAttribute("members",memberList);
        return "admin/member/index";
    }

    @GetMapping("create")
    public String create(ModelMap model) {
        model.addAttribute("member", new Member());
        return "admin/member/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, ModelMap modelMap){
        Member member= this.memberService.findOne(id);
        modelMap.addAttribute("member",member);
        return "/admin/member/update";
    }

    @PostMapping("create/submit")
    public String createSubmit(@ModelAttribute @Valid Member member, MultipartFile file, ModelMap modelMap) {
        System.out.println("========"+file);
        for (Member member1 : this.memberService.getAll()) {
            if (member.getId().equals(member1.getId())) {
                modelMap.addAttribute("error", true);
                modelMap.addAttribute("errorType", "id");
                return "admin/member/create";
            }

        }
        if (file == null)
            return null;

        File path = new File("/Users/smey/Documents/wu");

        if (!path.exists())
            path.mkdir();

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf('.') + 1);


        filename = UUID.randomUUID() + "." + extension;

        try {
            Files.copy(file.getInputStream(), Paths.get("/Users/smey/Documents/wu", filename));
        } catch (IOException e) {
            e.printStackTrace();
        }


        member.setImg("/image-wu/" + filename);
        this.memberService.save(member);
        return "redirect:/admin/member/all";

    }

    @PostMapping("update/submit")
    public String updateSubmit(@ModelAttribute @Valid Member member, MultipartFile file, ModelMap modelMap) {
//        for (Member member1 : this.memberService.getAll()) {
//            if (member.getId().equals(member1.getId())) {
//                modelMap.addAttribute("error", true);
//                modelMap.addAttribute("errorType", "id");
//                return "admin/member/update";
//            }
//
//        }
        if (file == null)
            return null;

        File path = new File("/Users/smey/Documents/wu");

        if (!path.exists())
            path.mkdir();

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        filename = UUID.randomUUID() + "." + extension;

        try {
            Files.copy(file.getInputStream(), Paths.get("/Users/smey/Documents/wu", filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        if (filename.contains("PNG") || filename.contains(".png") || filename.contains(".jpg")){
            member.setImg("/image-wu/" + filename);
            this.memberService.updatePicture(member, member.getId());
        }else {
            this.memberService.update(member,member.getId());
        }

        return "redirect:/admin/member/all";

    }


}
