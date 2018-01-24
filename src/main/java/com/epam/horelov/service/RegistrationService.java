package com.epam.horelov.service;

import com.epam.horelov.bean.RegistrationBean;
import com.epam.horelov.dal.dao.UserDao;
import com.epam.horelov.dal.dao.UserDaoImpl;
import com.epam.horelov.entity.Roles;
import com.epam.horelov.entity.User;

import java.util.UUID;

public class RegistrationService {

    private UserDao userDao;

    public RegistrationService(UserDaoImpl userDaoImpl){
        userDao = userDaoImpl;
    }

    private User createUser(RegistrationBean registrationBean) {
        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setFullName(registrationBean.getFullName());
        user.setEmail(registrationBean.getEmail());
        user.setPassword(registrationBean.getPassword().hashCode());
        user.setRole(Roles.USER);

        return user;
    }

    public void register(RegistrationBean registrationBean) {
        User newUser = createUser(registrationBean);
        userDao.insertUser(newUser);
    }
}
