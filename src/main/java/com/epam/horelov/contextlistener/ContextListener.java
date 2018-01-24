package com.epam.horelov.contextlistener;

import com.epam.horelov.dal.dao.UserDaoImpl;
import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.dal.dbcp.ConnectionPoolImpl;
import com.epam.horelov.service.AuthenticationService;
import com.epam.horelov.service.RegistrationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public static final String REGISTRATION_SERVICE = "REGISTRATION_SERVICE";
//    public static final String AUTHENTICATION_SERVICE = "AUTHENTICATION_SERVICE";
    public static final String CONNECTION_POOL = "CONNECTION_POOL";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        UserDaoImpl userDao = new UserDaoImpl();
        RegistrationService registrationService = new RegistrationService(userDao);
//        AuthenticationService authenticationService = new AuthenticationService(userDao);

        servletContext.setAttribute(REGISTRATION_SERVICE, registrationService);
//        servletContext.setAttribute(AUTHENTICATION_SERVICE, authenticationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
