package com.dkit.oopca5.server;

public interface IStudentDAOInterface {
    public void loadFromDatabase() throws DAOException;
    public void registerStudent(int caoNumber,String dateOfBirth,String password) throws DAOException;

}
