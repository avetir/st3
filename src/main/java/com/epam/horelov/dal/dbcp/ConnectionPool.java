package com.epam.horelov.dal.dbcp;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ConnectionPool {

    public Connection getConnection();
    public void returnConnection (Connection connection);

}
