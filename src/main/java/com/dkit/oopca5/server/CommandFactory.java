package com.dkit.oopca5.server;
import com.dkit.oopca5.core.CAOService;
public class CommandFactory {
    public static ICommand createCommand(String requestCommand)
    {
        ICommand c=null;
        switch(requestCommand)
        {
            case CAOService.REGISTER_COMMAND:
                c = new RegisterCommand();
                break;
            case CAOService.LOGIN_COMMAND:
                c = new LoginCommand();
                break;
            case CAOService.QUIT_COMMAND:
                c = new QuitCommand();
                break;
            case CAOService.LOGOUT:
                c = new LogoutCommand();
                break;
            case CAOService.DISPLAY_COURSE:
                c=new DisplayCommand();
                break;
            case CAOService.DISPLAY_ALL:
                c=new DisplayAllCommand();
                break;
            case CAOService.DISPLAY_CURRENT:
                c=new DisplayCurrentCommand();
                break;
            case CAOService.UPDATE_CURRENT:
                c=new UpdateCommand();
                break;
        }
        return c;
    }
}
