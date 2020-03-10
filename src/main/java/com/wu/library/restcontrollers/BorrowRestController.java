package com.wu.library.restcontrollers;

import com.wu.library.models.Borrow;
import com.wu.library.repositories.admin.BorrowRepository;
import com.wu.library.repositories.admin.UserRepository;
import com.wu.library.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowRestController {
    UserRepository userRepository;
    BorrowService borrowService;

    @Autowired
    public BorrowRestController(UserRepository userRepository, BorrowService borrowService) {
        this.userRepository = userRepository;
        this.borrowService = borrowService;
    }

    @PostMapping("/create/{bookId}/{memberId}")
    @ResponseBody
    public Map<String ,Object> insert (@PathVariable("bookId") String _bookId,@PathVariable("memberId") String _memberId, Authentication authentication)
    {   String approver = authentication.getName();
        String approverId = this.userRepository.loadUserByUsername(approver).getId();

        Map<String,Object> respone = new HashMap<>();
        int status = this.borrowService.insert(approverId,approver,_bookId,_memberId);
        System.out.println("status borrow"+status);
        if (status==1){
            this.borrowService.borrow(_bookId);
            respone.put("status",true);
            respone.put("message","borrow book successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","borrow book unsuccessfully");
        }
        return respone;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") int id ){
        Map<String,Object> respone = new HashMap<>();
        System.out.println(id);
        int status = this.borrowService.delete(id);
        System.out.println("delete borrw"+status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","delete borrow successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","delete borrow unsuccessfully");
        }
        return respone;
    }
}
