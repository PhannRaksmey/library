package com.wu.library.services;

import com.wu.library.models.ReturnBook;

import java.util.List;

public interface ReturnService {
    public List<ReturnBook> getAll();
    public int returnBook(String bookId);
    public int insert(String _approverId, String _approver, String _bookId, String _memberId);
    public int delete(int _id);
}
