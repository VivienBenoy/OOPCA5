package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.dto.Course;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommand implements ICommand{
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length>=1)
        {
            List<String> courses=new ArrayList<>();
            for(int i=1;i<components.length;i++)
            {
                courses.add(components[i]);
            }
            if(checkIfValid(courses,courseDAO))
            {
                if(!courses.isEmpty())
                {
                    studentCourseDAO.updateChoices(courses,studentDAO.getCaoNumber());
                    response= CAOService.SUCCESSFUL_UPDATE_CURRENT;
                }
            }
            else
            {
                System.out.println(Colours.RED+"The course entered does not exist"+Colours.RESET);
                response=CAOService.FAILED_UPDATE_CURRENT;
            }
        }
        else{
            response=CAOService.FAILED_UPDATE_CURRENT;
        }
        return response;
    }

    private boolean checkIfValid(List<String> courses, ICourseDAOInterface courseDAO) {
        boolean valid=true;
        int count=0;
            for(int i=0;i<courses.size();i++)
            {
                for(Course course: courseDAO.getAllCourses())
                {
                    if(!courses.get(i).equals(course))
                    {
                         count++;
                    }
                }
            }
            if(count==courseDAO.getAllCourses().size()+1)
            {
                valid=false;
            }
            return valid;
    }
}
