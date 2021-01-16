public class Init {
    public static void initializeBoxMessages() {
	for (int i = 0; i < Globals.boxMessages.length; i++) {
	    Globals.boxMessages[i] = Globals.EMPTY_CLIENT_MESSAGE;
	}
   }    
}
