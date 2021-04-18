package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

public class RegisterCommand implements ICommand{
    @Override
    public String generateResponse(String[] components,IStudentDAOInterface studentDAO,ICourseDAOInterface courseDAO,IStudentCoursesDAOInterface studentcourseDAO) {
        String response=null;
        boolean registered=false;
        if(components.length==4)
        {
            try {
                int caoNumber = Integer.parseInt(components[1]);
                String dateOfbirth = components[2];
                String password = components[3];
                studentDAO.registerStudent(caoNumber, dateOfbirth, password);
                if (studentDAO.getStudents().containsKey(caoNumber)) {
                    registered = true;
                }
                if (registered) {
                    response = CAOService.SUCCESSFUL_REGISTER;
                } else {
                    response = CAOService.FAILED_REGISTER;
                }
            }catch (NumberFormatException e)
            {
                System.out.println(e.getMessage());
            }

        }
        return response;
    }
}
