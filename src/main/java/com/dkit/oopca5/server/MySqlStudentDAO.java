package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlStudentDAO extends MySqlDAO implements IStudentDAOInterface{
    private Map<Integer, Student> students;
    private boolean login;
    private int caoNumber;

    public MySqlStudentDAO(){
        students=new HashMap<>();
        this.login=false;
        this.caoNumber=0;
        try{
            loadFromDatabase();
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

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
    public int getCaoNumber() {
        return caoNumber;
    }

    @Override
    public Map<Integer, Student> getStudents() {
        return students;
    }

    @Override
    public void loadFromDatabase() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from student";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String dateOfBirth = rs.getString("date_of_birth");
                String password = rs.getString("password");

                Student student=new Student(caoNumber,dateOfBirth,password);
                students.put(caoNumber,student);

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
    public void registerStudent(int caoNumber,String dateOfbirth,String password){
       if(!students.containsKey(caoNumber))
       {
           Student student=new Student(caoNumber,dateOfbirth,password);
           students.put(caoNumber,student);
       }
       else{
           System.out.println("Student with that caoNumber already exists.");
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
}
