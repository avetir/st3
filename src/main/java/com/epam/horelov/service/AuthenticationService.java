package com.epam.horelov.service;

import com.epam.horelov.bean.AuthenticationBean;
import com.epam.horelov.bean.RegistrationBean;
import com.epam.horelov.dal.dao.UserDao;
import com.epam.horelov.dal.dao.UserDaoImpl;
import com.epam.horelov.entity.Roles;
import com.epam.horelov.entity.User;
import com.epam.horelov.exception.AuthenticationException;
import com.epam.horelov.exception.CustomException;
import com.epam.horelov.exception.GetSingleUserException;

import java.util.UUID;

public class AuthenticationService {

    private static final String GET_USER_BY_EMAIL_ERROR_MSG = "There's no such user; maybe, you should register first?";
    private static final String PASSWORD_INCORRECT_ERROR_MSG = "Sorry, password is incorrect. Please, try again.";
    private UserDao userDao;

    public AuthenticationService(UserDao userDao){
        this.userDao = userDao;
    }

    public User logIn(AuthenticationBean authenticationBean) throws AuthenticationException, GetSingleUserException {
        User potentialUser = userDao.getUserByEmail(authenticationBean.getEmail());

        if (potentialUser.getEmail() == null){
            throw new GetSingleUserException(GET_USER_BY_EMAIL_ERROR_MSG);
        }

        if (authenticationBean.getPassword().hashCode() == potentialUser.getPassword()){
            return potentialUser;
        } else {
            throw new AuthenticationException(PASSWORD_INCORRECT_ERROR_MSG);
        }
    }
}
