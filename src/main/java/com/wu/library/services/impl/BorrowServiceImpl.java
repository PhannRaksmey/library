package com.wu.library.services.impl;

import com.wu.library.models.Borrow;
import com.wu.library.repositories.admin.BorrowRepository;
import com.wu.library.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Override
    public int delete(int _id) {
        return this.borrowRepository.delete(_id);
    }

    @Override
    public int insert(String approverId, String approver, String bookId, String memberId) {
        try {
            return this.borrowRepository.insert(approverId, approver,bookId,memberId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public List<Borrow> getAll() {
        return this.borrowRepository.getAll();
    }

    @Override
    public int borrow(String bookId) {
        return this.borrowRepository.borrow(bookId);
    }
}
