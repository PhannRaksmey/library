package com.wu.library.services;

import com.wu.library.models.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getUserById(String id);
    public int update(User user, String id);
    public int updateNopicture(User user, String id);
    public int delete(String id);
    public int insert(User user);
}
