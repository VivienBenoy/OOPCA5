package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

public class CAOServer
{
    public static void main(String[] args) {
        CAOServer server = new CAOServer();
        server.start();
    }

    public void start()
    {
        ICourseDAOInterface ICourseDAO=new MySqlCourseDAO();
        IStudentDAOInterface IStudentDAO=new MySqlStudentDAO();
        IStudentCoursesDAOInterface IStudentChoicesDAO=new MySQLStudentCoursesDAO();
        try
        {
            //Set up a listening socket
            ServerSocket listeningSocket = new ServerSocket(CAOService.PORT_NUM);
            //Socket dataSocket = new Socket();
            int clientNumber = 0;

            while(true) //loop continuously to accept new client connections
            {
                //listens (waits) for connection, accepts connection and open a new socket to communicate with client
                Socket dataSocket = listeningSocket.accept();
                clientNumber++;
                System.out.println("Server : Client "+clientNumber+" has connected.");
                Thread t = new Thread(new CAOClientHandler(dataSocket, clientNumber,IStudentChoicesDAO,IStudentDAO,ICourseDAO));
                t.start();
            }
        }
        catch (IOException e)
        {
            System.out.println("Problem setting up the connection"+e.getMessage());;
        }
    }
}
