package com.epam.horelov.servlet;

import com.epam.horelov.bean.AuthenticationBean;
import com.epam.horelov.bean.RegistrationBean;
import com.epam.horelov.exception.AuthenticationException;
import com.epam.horelov.exception.CustomException;
import com.epam.horelov.exception.GetSingleUserException;
import com.epam.horelov.service.AuthenticationService;
import com.epam.horelov.service.RegistrationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {


    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final int MAX_FIELD_CONTENT_LENGTH = 40;

    private AuthenticationService authenticationService;

    public AuthenticationBean fillBean(HttpServletRequest request) {

        AuthenticationBean bean = new AuthenticationBean();

        bean.setEmail(request.getParameter("email"));
        bean.setPassword(request.getParameter("password"));

        return bean;
    }

    public List<String> validateBean (AuthenticationBean authenticationBean){
        List<String> errors = new ArrayList<>();

        if (authenticationBean.getEmail().length() > MAX_FIELD_CONTENT_LENGTH){
            errors.add("Email field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(authenticationBean.getEmail())) {
            errors.add("Name field is empty.");
        }

        if (authenticationBean.getPassword().length() > MAX_FIELD_CONTENT_LENGTH){
            errors.add("Password field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(authenticationBean.getPassword())) {
            errors.add("Password field is empty.");
        }

        if (!authenticationBean.getEmail().contains("@")) {
            errors.add("Email must contain an '@' symbol.");
        }

        return errors;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        authenticationService = (AuthenticationService) servletContext.getAttribute("authentication_service");

        HttpSession session = req.getSession();
        AuthenticationBean bean = fillBean(req);
        List<String> errors = validateBean(bean);

        if (CollectionUtils.isNotEmpty(errors)) {
            req.setAttribute("errorList", errors);
            doGet(req, resp);
            return;
        }
        try {
            session.setAttribute("user", authenticationService.logIn(bean));
            resp.sendRedirect("/home");
        } catch (AuthenticationException ex){
            errors.add(ex.getMessage());
            req.setAttribute("errorList", errors);
            doGet(req, resp);
        } catch (GetSingleUserException ex){
            errors.add(ex.getMessage());
            req.setAttribute("errorList", errors);
            doGet(req, resp);
        }
//          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher();
//          dispatcher.forward(req, resp);
    }
}
