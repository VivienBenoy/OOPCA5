package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.Colours;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CAOClient
{
    private Scanner keyboard=new Scanner(System.in);
    public static void main(String[] args) {


        System.out.println( "CAO Online - CA4" );
        new CAOClient() .start();
    }
    private void start(){
        MainMenu selectedOption=MainMenu.Continue;
        while(selectedOption!=MainMenu.Quit)
        {
            try{
                printMainMenu();
                selectedOption=MainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch(selectedOption)
                {
                    case Register:
                        loggedinMenu();
                        break;
                    case Log_In:
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
        while(selectedOption!=LoggedInMenu.Quit)
        {
            try {
                printLoggedInMenu();
                selectedOption = LoggedInMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (selectedOption) {
                    case Logout:
                        break;
                    case Display_Course:
                        break;
                    case Display_all_courses:
                        break;
                    case Display_current_choices:
                        break;
                    case Update_current_choices:
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
