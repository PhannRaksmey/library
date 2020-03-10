package com.wu.library.restcontrollers;

import com.wu.library.models.Category;
import com.wu.library.services.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/cate")
public class CateRestController {

    private CateService cateService;

    @Autowired
    public CateRestController(CateService cateService) {
        this.cateService = cateService;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody Category category ){
        Map<String,Object> respone = new HashMap<>();
        int status = this.cateService.insert(category);
        if (status==1){
            respone.put("status",true);
            respone.put("message","create category successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","create category unsuccessfully");
        }
        return respone;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") String id ){
        Map<String,Object> respone = new HashMap<>();
        int status = this.cateService.delete(id);
        System.out.println(status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","delete category successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","delete category unsuccessfully");
        }
        return respone;
    }




}
