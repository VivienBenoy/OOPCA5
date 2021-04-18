package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Student;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MySqlStudentDAOTest {
    @Test
    public void login() {

        IStudentDAOInterface IStudentDAO=new MySqlStudentDAO();
        Student student=new Student(11112222,"2000-10-10","Green1234");
        IStudentDAO.getStudents().put(11112222,student);
        assertEquals(true,IStudentDAO.login(11112222,"2000-10-10","Green1234"));
    }
    @Test
    public void registerStudent() {
        IStudentDAOInterface studentDAO=new MySqlStudentDAO();
        studentDAO.registerStudent(12345678,"2000-12-12","green1234");
        assertEquals(true,studentDAO.getStudents().containsKey(12345678));
    }
}