package com.wu.library.restcontrollers;

import com.wu.library.repositories.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String ,Object> delete (@PathVariable("id") String id)
    {
        Map<String,Object> respone = new HashMap<>();
        this.userRepository.deleteUserRole(id);
        int status = this.userRepository.delete(id);
        System.out.println("status"+status+id);
        if (status==1){
            respone.put("status",true);
            respone.put("message","delete user successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","delete user unsuccessfully");
        }
        return respone;
    }

}
