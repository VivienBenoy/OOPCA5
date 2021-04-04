package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.server.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CAOClient
{
    private Scanner keyboard=new Scanner(System.in);
    private int loggedInCAONumber=0;
    public static void main(String[] args) {


        System.out.println( "CAO Online - CA4" );
        new CAOClient() .start();
    }
    private void start(){
        ICourseDAOInterface ICourseDAO=new MySqlCourseDAO();
        IStudentDAOInterface IStudentDAO=new MySqlStudentDAO();
        IStudentCoursesDAOInterface IStudentChoicesDAO=new MySQLStudentCoursesDAO(IStudentDAO,ICourseDAO);

        MainMenu selectedOption=MainMenu.Continue;
        String message;
        while(selectedOption!=MainMenu.Quit)
        {
            try{
                printMainMenu();

                selectedOption=MainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch(selectedOption)
                {
                    case Register:
                        message=RegisterNow();
                        System.out.println(Colours.BLUE+"Now Registered as a new Student"+Colours.RESET);
                        loggedinMenu(IStudentChoicesDAO);
                        break;
                    case Log_In:
                        message=loginDetails();
                        loggedinMenu(IStudentChoicesDAO);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED+"Please try again"+Colours.RESET);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println(Colours.RED+"Please try again"+Colours.RESET);
            }
            catch(ArrayIndexOutOfBoundsException aie)
            {
                System.out.println(aie.getMessage());
            }
        }
    }

    private void loggedinMenu(IStudentCoursesDAOInterface IStudentChoicesDAO) {
        LoggedInMenu selectedOption=LoggedInMenu.Continue;
        String message;
        while(selectedOption!=LoggedInMenu.Quit)
        {
            try {
                printLoggedInMenu();
                selectedOption = LoggedInMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (selectedOption) {
                    case Logout:
                        message=CAOService.LOGOUT;
                        System.out.println(CAOService.SUCCESSFUL_LOGOUT);
                        break;
                    case Display_Course:
                        System.out.println("Please Enter CourseID");
                        String courseID=keyboard.nextLine();
                        message=CAOService.DISPLAY_COURSE+CAOService.BREAKING_CHARACTER+courseID;
                        System.out.println(CAOService.DISPLAY_COURSE+CAOService.BREAKING_CHARACTER+"DK110");
                        break;
                    case Display_all_courses:
                        message=CAOService.DISPLAY_ALL;
                        System.out.println(CAOService.SUCCESSFUL_DISPLAY_ALL+CAOService.BREAKING_CHARACTER+"DK110....##DK980");
                        break;
                    case Display_current_choices:
                        message=CAOService.DISPLAY_CURRENT+CAOService.BREAKING_CHARACTER+loggedInCAONumber;
                        System.out.println(CAOService.SUCCESSFUL_DISPLAY_CURRENT+CAOService.BREAKING_CHARACTER+"DK110");
                        break;
                    case Update_current_choices:
                        message=updateChoices();
                        System.out.println(CAOService.SUCCESSFUL_UPDATE_CURRENT+CAOService.BREAKING_CHARACTER+"12345678"+CAOService.BREAKING_CHARACTER+"DK110");
                         break;

                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED+"Please try again"+Colours.RESET);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println(Colours.RED+"Please try again"+Colours.RESET);
            }
            catch(ArrayIndexOutOfBoundsException aie)
            {
                System.out.println(aie.getMessage());
            }
        }
    }
    private String RegisterNow()
    {
        StringBuffer message=new StringBuffer(CAOService.REGISTER_COMMAND);
        int caoNumber=0;
        String dateOfBirth="";
        String password="";
        enterDetails(caoNumber,dateOfBirth,password);
        System.out.println(CAOService.SUCCESSFUL_REGISTER);
        return appendMessage(message,caoNumber,dateOfBirth,password);
    }
    private String loginDetails()
    {
        StringBuffer message=new StringBuffer(CAOService.LOGIN_COMMAND);
        int caoNumber=0;
        String dateOfBirth="";
        String password="";
        enterDetails(caoNumber,dateOfBirth,password);
        System.out.println(CAOService.SUCCESSFUL_LOGIN);
        return appendMessage(message,caoNumber,dateOfBirth,password);
    }
    public String appendMessage(StringBuffer message,int caoNumber,String dateOfBirth,String password)
    {
        message.append(CAOService.BREAKING_CHARACTER);
        message.append(caoNumber);
        message.append(CAOService.BREAKING_CHARACTER);
        message.append(dateOfBirth);
        message.append(CAOService.BREAKING_CHARACTER);
        message.append(password);
        return message.toString();
    }
    public void enterDetails(int caoNumber,String dateOfBirth,String password)
    {
        boolean checkInput=true;
        boolean caoNumbercheck=false;
        boolean dateOfBirthcheck=false;
        boolean passwordcheck=false;
        RegexChecker inputValidation=new RegexChecker();
        try{
            while(checkInput) {

                while(!caoNumbercheck)
                {
                    System.out.println("Please enter your CAO Number(8 digits)");

                    caoNumber = Integer.parseInt(keyboard.nextLine());
                    if(!inputValidation.verifyCAONumber(caoNumber))
                    {
                        System.out.println(Colours.RED+"Please Enter the right format"+Colours.RESET);
                    }
                    caoNumbercheck=inputValidation.verifyCAONumber(caoNumber);
                }
                while(!dateOfBirthcheck) {
                    System.out.println("Please enter your date of birth(yyyy-mm-dd)");
                    dateOfBirth = keyboard.nextLine();
                    dateOfBirthcheck=inputValidation.verifyDateOfBirth(dateOfBirth);
                    if(!dateOfBirthcheck)
                    {
                        System.out.println(Colours.RED+"Please Enter the right format"+Colours.RESET);
                    }
                }
                while (!passwordcheck) {
                    System.out.println("Please enter a password(atleast 8 digits)");
                    password = keyboard.nextLine();
                    passwordcheck = inputValidation.verifyPasswrd(password);
                    if(!passwordcheck)
                    {
                        System.out.println(Colours.RED+"Please Enter the right format"+Colours.RESET);
                    }
                }


                if (passwordcheck) {
                    loggedInCAONumber=caoNumber;
                    checkInput = false;
                } else {
                    System.out.println("Please input the correct format");
                }
            }

        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InputMismatchException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public String updateChoices()
    {
        boolean trueValue=true;
        StringBuffer message=new StringBuffer(CAOService.UPDATE_CURRENT);
        System.out.println("Please Enter Your Choice of Courses");
        while(trueValue)
        {

            String input=keyboard.nextLine();
            String[] data=input.split(",");
            for (int i=0;i<data.length;i++)
            {
                message.append(CAOService.BREAKING_CHARACTER);
                message.append(data[i]);
            }


        }
    return message.toString();

    }



    private void printMainMenu() {
        System.out.println(Colours.BLUE+"0.Quit");
        System.out.println("1.Register");
        System.out.println("2.Log In"+Colours.RESET);
    }

    private void printLoggedInMenu() {
        System.out.println(Colours.BLUE+"0.Quit");
        System.out.println("1.Logout");
        System.out.println("2.Display course");
        System.out.println("3.Display all courses");
        System.out.println("4.Display current choices");
        System.out.println("5.Update Current choices"+Colours.RESET);
    }
}
