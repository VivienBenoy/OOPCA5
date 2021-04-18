package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;
import com.dkit.oopca5.core.dto.Student;

import java.util.Map;

public interface IStudentDAOInterface {
    public void loadFromDatabase() throws DAOException;
    public void registerStudent(int caoNumber,String dateOfBirth,String password);
    public Map<Integer, Student> getStudents() ;
    public boolean login(int caoNumber,String dateOfBirth,String password);
    public boolean isLogin();
    public void setLogin(boolean login);
    public int getCaoNumber();
    public void setCaoNumber(int caoNumber);
    public boolean saveToDatabase(Student student) throws DAOException;
}
