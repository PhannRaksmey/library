package com.wu.library.restcontrollers;

import com.wu.library.models.Member;
import com.wu.library.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/member")
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String ,Object> insert (@RequestBody Member member)
    {
        Map<String,Object> respone = new HashMap<>();
        int status = this.memberService.save(member);
        System.out.println("status"+status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","create member successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","create member unsuccessfully");
        }
        return respone;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Map<String ,Object> update (@RequestBody Member member, @PathVariable("id") String id)
    {
        Map<String,Object> respone = new HashMap<>();
        int status = this.memberService.update(member,id);
        System.out.println("status"+status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","update memeber successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","update member unsuccessfully");
        }
        return respone;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String ,Object> delete (@PathVariable("id") String id)
    {
        Map<String,Object> respone = new HashMap<>();
        int status = this.memberService.delete(id);
        System.out.println("status"+status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","update memeber successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","update member unsuccessfully");
        }
        return respone;
    }

}
