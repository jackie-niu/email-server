import java.io.*;
import java.net.*;

public class NetIO {
    public static final int BUFFER_SIZE = 2048;
    
    public static String myIPAddress() {
	String ipAddress = "";
	try {
	    InetAddress me = InetAddress.getLocalHost ();
	    ipAddress = me.getHostAddress();
	}
	catch(Exception e) {
	    e.printStackTrace ();
	}
	return ipAddress;
    }

    public static String myUserName() {
	String userName = "";
	try {
	    userName = System.getProperty("user.name");
	}
	catch(Exception e) {
	    e.printStackTrace ();
	}
	return userName;
    }
	
    public static int sendRequest (String message, String destinationIPAddress) {
	int errorCode = Globals.NET_SEND_ERROR; 
	int attempts  = 0;
	do {
	    try {
		// create socket and timeout
		Socket me = new Socket();
		me.connect(new InetSocketAddress(destinationIPAddress, Globals.PORT_NUMBER), Globals.TIME_OUT);
		me.setSoTimeout(Globals.TIME_OUT);

		// get output stream and send request
		DataOutputStream output = new DataOutputStream (me.getOutputStream());
		output.writeUTF(message); 

		// wait for confirmation code from receiving node 
		DataInputStream input = new DataInputStream(me.getInputStream());
		String request = input.readUTF();
		
if (Globals.DEBUG_ON) {
    System.out.println("In sendRequest(): Confirmation from server: $" + request + "$");
}
		if (isANumber(request))
		    errorCode = Integer.parseInt(request);

		me.close ();
	    }
	    catch (IOException e) {
		if (Globals.DEBUG_ON) {
		    System.out.println("Exception thrown in sendRequest");
		    //e.printStackTrace ();
		}  
	    }                  
	    attempts++;
	} while (errorCode != Globals.NET_OK && attempts < Globals.SENDING_ATTEMPTS_LIMIT);
	
	if (Globals.DEBUG_ON)
	    System.out.println("errorCode=" + errorCode + " attempts=" + attempts);
	
	return errorCode;
    }
    
    public static String receiveRequest (){
	String request = Globals.STR_NULL;
	int errorCode = Globals.NET_RECEIVE_ERROR;
	try {
	    // create server socket
	    ServerSocket server = new ServerSocket(Globals.PORT_NUMBER, 100);
	    
	    // create socket and timeout
	    Socket me = server.accept();
	    me.setSoTimeout(Globals.TIME_OUT);
		    
	    // get input stream and receive request
	    DataInputStream input = new DataInputStream(me.getInputStream());
	    request = input.readUTF();
	    // get client's ip-address
	    Globals.clientIPAddress = me.getInetAddress().getHostAddress();
	    
	    //Utils.delay(400);
	    // get output stream and send confirmation
	    DataOutputStream output = new DataOutputStream(me.getOutputStream());
	    output.writeUTF(Globals.STR_NULL + Globals.NET_OK); 

	    me.close ();               
	    server.close ();
	    errorCode = Globals.NET_OK;
	}
	catch (IOException e) {
	    if (Globals.DEBUG_ON) {            
		System.out.println("Exception thrown in receiveRequest");
		e.printStackTrace ();
	    }
	}
	return request;
    }
    
    private static boolean isANumber (String s) {
	boolean result = true;
	for (int i = 0 ; i < s.length () ; i++)
	    result = result && Character.isDigit(s.charAt (i));
	return result;
    }
}

