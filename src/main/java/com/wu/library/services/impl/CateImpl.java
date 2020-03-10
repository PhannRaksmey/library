package com.wu.library.services.impl;

import com.wu.library.models.Category;
import com.wu.library.repositories.admin.CateRepository;
import com.wu.library.services.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateImpl implements CateService {
    @Autowired
    CateRepository cateRepository;

    @Override
    public List<Category> getAll() {
        return this.cateRepository.getAll();
    }

    @Override
    public int insert(Category category) {
        return this.cateRepository.insert(category);
    }

    @Override
    public int delete(String id) {
        try{
            return this.cateRepository.delete(id);
        }catch (DataIntegrityViolationException e){
            return 0;
        }

    }

    @Override
    public int update(Category category, String id) {
        return this.cateRepository.update(category,id);
    }

    @Override
    public Category selectCategoryById(String id) {
        List<Category> categoryList = this.cateRepository.selectCategoryById(id);
        Category category = new Category();
        category = categoryList.get(0);
        return category;
    }
}
