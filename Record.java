import java.io.*;
public class Record {
    private byte[] data = new byte[Globals.RECORD_DATA_LEN];
    private int next = Globals.END_OF_MESSAGE;
    
    public Record() {
	for (int i = 0; i < Globals.RECORD_DATA_LEN; i++) {
	    data[i] = (byte) Globals.BLANK;
	}
	next = Globals.END_OF_MESSAGE;
    }
    
    public Record(String s, int nextRecord) {
	setData(s, nextRecord);
    }
    
    public String getData() { // 
	String s = "";
	for (int i = 0; i < Globals.RECORD_DATA_LEN; i++)  {
	    s = s + (char) ((data[i] + 256) % 256); // removes the negatives so data isn't all wrong
	}
	return s;
    }
    
    public void setData(String s, int nextRecord) {
	int i = 0;
	for (; i < s.length(); i++) {
	    data[i] = (byte) s.charAt(i); 
	}
	for (; i < Globals.RECORD_DATA_LEN; i++) {
	    data[i] = (byte) Globals.BLANK;
	}
	next = nextRecord;
    }
    
    public int getNext() {
	return next;
    }
    
    public int readFromMessagesFile(int recordNumber) {
	try {
	    Globals.msg.seek(recordNumber * Globals.RECORD_LEN);
	    int bytes = Globals.msg.read(data); // pointer goes to RECORD_DATA_LEN
	    // for debugging, reads length of "data" array .read will fill up data array
	    next = Globals.msg.readInt(); // Globals.msg is _messages file, 4 byte int will be read
	    return Globals.PROCESS_OK;
	}
	catch(IOException error) {
	    return Globals.PROCESS_ERROR;
	}
    }    
    
    public int writeToMessagesFile(int recordNumber, int mode) {
	try {
	    Globals.msg.seek(recordNumber * Globals.RECORD_LEN); // first record goes to 0
	    Globals.msg.write(data);
	    Globals.msg.writeInt(next);
	    
	    if (mode == Globals.APPEND) { // append is if no holes - adding to the end
		Globals.totalRecordsInMessageFile++;
	    }
	    
	    return Globals.PROCESS_OK;
	}
	catch(IOException error) {
	    return Globals.PROCESS_ERROR;
	}
    }
    
    public int deleteFromMessagesFile(int recordNumber) {
	int error = readFromMessagesFile(recordNumber);
	
	if (error == Globals.PROCESS_OK) {
	    data[0] = (byte) Globals.DELETED;
	    int tmp = next;
	    next = Globals.END_OF_MESSAGE;
	    
	    error = writeToMessagesFile(recordNumber, Globals.MODIFY);
	    next = tmp; // restore so that the calling method can continue deleting
	    
	    if (error == Globals.PROCESS_OK) {
		Globals.availableList.addRecord(recordNumber);
	    }
	    else {
		System.out.println("error writing a deleted record to _messages.dat");
	    }
	}
	else {
	    System.out.println("error reading a record from _messages.dat");
	}
	return error;  
    }
    
    public String toString() {
	return getData() + " " + next;
    }
}
