package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.dto.Course;

public class DisplayCommand implements ICommand {
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length==2)
        {
            String courseID=components[1];
            Course course=courseDAO.displayCourse(courseID);
            response=course.format();
            if(!courseDAO.getCourses().containsKey(courseID))
            {
                response= CAOService.FAILED_DISPLAY_COURSE;
            }
        }
        return response;
    }
}
