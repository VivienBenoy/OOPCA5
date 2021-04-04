package com.dkit.oopca5.server;

import java.util.List;

public interface IStudentCoursesDAOInterface {
    public void loadFromDatabase() throws DAOException;
    public boolean login(int caoNumber,String dateOfBirth,String password);
    public void displayCourse(String courseId);
    public void displayAllCourses();
    public void displayCurrentChoices(int caoNumber);
    public void updateChoices(List<String> courseID);



}
