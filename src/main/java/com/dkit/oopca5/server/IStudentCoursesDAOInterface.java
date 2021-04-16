package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;

import java.util.List;

public interface IStudentCoursesDAOInterface {
    public void loadFromDatabase() throws DAOException;
    public boolean login(int caoNumber,String dateOfBirth,String password);
    public Course displayCourse(String courseId);
    public List<Course> getAllCourses();
    public List<Course> displayCurrentChoices(int caoNumber);
    public void updateChoices(List<String> courseID);
    public boolean isLogin();
    public void setLogin(boolean login);


}
