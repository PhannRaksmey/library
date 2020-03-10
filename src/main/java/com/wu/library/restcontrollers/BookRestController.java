package com.wu.library.restcontrollers;

import com.wu.library.models.Book;
import com.wu.library.services.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String ,Object> insert (@RequestBody Book book)
    {
        Map<String,Object> respone = new HashMap<>();
        int status = this.bookService.insert(book);
        if (status==1){
            respone.put("status",true);
            respone.put("message","create book successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","create book unsuccessfully");
        }
        return respone;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Map<String ,Object> update (Principal _principal, @RequestBody Book book, @PathVariable("id") String id)
    {
        System.out.println(_principal.getName());
        Map<String,Object> respone = new HashMap<>();
        int status = this.bookService.update(book,id);
        System.out.println("status"+status);
        if (status==1){
            respone.put("status",true);
            respone.put("message","update book successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","update book unsuccessfully");
        }
        return respone;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String ,Object> delete (@PathVariable("id") String id)
    {
        Map<String,Object> respone = new HashMap<>();
        int status = this.bookService.delete(id);
        System.out.println("status"+status+id);
        if (status==1){
            respone.put("status",true);
            respone.put("message","delete book successfully");
            return respone;
        }else {
            respone.put("status",false);
            respone.put("message","delete book unsuccessfully");
        }
        return respone;
    }




}
