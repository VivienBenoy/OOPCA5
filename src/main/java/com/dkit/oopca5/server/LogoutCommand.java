package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

public class LogoutCommand implements ICommand{
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        if(components.length==1)
        {
            if(studentDAO.isLogin())
            {
                studentDAO.setLogin(false);
            }
            if(!studentDAO.isLogin())
            {
                response=CAOService.SUCCESSFUL_LOGOUT;
            }
            else{
                response=CAOService.FAILED_LOGOUT;
            }
        }
        return response;
    }
}
