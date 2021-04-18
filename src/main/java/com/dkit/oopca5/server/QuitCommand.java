package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

public class QuitCommand implements ICommand {
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length==1)
        {
            response= CAOService.EXIT;
        }
        return response;
    }
}
