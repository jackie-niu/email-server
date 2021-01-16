import java.util.Date;
public class Message {
    private char command = Globals.CHR_NULL;
    private String sender = Globals.STR_NULL;
    private String receiver = Globals.STR_NULL;
    private String dateTime = Globals.STR_NULL;
    private char firstRecordMarker = Globals.CHR_NULL;
    private String subject = Globals.STR_NULL;
    private char eosMarker = Globals.CHR_NULL;
    private String text = Globals.STR_NULL;
    
    public Message() {
	command = Globals.CHR_NULL;
	sender = Globals.STR_NULL;
	receiver = Globals.STR_NULL;
	dateTime = Globals.STR_NULL;
	firstRecordMarker = Globals.CHR_NULL;
	subject = Globals.STR_NULL;
	eosMarker = Globals.CHR_NULL;
	text = Globals.STR_NULL;     
    }
    
    public Message(String s) {
	setMessage(s);
    } 
    
    // setMessage() method breaks the string s into each of the fields
    // of a message (see slide 24 of Unit 2 case study server pdf)
    
    public void setMessage(String s) {
	command = s.charAt(Globals.COMMAND_POS);
	sender = s.substring(Globals.SENDER_POS, Globals.SENDER_POS + Globals.SENDER_LEN);
	receiver = s.substring(Globals.RECEIVER_POS, Globals.RECEIVER_POS + Globals.RECEIVER_LEN);
	dateTime = s.substring(Globals.DATE_TIME_POS, Globals.DATE_TIME_POS + Globals.DATE_TIME_LEN);
	firstRecordMarker = s.charAt(Globals.FIRST_RECORD_MARKER_POS);
	
	// for subject and text an overloaded version of indexOf) is used
	// because we need to skip over the dateTime field when searching
	// for the END_OF_SUBJECT_MARKER in the string s
	
	subject = s.substring(Globals.FIRST_RECORD_MARKER_POS + 1, s.indexOf(Globals.END_OF_SUBJECT_MARKER, Globals.FIRST_RECORD_MARKER_POS + 1));
	
	eosMarker = Globals.END_OF_SUBJECT_MARKER;
	
	text = s.substring(s.indexOf(Globals.END_OF_SUBJECT_MARKER, Globals.FIRST_RECORD_MARKER_POS + 1) + 1);
    }
    
    public String getMessage() {
	return command + sender + receiver + dateTime + firstRecordMarker + subject + eosMarker + text;
    }
    
    public String getText() {
	return text;
    }
    
    public String getIdSenderFirst() {
	return sender + receiver + dateTime;
    }
    
    public String getIdReceiverFirst() {
	return receiver + sender + dateTime;
    }
    
    public void readFromMessagesFile(int recordNumber) {
	String data = Globals.STR_NULL;
	Record record = new Record();
	
	do {
	    record.readFromMessagesFile(recordNumber);
	    data = data + record.getData();
	    recordNumber = record.getNext();
	} while (recordNumber != Globals.END_OF_MESSAGE);
	setMessage(data);
    }
    
    public int writeToMessagesFile() {
	String s = getMessage();
	int recordNumber = -1;
	int startRecordNumber = Globals.totalRecordsInMessageFile;
	
	Record record = null;
	while (s.length() > 0) {
	    recordNumber = Globals.totalRecordsInMessageFile;
	    if (s.length() <= Globals.RECORD_DATA_LEN) {
		record = new Record(s, Globals.END_OF_MESSAGE);
		s = Globals.STR_NULL;
	    }
	    else {
		record = new Record(s.substring(0, Globals.RECORD_DATA_LEN), recordNumber +1);
		s = s.substring(Globals.RECORD_DATA_LEN);
	    }
	    record.writeToMessagesFile(recordNumber, Globals.APPEND);
	}
	return startRecordNumber;
    }
    
    public String toString() {
	Date date = new Date(Utils.bytesStrToLong(dateTime));
	return "Command     : " + command  + "\n" +
	       "Sender      : " + sender   + "\n" +
	       "Receiver    : " + receiver + "\n" +
	       "Date/Time   : " + date.toString() + "\n" +
	       "FR Marker   : " + firstRecordMarker + "\n" +
	       "Subject     : " + subject  + "\n" +
	       "EOS Marker  : " + eosMarker + "\n" +
	       "Message text: " + text;
    }
}
