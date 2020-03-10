package com.wu.library.services;

import com.wu.library.models.Borrow;

import java.util.List;

public interface BorrowService {
    public List<Borrow> getAll();
    public int borrow(String bookId);
    public int delete(int _id);
    public int insert(String approverId,String approver, String bookId, String memberId);
}
