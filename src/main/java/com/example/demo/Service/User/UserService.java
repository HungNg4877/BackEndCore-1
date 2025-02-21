package com.example.demo.Service.User;

import com.example.demo.Entity.User;

public interface UserService {
    public User getCurrentUser();
    void softDeleteUser(String userId);
}
