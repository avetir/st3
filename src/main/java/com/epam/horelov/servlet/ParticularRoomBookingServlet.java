package com.epam.horelov.servlet;

import com.epam.horelov.entity.Room;
import com.epam.horelov.service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookingroom")
public class ParticularRoomBookingServlet extends HttpServlet {

    private RoomService roomService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomService = (RoomService) getServletContext().getAttribute("room_service");
        int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
        Room room = roomService.getRoomByNumber(roomNumber);
        req.setAttribute("room", room);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/booking.jsp");
        requestDispatcher.forward(req, resp);
    }

}
