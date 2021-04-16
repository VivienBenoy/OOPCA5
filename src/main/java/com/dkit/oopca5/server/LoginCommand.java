package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

public class LoginCommand implements ICommand {
    @Override
    public String generateResponse(String[] components, IStudentDAOInterface studentDAO, ICourseDAOInterface courseDAO, IStudentCoursesDAOInterface studentCourseDAO) {
        String response=null;
        boolean login=false;
        if(components.length==4)
        {
            try {
                int caoNumber = Integer.parseInt(components[1]);
                String dateOfbirth = components[2];
                String password = components[3];
                studentCourseDAO.login(caoNumber,dateOfbirth,password);
                login=studentCourseDAO.isLogin();
                if(login)
                {
                    response= CAOService.SUCCESSFUL_LOGIN;
                }
                else{
                    response=CAOService.FAILED_LOGIN;
                }
            } catch (NumberFormatException e) {
                response=CAOService.FAILED_LOGIN;
            }

        }
        return response;
    }
}
