package com.epam.horelov.servlet;

import com.epam.horelov.service.RequestsProcessingService;
import com.epam.horelov.service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requests")
public class RequestsProcessingServlet extends HttpServlet{

    private RequestsProcessingService requestsProcessingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestsProcessingService = (RequestsProcessingService) getServletContext().getAttribute("request_processing_service");
        req.setAttribute("requests", requestsProcessingService.getAllRequests());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/requests.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
