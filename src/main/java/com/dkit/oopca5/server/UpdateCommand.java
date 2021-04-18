package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommand implements ICommand{
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length>1)
        {
            List<String> courses=new ArrayList<>();
            for(int i=1;i<components.length;i++)
            {
                courses.add(components[i]);
            }
            if(!courses.isEmpty())
            {
                studentCourseDAO.updateChoices(courses,studentDAO.getCaoNumber());
                response= CAOService.SUCCESSFUL_UPDATE_CURRENT;
            }
            else
            {
                response=CAOService.FAILED_UPDATE_CURRENT;
            }
        }
        else{
            response=CAOService.FAILED_UPDATE_CURRENT;
        }
        return response;
    }
}
