package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.dto.Course;

import java.util.List;

public class DisplayAllCommand implements ICommand {
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length==1);
        {
            List<Course> courses=studentCourseDAO.getAllCourses();
            if(!courses.isEmpty())
            {
                response= CAOService.SUCCESSFUL_DISPLAY_ALL+CAOService.BREAKING_CHARACTER+CAOService.flattenCourseList(courses);
            }
            else{
                response=CAOService.FAILED_DISPLAY_ALL;
            }

        }
        return response;
    }
}
