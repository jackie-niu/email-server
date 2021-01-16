import hsa.*;
public class EmailServer {
    public static void main(String[] args) {
        int error = Init.initializeSystem();
        
        if (error == Globals.PROCESS_OK) {
            char serverCommand = 0;
            do {
                System.out.println("Waiting for client request...");
                String request = Stdin.readLine();     // NetIO.receiveRequest(); to be implemented soon

                serverCommand = request.charAt(0);
                switch(serverCommand) {

                    case Globals.SEND_MESSAGE    : // here we will place the code that deals with sending a message
                        break;

                    case Globals.IN_BOX          : // here we will place the code for giving the client its IN_BOX messages                        
                        break;

                    case Globals.OUT_BOX         : // here we will place the code for giving the client its OUT_BOX messages
                        break;

                    case Globals.DELETE_MESSAGE  : // here we will place the code for deleting a message
                        break;

                    case Globals.SERVER_SHUTDOWN : // server is shutdown from a client; only for development purposes
                        break;
                }
            } while (serverCommand != Globals.SERVER_SHUTDOWN);
        }
        else {
            ErrorReport.report(0);
        }
    } 
}
