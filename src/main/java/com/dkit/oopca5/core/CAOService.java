package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

import com.dkit.oopca5.core.dto.Course;

import java.util.ArrayList;
import java.util.List;

public class CAOService
{
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";
    public static final String COURSE_SEPARATOR="##";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";

    public static final String LOGIN_COMMAND="LOGIN";
    public static final String SUCCESSFUL_LOGIN="LOGGED IN";
    public static final String FAILED_LOGIN="LOGIN FAILED";

    public static final String LOGOUT="LOGOUT";
    public static final String SUCCESSFUL_LOGOUT="LOGGED OUT";
    public static final String FAILED_LOGOUT="FAILED TO LOG OUT";

    public static final String DISPLAY_COURSE="DISPLAY COURSE";
    public static final String FAILED_DISPLAY_COURSE="DISPLAY FAILED";

    public static final String DISPLAY_ALL="DISPLAY ALL";
    public static final String FAILED_DISPLAY_ALL="FAILED DISPLAY ALL";
    public static final String SUCCESSFUL_DISPLAY_ALL="SUCCESSFUL DISPLAY ALL";

    public static final String DISPLAY_CURRENT="DISPLAY CURRENT";
    public static final String FAILED_DISPLAY_CURRENT="FAILED DISPLAY CURRENT";
    public static final String SUCCESSFUL_DISPLAY_CURRENT="SUCCESSFUL DISPLAY CURRENT";

    public static final String UPDATE_CURRENT="UPDATE CURRENT";
    public static final String FAILED_UPDATE_CURRENT="FAILED UPDATE CURRENT";
    public static final String SUCCESSFUL_UPDATE_CURRENT="SUCCESSFUL UPDATE CURRENT";

    public static final String QUIT_COMMAND="QUIT";
    public static final String EXIT="SIGNED OFF";

    public static String flattenCourseList(List<Course> courseList)
    {
        if(!courseList.isEmpty())
        {
            String courses = courseList.get(0).format();
            for(int i=1; i < courseList.size(); i++)
            {
                courses = courses + CAOService.COURSE_SEPARATOR + courseList.get(i).format();
            }
            return courses;
        }
        else
        {
            return null;
        }
    }
    public static List<Course> recreateCourseList(String courses)
    {
        List<Course> courseList = new ArrayList<>();
        String[] courseStrings = courses.split(CAOService.COURSE_SEPARATOR);
        for (String course : courseStrings)
        {

            String[] components = course.split(CAOService.BREAKING_CHARACTER);
            if (components.length == 3)
            {
                    int level=Integer.parseInt(components[1]);
                    Course c=new Course(components[0],level,components[2],components[3]);
                    courseList.add(c);
            }
        }
        return courseList;
    }
}

