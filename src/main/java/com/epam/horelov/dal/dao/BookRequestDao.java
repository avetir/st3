package com.epam.horelov.dal.dao;

import com.epam.horelov.entity.BookRequest;
import com.epam.horelov.entity.RequestStatus;
import com.epam.horelov.entity.User;

import java.util.List;

public interface BookRequestDao {

    public void insertBookRequest(BookRequest bookRequest);
    public List<BookRequest> getAllRequests();
    public BookRequest getRequestById(String id);
    public void updateRequestStatus(RequestStatus requestStatus, String id);

}
