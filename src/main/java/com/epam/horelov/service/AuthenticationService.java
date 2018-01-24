package com.epam.horelov.service;

import com.epam.horelov.bean.AuthenticationBean;
import com.epam.horelov.bean.RegistrationBean;
import com.epam.horelov.dal.dao.UserDao;
import com.epam.horelov.dal.dao.UserDaoImpl;
import com.epam.horelov.entity.Roles;
import com.epam.horelov.entity.User;
import com.epam.horelov.exception.AuthenticationException;

import java.util.UUID;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(){
        userDao = new UserDaoImpl();
    }

    public User logIn(AuthenticationBean authenticationBean) throws AuthenticationException {
        User potentialUser = userDao.getUserByEmail(authenticationBean.getEmail());
        if (authenticationBean.getPassword().hashCode() == potentialUser.getPassword()){
            return potentialUser;
        } else {
            throw new AuthenticationException("Something went wrong. Please, try again");
        }
    }
}
