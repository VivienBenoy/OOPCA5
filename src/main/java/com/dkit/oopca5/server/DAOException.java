package com.dkit.oopca5.server;

import java.sql.SQLException;

public class DAOException extends SQLException {
    public DAOException()
    {

    }

    public DAOException(String aMessage)
    {
        super(aMessage);
    }

}
