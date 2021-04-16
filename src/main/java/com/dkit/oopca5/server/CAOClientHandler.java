package com.dkit.oopca5.server;

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.core.CAOService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CAOClientHandler implements  Runnable
{
    private Socket clientSocket;
    private int clientNumber;
    private IStudentCoursesDAOInterface studentCourseDAO;
    private IStudentDAOInterface studentDAO;
    private ICourseDAOInterface courseDAO;
    public CAOClientHandler(Socket clientSocket,int clientNumber,IStudentCoursesDAOInterface studentCourseDAO,IStudentDAOInterface studentAO,ICourseDAOInterface courseDAO){

        this.clientSocket=clientSocket;
        this.clientNumber=clientNumber;
        this.studentCourseDAO=studentCourseDAO;
        this.studentDAO=studentAO;
        this.courseDAO=courseDAO;


    }
    @Override
    public void run() {
        try{
            Scanner clientInput=new Scanner(clientSocket.getInputStream());
            PrintWriter clientOutput =new PrintWriter(clientSocket.getOutputStream(),true);
            boolean active=true;
            while(active)
            {
                System.out.println("Waiting for client input");
                String request = clientInput.nextLine();
                System.out.println("Input Received " + request);

                String components[]=request.split(CAOService.BREAKING_CHARACTER);
                String response=null;
                ICommand command = CommandFactory.createCommand(components[0]);

                if (command != null)
                {
                    response = command.generateResponse(components,studentDAO,courseDAO,studentCourseDAO);
                    if (response != null)
                    {
                        clientOutput.println(response);
                        if (command instanceof QuitCommand)
                        {
                            active = false;
                        }
                    }
                }
            }

            clientSocket.close();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
