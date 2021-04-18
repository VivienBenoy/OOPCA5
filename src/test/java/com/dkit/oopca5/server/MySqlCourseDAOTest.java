package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlCourseDAOTest {

    @Test
    public void displayCourse() {
        Course c=new Course("DK001",8,"Games","DKIT");
        ICourseDAOInterface courseDAO=new MySqlCourseDAO();
        courseDAO.getCourses().put("DK001",c);
        assertEquals(c,courseDAO.displayCourse("DK001"));
    }

    @Test
    public void getAllCourses() {
        Course c1=new Course("DK110",8,"Games Development","DKIT");
        Course c2=new Course("DK990",8,"Business Studies","DKIT");
        Course c3=new Course("DK200",8,"Culinary Arts","AIT");
        List<Course> courseList=new ArrayList<>();
        ICourseDAOInterface courseDAO=new MySqlCourseDAO();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        assertEquals(courseList,courseDAO.getAllCourses());


    }
}