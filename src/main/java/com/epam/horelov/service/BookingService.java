package com.epam.horelov.service;

import com.epam.horelov.dal.dao.BookRequestDao;
import com.epam.horelov.dal.dao.BookRequestDaoImpl;
import com.epam.horelov.entity.BookRequest;
import com.epam.horelov.entity.RequestStatus;
import com.epam.horelov.entity.RoomClass;
import com.epam.horelov.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BookingService {

    private BookRequestDao bookRequestDao;

    private static final String A_PLUS_ROOM_CLASS = "A+";
    private static final String A_ROOM_CLASS = "A";
    private static final String B_ROOM_CLASS = "B";

    public BookingService(BookRequestDao bookRequestDao) {
        this.bookRequestDao = bookRequestDao;
    }

    private BookRequest createBookRequest(HttpServletRequest req){
        BookRequest bookRequest = new BookRequest();

        bookRequest.setId(UUID.randomUUID().toString());
        bookRequest.setUserId(((User)req.getSession().getAttribute("user")).getId());
        bookRequest.setPlacesNumber(Integer.parseInt(req.getParameter("place-number")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        bookRequest.setDateTimeIn(LocalDateTime.parse(req.getParameter("date-from"), formatter));
        bookRequest.setDateTimeOut(LocalDateTime.parse(req.getParameter("date-to"), formatter));

        if (A_PLUS_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.APLUS);
        } else if (A_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.A);
        } else if (B_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.B);
        }

        bookRequest.setStatus(RequestStatus.PENDING);

        //todo: ROOM CHOOSING, FILL ROOM SELECTION OPTIONS SELECT FROM DATABASE (BOOKING.JSP)
        bookRequest.setRoomNumber(123);

        bookRequest.setRequestDateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter)));

        return bookRequest;
    }

    public void book(HttpServletRequest req) {
        BookRequest bookRequest = createBookRequest(req);
        bookRequestDao.insertBookRequest(bookRequest);
    }
}
