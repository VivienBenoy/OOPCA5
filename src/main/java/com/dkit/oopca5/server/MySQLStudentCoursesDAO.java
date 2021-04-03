package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;
import com.dkit.oopca5.core.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLStudentCoursesDAO extends MySqlDAO implements IStudentCoursesDAOInterface {
    private MySqlStudentDAO studentDatabase;
    private MySqlCourseDAO courseDatabase;

    private Map<Integer, List<String>> studentChoices;
    private Map<String, Course> courses;
    private Map<Integer, Student> students;
    private boolean login;
    private int caoNumber;

    public MySQLStudentCoursesDAO(MySqlStudentDAO studentDatabase, MySqlCourseDAO courseDatabase) {
        this.studentDatabase = studentDatabase;
        this.courseDatabase = courseDatabase;
        this.login=false;
        this.caoNumber=0;
        this.studentChoices=new HashMap<>();
        this.courses=this.courseDatabase.getCourses();
        this.students=this.studentDatabase.getStudents();
    }

    @Override
    public void loadFromDatabase() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from user";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String courseID = rs.getString("courseID");
                List<String> courses=new LinkedList<>();
                if(studentChoices.containsKey(caoNumber))
                {
                    studentChoices.get(caoNumber).add(courseID);
                }
                else{
                    courses.add(courseID);
                    studentChoices.put(caoNumber,courses);
                }

            }
        } catch (SQLException se)
        {
            throw new DAOException("loadFromDatabase() " + se.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException se)
            {
                throw new DAOException("loadFromDatabase() finally " + se.getMessage());
            }
        }
    }
    public boolean login(int caoNumber,String dateOfBirth,String password)
    {
        for(Map.Entry<Integer, Student> entry : students.entrySet())
        {
            if(caoNumber== entry.getKey() && dateOfBirth.equals(entry.getValue().getDateOfBirth()) && password.equals(entry.getValue().getPassword()))
            {
                login=true;
                this.caoNumber=caoNumber;
            }
        }
        return login;
    }
    public Course displayCourse(String courseId)
    {
        String message=null;
        for(Map.Entry<String, Course> entry : courses.entrySet())
        {
            if(courseId.equals(entry.getKey()))
            {
                return entry.getValue();
            }
            else{
                message="Course was not found";
            }
        }
        if(message!=null)
        {
            System.out.println(message);
        }
        return  null;
    }
    
}
