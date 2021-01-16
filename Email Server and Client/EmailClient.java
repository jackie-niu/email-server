import hsa.*;
import java.util.Date;
public class EmailClient {
    public static void main(String[] args) {

	Init.initializeBoxMessages();
	for (int i = 0 ; i < Globals.MAX_CLIENT_MESSAGES; i++) {
	    Globals.boxMessages[i] = "S" + 
				    Utils.generateRandomCode(3) + "SENDER" +  
				    Utils.generateRandomCode(3) + "RECEIV" +
				    Utils.longToBytesStr(System.currentTimeMillis() + 5000 * i) + 
				    "+This is subject " + (999 - i) + 
				    Globals.END_OF_SUBJECT_MARKER + 
				    "This is text for message " + (999 - i);
	}
	SearchAndSort.selectionSort(Globals.boxMessages, Globals.DATE_TIME_POS, false);
	EmailClientGUI gui = new EmailClientGUI();
	EmailClientGUI gui2 = new EmailClientGUI();
    }
}
