package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;
import com.dkit.oopca5.core.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLStudentCoursesDAO extends MySqlDAO implements IStudentCoursesDAOInterface {
    private Map<Integer, List<String>> studentChoices;


    public MySQLStudentCoursesDAO() {

        this.studentChoices=new HashMap<>();
        try{
            loadFromDatabase();
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void loadFromDatabase() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from student_courses";
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
@Override
   public void updateChoices(List<String> courseID,int caoNumber)
   {
       if(studentChoices.containsKey(caoNumber))
       {
           if(!studentChoices.get(caoNumber).isEmpty())
           {
               studentChoices.get(caoNumber).clear();
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


    @Override
    public List<String> displayCurrentChoices(int caoNumber){
      List<String> courseList=new ArrayList<>();
            if(studentChoices.containsKey(caoNumber))
            {
                for(int j=0;j<studentChoices.get(caoNumber).size();j++)
                {
                    courseList.add(studentChoices.get(caoNumber).get(j));
                }
            }
        return courseList;
    }

}
