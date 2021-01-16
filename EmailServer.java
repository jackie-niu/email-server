import java.io.*;
public class EmailServer {
    public static void main(String[] args) {
	Globals.availableList = new AvailableList();
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if (error == Globals.PROCESS_OK) {
	    Globals.availableList.buildFromMessagesFile();
	    System.out.println(Globals.availableList);
	    Message message = new Message("S012345678ABCDEFGHI00000000+Tracing exercise" + ((char)30) + "This message is going to help us see what will happen when the system tries to write all this text in the messages file. There are some spots available so the file will not grow at all.");
	    int recordNumber = message.writeToMessagesFile();
	    System.out.println(Globals.availableList);
	    
	    message = new Message();
	    message.readFromMessagesFile(recordNumber);
	    System.out.println(message);
	}
	else {
	    System.out.println("Error opening messages file.");
	}
    
	/*Globals.availableList = new AvailableList();
	Message message = new Message();
	
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if (error == Globals.PROCESS_OK) {
	    // to delete a record, you must instantiate a record first ^
	    message.deleteFromMessagesFile(1);
	    message.deleteFromMessagesFile(0);
	    System.out.println(Globals.availableList);
	}
	else {
	    System.out.println("error opening _messages.dat");
	}
	
	AvailableList list = new AvailableList();
	
	list.addRecord(12); // adds node with recordNumber 12
	list.addRecord(6); // adds node with recordNumber 6
	list.addRecord(18); // adds node with recordNumber 18
	System.out.println(list);
	System.out.println();
	
	int r = list.getNextRecord();
	System.out.println(r);
	System.out.println(list);
	System.out.println();
	
	r = list.getNextRecord();
	System.out.println(r);
	System.out.println(list);
	System.out.println();
	
	r = list.getNextRecord();
	System.out.println(r);
	System.out.println(list);
	System.out.println();
	
	r = list.getNextRecord();
	System.out.println(r);
	System.out.println(list);
    
    
	AvailableList list = new AvailableList();
	
	list.addRecord(87);
	list.addRecord(55);
	list.addRecord(23);
	list.addRecord(12);
	
	System.out.println(list);
	
    
	Available head = new Available(5);
	Available p = new Available(27);
	head.setNext(p);
	Available q = new Available(16);
	p.setNext(q);
	p = new Available(38);
	q.setNext(p);
	for (p = head; p != null; p = p.getNext()) {
	    System.out.println(p);
	}
    
	int errorCode = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if (errorCode == Globals.PROCESS_OK) {
	    String s = "SBillGatesSteveJobs^&#@45!!+Hey" + Globals.END_OF_SUBJECT_MARKER + "Hey Steve, are you making money with the MacIntosh?";
	    String t = "SSteveJobsBillGates^&#@45!!+No" + Globals.END_OF_SUBJECT_MARKER + "Hey Gates, no we are not.";
	    
	    int recordNumber = Globals.totalRecordsInMessageFile;
	    
	    Message message = new Message(s);
	    message.writeToMessagesFile();
	    
	    message = new Message(t);
	    message.writeToMessagesFile();
	    
	    message = new Message();
	    message.readFromMessagesFile(recordNumber);
	    System.out.println(message);
	    
	    FileIO.closeMessagesFile();
	}
	
	else {
	    System.out.println("unable to open file");
	}

	int errorCode = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if (errorCode == Globals.PROCESS_OK) {
	    Record record = new Record("Hello, how are you?", 65);
	    record.writeToMessagesFile(0, Globals.APPEND);
	    
	    record = new Record("Movie tonight?", 66);
	    record.writeToMessagesFile(1, Globals.APPEND);
	    
	    record = new Record("I have no money", 67);
	    record.writeToMessagesFile(2, Globals.APPEND);
	    
	    record = new Record("I have a lot of money", 68);
	    record.writeToMessagesFile(3, Globals.APPEND);
	    
	    record = new Record("Can you lend me money", 69);
	    record.writeToMessagesFile(4, Globals.APPEND);
	    
	    FileIO.closeMessagesFile();
	}
	else {
	    System.out.println("error opening file");
	}
	if (errorCode == Globals.PROCESS_OK) {
	    Record record = new Record();
	    
	    for (int i = 4; i >= 0; i--) {
		record.readFromMessagesFile(i);
		System.out.println(record);
	    }
	    FileIO.closeMessagesFile();
	}
	else {
	    System.out.println("error opening file");
	}
	*/
    }
    
}
