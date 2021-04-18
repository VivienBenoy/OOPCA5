package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;

import java.util.List;

public interface IStudentCoursesDAOInterface {
    public void loadFromDatabase() throws DAOException;


    public List<String> displayCurrentChoices(int caoNumber);
    public void updateChoices(List<String> courseID,int caoNumber);



}
