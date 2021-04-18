package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Student;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MySQLStudentCoursesDAOTest {
    @Test
    public void login() {
        ICourseDAOInterface ICourseDAO=new MySqlCourseDAO();
        IStudentDAOInterface IStudentDAO=new MySqlStudentDAO();
        IStudentCoursesDAOInterface IStudentChoicesDAO=new MySQLStudentCoursesDAO();
        Student student=new Student(11112222,"2000-10-10","Green1234");
        IStudentDAO.getStudents().put(11112222,student);
        assertEquals(true,IStudentDAO.login(11112222,"2000-10-10","Green1234"));
    }

}