package com.epam.horelov.servlet;

import com.epam.horelov.bean.RegistrationBean;
import com.epam.horelov.service.RegistrationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService = new RegistrationService();

    public RegistrationBean fillBean(HttpServletRequest request) {

        RegistrationBean bean = new RegistrationBean();

        bean.setFullName((String) request.getParameter("name"));
        bean.setEmail((String) request.getParameter("email"));
        bean.setPassword((String) request.getParameter("password"));
        bean.setPasswordConfirmation((String) request.getParameter("password-confirmation"));

        return bean;
    }

    public List<String> validateBean (RegistrationBean registrationBean){
        List<String> errors = new ArrayList<>();

        if (registrationBean.getFullName().length() > 40){
            errors.add("Name field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(registrationBean.getFullName())) {
            errors.add("Name field is empty.");
        }

        if (registrationBean.getEmail().length() > 40){
            errors.add("Email field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(registrationBean.getEmail())) {
            errors.add("Name field is empty.");
        }

        if (registrationBean.getPassword().length() > 40){
            errors.add("Password field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(registrationBean.getPassword())) {
            errors.add("Password field is empty.");
        }

        if (registrationBean.getPasswordConfirmation().length() > 40){
            errors.add("Password confirmation field content is too long. (40 characters max)");
        } else if (StringUtils.isEmpty(registrationBean.getPasswordConfirmation())) {
            errors.add("Password confirmation field is empty.");
        }

        if (registrationBean.getEmail().contains("@")) {
            errors.add("Email must contain an '@' symbol.");
        }

        if (!registrationBean.getPassword().equals(registrationBean.getPasswordConfirmation())) {
            errors.add("Passwords are not equal.");
        }

        return errors;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
        RegistrationBean bean = fillBean(req);
        List<String> errors = validateBean(bean);
        if (CollectionUtils.isNotEmpty(errors)) {
            req.setAttribute("errorList", errors);
            doGet(req, resp);
        }

        registrationService.register(bean);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
        dispatcher.forward(req, resp);
    }
}
