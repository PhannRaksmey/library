package com.wu.library.services.impl;

import com.wu.library.models.ReturnBook;
import com.wu.library.repositories.admin.ReturnRepository;
import com.wu.library.services.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServieImpl implements ReturnService {

    @Autowired
    ReturnRepository returnRepository;
    @Override
    public List<ReturnBook> getAll() {
        return this.returnRepository.getAll();
    }

    @Override
    public int returnBook(String bookId) {
        return this.returnRepository.returnBook(bookId);
    }

    @Override
    public int insert(String _approverId,String _approver, String _bookId, String _memberId) {
        try {
            return this.returnRepository.insert(_approverId,_approver,_bookId,_memberId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int _id) {
        try {
            return this.returnRepository.delete(_id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}
