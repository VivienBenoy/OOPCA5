package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;

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
                        message=CAOService.REGISTER_COMMAND;
                        RegisterNow(message);
                        loggedinMenu();
                        break;
                    case Log_In:
                        message=CAOService.LOGIN_COMMAND;
                        loginDetails(message);
                        loggedinMenu();
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
        }
    }

    private void loggedinMenu() {
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
                        break;
                    case Display_Course:

                        System.out.println("Please Enter CourseID");
                        String courseID=keyboard.nextLine();
                        message=CAOService.DISPLAY_COURSE+CAOService.BREAKING_CHARACTER+courseID;
                        break;
                    case Display_all_courses:
                        message=CAOService.DISPLAY_ALL;
                        break;
                    case Display_current_choices:
                        message=CAOService.DISPLAY_CURRENT+CAOService.BREAKING_CHARACTER+loggedInCAONumber;
                        break;
                    case Update_current_choices:
                        message=updateChoices();
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
        }
    }
    private void RegisterNow(String message)
    {
        int caoNumber=0;
        String dateOfBirth="";
        String password="";
        enterDetails(caoNumber,dateOfBirth,password);
        message=message+ CAOService.BREAKING_CHARACTER+caoNumber+CAOService.BREAKING_CHARACTER+dateOfBirth+CAOService.BREAKING_CHARACTER+password;

    }
    private void loginDetails(String message)
    {
        int caoNumber=0;
        String dateOfBirth="";
        String password="";
        enterDetails(caoNumber,dateOfBirth,password);
        message=message+ CAOService.BREAKING_CHARACTER+caoNumber+CAOService.BREAKING_CHARACTER+dateOfBirth+CAOService.BREAKING_CHARACTER+password;
    }
    public void enterDetails(int caoNumber,String dateOfBirth,String password)
    {
        try{
            System.out.println("Please enter your CAO Number");
            caoNumber=Integer.parseInt(keyboard.nextLine());
            System.out.println("Please enter your date of birth");
            dateOfBirth=keyboard.nextLine();
            System.out.println("Please enter a password");
            password= keyboard.nextLine();
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
