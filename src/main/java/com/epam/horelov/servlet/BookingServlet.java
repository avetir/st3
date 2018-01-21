package com.epam.horelov.servlet;

import com.epam.horelov.entity.*;
import com.epam.horelov.service.BookingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private static final String A_PLUS_ROOM_CLASS = "A+";
    private static final String A_ROOM_CLASS = "A";
    private static final String B_ROOM_CLASS = "B";

    public BookRequest fillRequest(HttpServletRequest request){
        BookRequest bookRequest = new BookRequest();

        bookRequest.setId(UUID.randomUUID().toString());
        bookRequest.setUserId(((User)request.getSession().getAttribute("user")).getId());
        bookRequest.setPlacesNumber(Integer.parseInt(request.getParameter("place-number")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        bookRequest.setDateTimeIn(LocalDateTime.parse(request.getParameter("date-from"), formatter));
        bookRequest.setDateTimeOut(LocalDateTime.parse(request.getParameter("date-to"), formatter));

        if (A_PLUS_ROOM_CLASS.equals(request.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.APLUS);
        } else if (A_ROOM_CLASS.equals(request.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.A);
        } else if (B_ROOM_CLASS.equals(request.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.B);
        }

        bookRequest.setStatus(RequestStatus.PENDING);

        return bookRequest;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/booking.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookingService bookingService = new BookingService();
//        bookingService.book()
        String debugDate = req.getParameter("date-from");
        super.doPost(req, resp);
    }
}
