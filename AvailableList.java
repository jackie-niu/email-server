public class AvailableList {
    private Available head = null;
    private Available tail = null;
    
    public AvailableList() {
	head = null;
	tail = null;
    } 
    
    public AvailableList(Available h, Available t) {
	head = h;
	tail = t;
    }
    
    public Available getHead() {
	return head;
    }
    
    public Available getTail() {
	return tail;
    }
    
    public void setHead(Available p) {
	head = p;
    }
    
    public void setTail(Available p) {
	tail = p;
    }
    
    public void addRecord(int recordNumber) {
	Available p = new Available(recordNumber);
	if (head == null) {
	    head = p;
	}
	else {
	    tail.setNext(p);
	}
	tail = p;
    }
    
    public void buildFromMessagesFile() {
	Record record = new Record();
	for (int r = 0; r < Globals.totalRecordsInMessageFile; r++) {
	    record.readFromMessagesFile(r);
	    if (record.getData().charAt(0) == Globals.DELETED) {
		addRecord(r);
	    }
	}
    }
    
    public int getNextRecord() {
	int recordNumber = Globals.EMPTY_AVAILABLE_LIST;
	if (head == null) {
	    return Globals.EMPTY_AVAILABLE_LIST;
	}
	
	else {
	    tail = head == tail ? null : tail;
	    int r = head.getRecordNumber();
	    head = head.getNext();
	    return r;
	}
    }
    
    public String toString() {
	if (head == null) {
	    return "(empty)";
	}
	else {
	    String s = "";
	    for (Available p = head; p != null; p = p.getNext()) {
		s = s + p.toString() + " ";
	    }
	    return s;
	}
    }
}
