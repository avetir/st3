package com.epam.horelov.contextlistener;

import com.epam.horelov.dal.dao.*;
import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.dal.dbcp.ConnectionPoolImpl;
import com.epam.horelov.service.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    public static final String REGISTRATION_SERVICE = "registration_service";
    public static final String AUTHENTICATION_SERVICE = "authentication_service";
    public static final String ROOM_SERVICE = "room_service";
    public static final String BOOKING_SERVICE = "booking_service";
    public static final String REQUEST_PROCESSING_SERVICE = "request_processing_service";
    public static final String CONNECTION_POOL = "connection_pool";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        ConnectionPool connectionPool = new ConnectionPoolImpl();

        UserDao userDao = new UserDaoImpl(connectionPool);
        RegistrationService registrationService = new RegistrationService(userDao);
        AuthenticationService authenticationService = new AuthenticationService(userDao);

        RoomDao roomDao = new RoomDaoImpl(connectionPool);
        RoomService roomService = new RoomService(roomDao);

        BookRequestDao bookRequestDao = new BookRequestDaoImpl(connectionPool);
        BookingService bookingService = new BookingService(bookRequestDao);
        RequestsProcessingService requestsProcessingService = new RequestsProcessingService(bookRequestDao);

        servletContext.setAttribute(CONNECTION_POOL, connectionPool);
        servletContext.setAttribute(REGISTRATION_SERVICE, registrationService);
        servletContext.setAttribute(AUTHENTICATION_SERVICE, authenticationService);
        servletContext.setAttribute(ROOM_SERVICE, roomService);
        servletContext.setAttribute(BOOKING_SERVICE, bookingService);
        servletContext.setAttribute(REQUEST_PROCESSING_SERVICE, requestsProcessingService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
