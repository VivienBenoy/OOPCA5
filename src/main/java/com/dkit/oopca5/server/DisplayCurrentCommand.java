package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.dto.Course;

import java.util.List;

public class DisplayCurrentCommand implements ICommand {
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length==2)
        {
            int caoNumber=Integer.parseInt(components[1]);
            List<Course> courseList=studentCourseDAO.displayCurrentChoices(caoNumber);
            if(!courseList.isEmpty())
            {
                response= CAOService.SUCCESSFUL_DISPLAY_CURRENT+CAOService.BREAKING_CHARACTER+CAOService.flattenCourseList(courseList);
            }
            else
            {
                response=CAOService.FAILED_DISPLAY_CURRENT;
            }
        }
        return response;
    }
}
