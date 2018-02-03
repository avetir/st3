package com.epam.horelov.servlet;

import com.epam.horelov.entity.RequestStatus;
import com.epam.horelov.service.RequestsProcessingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestReject")
public class RejectRequestServlet extends HttpServlet {

    private RequestsProcessingService requestsProcessingService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        requestsProcessingService = (RequestsProcessingService) getServletContext().getAttribute("request_processing_service");
        String requestId = req.getParameter("requestId");
        requestsProcessingService.updateRequestStatus(RequestStatus.REJECTED, requestId);

        resp.sendRedirect("/requests");

    }
}
