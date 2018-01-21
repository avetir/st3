package com.epam.horelov.dal.dao;

import com.epam.horelov.entity.User;

public interface UserDao {

    public User getUserByEmail(String email);
    public void insertUser(User user);

}
