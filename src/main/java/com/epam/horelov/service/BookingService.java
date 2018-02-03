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

        int placesNumber = Integer.parseInt(req.getParameter("place-number"));

        if (placesNumber > 0) {
            bookRequest.setPlacesNumber(placesNumber);
        } else {
            throw new IllegalArgumentException("Places number must be > 0");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateIn = LocalDateTime.parse(req.getParameter("date-from"), formatter);
        LocalDateTime dateOut = LocalDateTime.parse(req.getParameter("date-to"), formatter);
        if (!dateIn.isBefore(LocalDateTime.now()) &&
            !dateOut.isBefore(LocalDateTime.now()) &&
            dateIn.isBefore(dateOut)) {
            bookRequest.setDateTimeIn(dateIn);
            bookRequest.setDateTimeOut(dateOut);
        }
        else {
            throw new IllegalArgumentException("Please, make sure that dates you've entered hadn't passed yet");
        }

        if (A_PLUS_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.APLUS);
        } else if (A_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.A);
        } else if (B_ROOM_CLASS.equals(req.getParameter("room-class"))) {
            bookRequest.setRoomClass(RoomClass.B);
        }

        bookRequest.setStatus(RequestStatus.PENDING);

        //todo: ROOM CHOOSING, FILL ROOM SELECTION OPTIONS SELECT FROM DATABASE (BOOKING.JSP)
        bookRequest.setRoomNumber(Integer.parseInt(req.getParameter("roomNumber")));

        bookRequest.setRequestDateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter)));

        return bookRequest;
    }

    public void book(HttpServletRequest req) {
        BookRequest bookRequest = createBookRequest(req);
        bookRequestDao.insertBookRequest(bookRequest);
    }
}
