package com.wu.library.controllers;

import com.wu.library.models.Book;
import com.wu.library.models.BookReport;
import com.wu.library.models.Borrow;
import com.wu.library.models.ReturnBook;
import com.wu.library.repositories.admin.BookRepository;
import com.wu.library.repositories.admin.BorrowRepository;
import com.wu.library.repositories.admin.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/report")
public class ReportController
{

    private BookRepository bookRepository;
    private ReturnRepository returnRepository;
    private BorrowRepository borrowRepository;

    @Autowired
    public ReportController(BookRepository bookRepository, ReturnRepository returnRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.returnRepository = returnRepository;
        this.borrowRepository = borrowRepository;
    }

    @GetMapping("/book")
    public String bookReport(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;
        try{
            Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
            startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
            endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;
        }catch (Exception e){
            return "admin/Report/bookFilter";
        }


        List<Book> books = this.bookRepository.getBooksByDate(startDate,endDate);
        modelMap.addAttribute("books",books);
        modelMap.addAttribute("bookReport",bookReport);
        return "admin/Report/bookList";
    }

    @GetMapping("/return")
    public String returnReport(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;
        try{
            Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
            startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
            endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;
        }catch (Exception e){
            return "admin/Report/returnList";
        }

        List<ReturnBook> returns = this.returnRepository.getReturnByDate(startDate,endDate);
        modelMap.addAttribute("returns",returns);
        modelMap.addAttribute("bookReport",bookReport);
        return "admin/Report/returnList";
    }

    @GetMapping("/borrow")
    public String borrowReport(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;
        try{
            Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
            startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
            endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;
        }catch (Exception e){
            return "admin/Report/borrowList";
        }
        List<Borrow> borrows = this.borrowRepository.getBorrowByDate(startDate, endDate);

        modelMap.addAttribute("borrows",borrows);
        modelMap.addAttribute("bookReport",bookReport);
        return "admin/Report/borrowList";
    }

    @GetMapping("/bookFilter")
    public String bookFilter(){
        return "admin/Report/bookFilter";
    }

    @GetMapping("/returnFilter")
    public String returnFilter(){
        return "admin/Report/returnFilter";
    }

    @GetMapping("/borrowFilter")
    public String borrowFilter(){
        return "admin/Report/borrowFilter";
    }

    @GetMapping("/bookList")
    public String bookList(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;
        try{
            Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
            startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
            endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;
        }catch (Exception e){
            return "admin/Report/bookFilter";
        }

        List<Book> books = this.bookRepository.getBooksByDate(startDate,endDate);
        modelMap.addAttribute("books",books);
        return "admin/Report/bookReport";
    }

    @GetMapping("/returnReport")
    public String returnList(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;

        Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
        startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
        endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;

        List<ReturnBook> returns = this.returnRepository.getReturnByDate(startDate,endDate);
        modelMap.addAttribute("returns",returns);
        return "admin/Report/ReturnReport";
    }

    @GetMapping("/borrowReport")
    public String borrowList(@ModelAttribute BookReport bookReport, ModelMap modelMap){
        String startDate;
        String endDate;

        Integer endDay = Integer.parseInt(bookReport.getEndDay()) +1;
        startDate = bookReport.getStartYear()+"-"+bookReport.getEndMonth()+"-"+bookReport.getEndDay();
        endDate = bookReport.getEndYear()+"-"+bookReport.getEndMonth()+"-"+endDay;

        List<Borrow> borrows = this.borrowRepository.getBorrowByDate(startDate,endDate);
        System.out.println(borrows);
        System.out.println(startDate);
        System.out.println(endDate);
        modelMap.addAttribute("borrows",borrows);
        return "admin/Report/borrowReport";
    }

}
