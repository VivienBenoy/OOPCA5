package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.server.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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

        try
        {
            Socket dataSocket=new Socket(CAOService.HOSTNAME,CAOService.PORT_NUM);
            Scanner serverIn = new Scanner(dataSocket.getInputStream());
            PrintWriter serverOut = new PrintWriter(dataSocket.getOutputStream(), true);
        MainMenu selectedOption=MainMenu.Continue;
        String message;
        String response;
        while(selectedOption!=MainMenu.Quit)
        {

                printMainMenu();

                selectedOption=MainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch(selectedOption)
                {
                    case Register:
                        message=RegisterNow();
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        loggedinMenu(dataSocket,serverIn,serverOut);
                        break;
                    case Log_In:
                        message=loginDetails();
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        loggedinMenu(dataSocket,serverIn,serverOut);
                        break;
                }
            }

        } catch(InputMismatchException ime)
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
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private void loggedinMenu(Socket dataSocket,Scanner serverIn,PrintWriter serverOut) {
        LoggedInMenu selectedOption=LoggedInMenu.Continue;
        String message;
        String response;
        while(selectedOption!=LoggedInMenu.Quit)
        {
            try {
                printLoggedInMenu();
                selectedOption = LoggedInMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (selectedOption) {
                    case Logout:
                        message=CAOService.LOGOUT;
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        break;
                    case Display_Course:
                        System.out.println("Please Enter CourseID");
                        String courseID=keyboard.nextLine();
                        message=CAOService.DISPLAY_COURSE+CAOService.BREAKING_CHARACTER+courseID;
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        break;
                    case Display_all_courses:
                        message=CAOService.DISPLAY_ALL;
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        break;
                    case Display_current_choices:
                        message=CAOService.DISPLAY_CURRENT+CAOService.BREAKING_CHARACTER+loggedInCAONumber;
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
                        break;
                    case Update_current_choices:
                        message=updateChoices();
                        serverOut.println(message);
                        response=serverIn.nextLine();
                        System.out.println("Response: "+response);
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

        return enterDetails(message);

    }
    private String loginDetails()
    {
        StringBuffer message=new StringBuffer(CAOService.LOGIN_COMMAND);

        return enterDetails(message);
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
    public String enterDetails(StringBuffer message)
    {
        boolean checkInput=true;
        boolean caoNumbercheck=false;
        boolean dateOfBirthcheck=false;
        boolean passwordcheck=false;
        int caoNumber=0;
        String dateOfBirth="";
        String password="";
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
        return appendMessage(message,caoNumber,dateOfBirth,password);
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
