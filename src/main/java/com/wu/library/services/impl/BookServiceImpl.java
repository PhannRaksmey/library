package com.wu.library.services.impl;

import com.wu.library.models.Book;
import com.wu.library.repositories.admin.BookRepository;
import com.wu.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return this.bookRepository.getAll();
    }

    @Override
    public int insert(Book book) {
        try {
            return this.bookRepository.insert(book);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Book> getBooksByCateId(String cateId) {
        return this.bookRepository.getBooksByCateId(cateId);
    }

    @Override
    public int delete(String bookId) {
        try {
            return this.bookRepository.delete(bookId);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<Book> getById(String id) {
        return this.bookRepository.getById(id);
    }

    @Override
    public int update(Book book, String id) {
        try {
            return this.bookRepository.update(book,id);
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }

    }
}
