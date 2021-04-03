package com.dkit.oopca5.server;

import com.dkit.oopca5.core.dto.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlCourseDAO extends MySqlDAO implements ICourseDAOInterface {
    private Map<String,Course> courses;

    public MySqlCourseDAO(){
        courses=new HashMap<>();
        try{
            loadFromDatabase();
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    @Override
    public void loadFromDatabase() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from course";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                String courseID = rs.getString("courseid");
                int level = rs.getInt("level");
                String title = rs.getString("title");
                String institution = rs.getString("institution");

                Course course=new Course(courseID,level,title,institution);
                courses.put(courseID,course);

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
}
