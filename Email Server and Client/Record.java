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
    
    public String getData() {
        String s = "";
        for (int i = 0; i < Globals.RECORD_DATA_LEN; i++) {
            s = s + (char) ((data[i] + 256) % 256);
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
           int bytes = Globals.msg.read(data);
           next = Globals.msg.readInt();
           return Globals.PROCESS_OK;
        }
        catch(IOException error) {
            return Globals.PROCESS_ERROR;
        }
    }
    
    public int writeToMessagesFile(int recordNumber, int mode) {
        try {
            Globals.msg.seek(recordNumber * Globals.RECORD_LEN);
            Globals.msg.write(data);
            Globals.msg.writeInt(next);
            
            if (mode == Globals.APPEND) {
                Globals.totalRecordsInMessagesFile++;
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
                System.out.println("Error writing to messages file. In method deleteFromMessagesFile");
            }
        }
        else {
            System.out.println("Error reading from messages file. In method deleteFromMessageFile");
        }
        return error;
    }

    public String getIdSenderFirst() {
        return getData().substring(Globals.SENDER_POS, Globals.RECEIVER_POS) +
               getData().substring(Globals.RECEIVER_POS, Globals.DATE_TIME_POS) +
               getData().substring(Globals.DATE_TIME_POS, Globals.FIRST_RECORD_MARKER_POS);
    }
    
    public String getIdReceiverFirst() {
        return getData().substring(Globals.RECEIVER_POS, Globals.DATE_TIME_POS) +
               getData().substring(Globals.SENDER_POS, Globals.RECEIVER_POS) +
               getData().substring(Globals.DATE_TIME_POS, Globals.FIRST_RECORD_MARKER_POS);
    }
    
    public String toString() {
        return getData() + " " + next;
    }
}














