package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;
import com.dkit.oopca5.core.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLStudentCoursesDAO extends MySqlDAO implements IStudentCoursesDAOInterface {
    private IStudentDAOInterface studentDatabase;
    private ICourseDAOInterface courseDatabase;

    private Map<Integer, List<String>> studentChoices;
    private Map<String, Course> courses;
    private Map<Integer, Student> students;
    private boolean login;
    private int caoNumber;

    public MySQLStudentCoursesDAO(IStudentDAOInterface studentDatabase, ICourseDAOInterface courseDatabase) {
        this.studentDatabase = studentDatabase;
        this.courseDatabase = courseDatabase;
        this.login=false;
        this.caoNumber=0;
        this.studentChoices=new HashMap<>();
        this.courses=this.courseDatabase.getCourses();
        this.students=this.studentDatabase.getStudents();
    }
    @Override
    public boolean isLogin() {
        return login;
    }
    @Override
    public void setLogin(boolean login) {
        this.login = login;
    }

    @Override
    public void loadFromDatabase() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from student_choices";
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
                System.out.println(studentChoices.toString());
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
@Override
   public void updateChoices(List<String> courseID)
   {
       if(studentChoices.containsKey(caoNumber))
       {
           if(studentChoices.get(caoNumber)!=null)
           {
               removeStudentChoices(caoNumber);
           }
           try{
               for(int i=0;i<courseID.size();i++)
               {
                   studentChoices.get(caoNumber).add(courseID.get(i));
               }

           }
           catch(NullPointerException npe)
           {
               System.out.println(npe.getMessage());
           }

       }
       else
       {
           studentChoices.put(caoNumber,courseID);
       }
   }
    public void removeStudentChoices(int caoNumber){
        for(int i=0;i<studentChoices.get(caoNumber).size();i++)
        {
            studentChoices.get(caoNumber).remove(i);
        }
    }
    @Override
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
    @Override
    public Course displayCourse(String courseId)
    {
        Course course=null;
        for(Map.Entry<String, Course> entry : courses.entrySet())
        {
            if(courseId.equals(entry.getKey()))
            {
                course=entry.getValue();
            }
        }
      return course;
    }
    @Override
    public List<Course> getAllCourses(){
        List<Course> courseList=new ArrayList<>();
        for(Map.Entry<String, Course> entry : courses.entrySet())
        {
            courseList.add(entry.getValue());
        }
        return courseList;
    }
    @Override
    public List<Course> displayCurrentChoices(int caoNumber){
      List<Course> courseList=new ArrayList<>();
            if(studentChoices.containsKey(this.caoNumber))
            {
                for(Map.Entry<String, Course> entry : courses.entrySet())
                {
                    for(int j=0;j<studentChoices.get(caoNumber).size();j++)
                    {
                        if(entry.getKey().equalsIgnoreCase(studentChoices.get(caoNumber).get(j)))
                        {
                            courseList.add(entry.getValue());
                        }
                    }
                }
            }
        return courseList;
    }

}
