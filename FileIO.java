import java.io.*;
public class FileIO {
    public static int openMessagesFile (String fileName) {
	try {
	    Globals.msg = new RandomAccessFile(fileName, "rw");
	    Globals.totalRecordsInMessageFile = (int) (Globals.msg.length() / Globals.RECORD_LEN);
	}
	catch (IOException error) {
	    return Globals.PROCESS_ERROR;
	}
	return Globals.PROCESS_OK;
    }
    
    public static int closeMessagesFile() {
	try {
	    Globals.msg.close();
	}
	catch (IOException error) {
	    return Globals.PROCESS_ERROR;
	}
	return Globals.PROCESS_OK;
    }
}
