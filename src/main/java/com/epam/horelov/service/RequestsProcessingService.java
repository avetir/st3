package com.epam.horelov.service;

import com.epam.horelov.dal.dao.BookRequestDao;
import com.epam.horelov.entity.BookRequest;
import com.epam.horelov.entity.RequestStatus;

import java.util.List;

public class RequestsProcessingService {

    private BookRequestDao bookRequestDao;

    public RequestsProcessingService(BookRequestDao bookRequestDao){
        this.bookRequestDao = bookRequestDao;
    }

    public List<BookRequest> getAllRequests(){
        return bookRequestDao.getAllRequests();
    }

    public BookRequest getRequestByNumber(String id){
        return bookRequestDao.getRequestById(id);
    }

    public void updateRequestStatus (RequestStatus requestStatus, String id) {bookRequestDao.updateRequestStatus(requestStatus, id);}

}
