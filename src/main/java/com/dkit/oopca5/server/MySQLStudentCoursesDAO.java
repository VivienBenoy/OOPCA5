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
           try{
           if(!studentChoices.get(caoNumber).isEmpty())
           {
               studentChoices.get(caoNumber).clear();
               deleteFromDatabase(caoNumber);
           }


               for(int i=0;i<courseID.size();i++)
               {
                   studentChoices.get(caoNumber).add(courseID.get(i));
                   saveToDatabase(caoNumber,courseID.get(i));
               }
           }
           catch(NullPointerException | DAOException npe)
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

    @Override
    public void saveToDatabase(int caoNumber,String courseID) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean saved=false;

        try
        {
            con = this.getConnection();
            String query="INSERT INTO STUDENT_COURSES VALUES(?,?)";
                ps = con.prepareStatement(query);
                ps.setInt(1,caoNumber);
                ps.setString(2,courseID);
                saved=ps.executeUpdate()==1;

        } catch (SQLException se)
        {
            throw new DAOException("saveToDatabase() " + se.getMessage());
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
                throw new DAOException("saveToDatabase() finally " + se.getMessage());
            }
        }
    }
    public void deleteFromDatabase(int caoNumber) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean saved=false;

        try
        {
            con = this.getConnection();
            String query1 = "delete from student_courses where caoNumber = "+caoNumber;
            ps = con.prepareStatement(query1);
            saved=ps.executeUpdate()==1;



        } catch (SQLException se)
        {
            throw new DAOException("DeleteFromDatabase() " + se.getMessage());
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
                throw new DAOException("DeleteFromDatabase() finally " + se.getMessage());
            }
        }
    }
}
