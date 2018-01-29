package com.epam.horelov.servlet;

import com.epam.horelov.service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet{

    private RoomService roomService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomService = (RoomService) getServletContext().getAttribute("room_service");
        req.setAttribute("rooms", roomService.getAllRooms());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/rooms.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
