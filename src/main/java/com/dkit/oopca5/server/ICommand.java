package com.dkit.oopca5.server;

public interface ICommand {
    public String generateResponse(String[] components,IStudentDAOInterface studentDAO,ICourseDAOInterface courseDAO,IStudentCoursesDAOInterface studentCourseDAO);

}
