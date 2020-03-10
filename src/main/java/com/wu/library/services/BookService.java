package com.wu.library.services;

import com.wu.library.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {

    public List<Book> getAll();

    public int insert(Book book);

    public List<Book> getBooksByCateId(String cateId);

    public int delete(String bookId);

    public List<Book> getById(String id);

    public int update (Book book, String id);
}
