package com.wu.library.controllers;

import com.sun.security.auth.UserPrincipal;
import com.wu.library.models.Book;
import com.wu.library.models.Category;
import com.wu.library.repositories.admin.BookRepository;
import com.wu.library.repositories.admin.CateRepository;
import com.wu.library.services.BookService;
import com.wu.library.services.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("/admin/book")
public class BookController {

    private CateService cateService;
    private BookService bookService;

    @Autowired
    public BookController(CateService cateService, BookService bookService) {
        this.cateService = cateService;
        this.bookService = bookService;
    }


    @GetMapping("/all")
    public String getAll(ModelMap modelMap, Authentication authentication){
        //System.out.println("user"+authentication.getName());
        List<Category> categories = this.cateService.getAll();
        modelMap.addAttribute("cate",categories);
        List<Book> bookList = this.bookService.getAll();
        modelMap.addAttribute("books",bookList);

        return "/index";
    }

    @GetMapping("/cate/{id}")
    public String getBooksByCateId(@PathVariable("id") String id, ModelMap modelMap){
        List<Category> categories = this.cateService.getAll();
        modelMap.addAttribute("cate",categories);
        List<Book> bookList = this.bookService.getBooksByCateId(id);
        modelMap.addAttribute("books",bookList);

        return "/admin/book/catebook";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, ModelMap modelMap){
        List<Category> categories = this.cateService.getAll();
        modelMap.addAttribute("cate",categories);
        List<Book> bookList = this.bookService.getById(id);
        modelMap.addAttribute("books",bookList);
        return "/admin/book/update";
    }

    @PostMapping("/create")
    public String create( @Valid Book book , Model model)
    {
        model.addAttribute("bookes", new Book());
        this.bookService.insert(book);
        return "redirect:/admin/index";
    }
}
