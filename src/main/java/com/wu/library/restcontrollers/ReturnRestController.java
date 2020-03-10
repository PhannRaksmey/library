package com.wu.library.restcontrollers;

import com.wu.library.repositories.admin.UserRepository;
import com.wu.library.services.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/return")
public class ReturnRestController{

    UserRepository userRepository;
    ReturnService returnService;

    @Autowired
    public ReturnRestController(UserRepository userRepository, ReturnService returnService) {
        this.userRepository = userRepository;
        this.returnService = returnService;
    }
    @PostMapping("/create/{bookId}/{memberId}")
    @ResponseBody
    public Map<String ,Object> insert (@PathVariable("bookId") String _bookId, @PathVariable("memberId") String _memberId, Authentication authentication)
    {

        String approver = authentication.getName();
        String approverId = this.userRepository.loadUserByUsername(approver).getId();

        Map<String,Object> respone = new HashMap<>();
        int status = this.returnService.insert(approverId,approver,_bookId,_memberId);
        System.out.println("status"+status);
        if (status==1){
            this.returnService.returnBook(_bookId);
            respone.put("status",true);
            respone.put("message","return book successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","return book unsuccessfully");
        }
        return respone;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") int id ){
        Map<String,Object> respone = new HashMap<>();
        int status = this.returnService.delete(id);
        System.out.println(status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","delete return successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","delete return unsuccessfully");
        }
        return respone;
    }
}
