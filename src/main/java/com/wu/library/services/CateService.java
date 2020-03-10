package com.wu.library.services;

import com.wu.library.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CateService {
    public List<Category> getAll();
    public int insert(Category category);
    public int delete(String id);
    public int update(Category category, String id);
    public Category selectCategoryById(String id);
}
