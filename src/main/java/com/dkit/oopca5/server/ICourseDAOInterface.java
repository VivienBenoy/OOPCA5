package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;

import java.util.List;
import java.util.Map;

public interface ICourseDAOInterface {
    public void loadFromDatabase() throws DAOException;
    public Map<String, Course> getCourses();
    public Course displayCourse(String courseId);
    public List<Course> getAllCourses();
}
