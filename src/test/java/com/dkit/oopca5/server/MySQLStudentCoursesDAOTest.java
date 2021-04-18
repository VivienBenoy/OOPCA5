package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MySQLStudentCoursesDAOTest {


    @Test
    public void updateChoices() {
        IStudentCoursesDAOInterface studentCoursesDAO=new MySQLStudentCoursesDAO();
        List<String> courseID=new ArrayList<>();
        courseID.add("DK110");
        courseID.add("DK200");
        studentCoursesDAO.updateChoices(courseID,12345678);
        assertEquals(courseID,studentCoursesDAO.displayCurrentChoices(12345678));
    }

}