public class LList {
    private SNode head = null;
    private SNode tail = null;
    
    public LList() {
	head = null;
	tail = null;
    }
    
    public SNode findNode(String key) {
	SNode p = head;
	while (p != null && !(p.getName().equals(key))) {
	    p = p.getNext();
	}
	return p;
    }
    
    public SNode insertNode(SNode p) {
	if (head == null) {
	    head = p;
	    tail = p;
	}
	else if (p.getName().compareTo(head.getName()) <= 0) {
	    p.setNext(head);
	    head = p;
	}
	
	else if (p.getName().compareTo(tail.getName()) >= 0) {
	    tail.setNext(p);
	    tail = p;
	}
	// we use setNext because setNext is a private field, we must use an access method
	else {
	    SNode q = head.getNext(); // we don't need to instantiate this because head already exists; we don't need to make a whole new node
	    SNode r = head;
	    for (; p.getName().compareTo(q.getName()) > 0; q = q.getNext()) {
		r = q;                
	    }
	    p.setNext(q);
	    r.setNext(p);
	}
	return head;
    }
    
    public SNode deleteNode(SNode p) {
	if (p != null) {
	    if (head == tail) {
		head = null;
		tail = null;
	    }
	    else if (p == head) {
		head = head.getNext();
	    }
	    else if (p == tail) {
		SNode r = head;
		for (; r.getNext() != p; r = r.getNext());
		tail = r;
		tail.setNext(null);
	    }
	    else {
		SNode r = head;
		for (; r.getNext() != p; r.getNext());
		r.setNext(p.getNext());
	    }
	    p = null;
	}
	return head;
    }
    
    public String toString() {
	if (head == null) {
	    return "(empty)";
	}
	else {
	    String s = "";
	    for (SNode p = head; p != null; p = p.getNext()) {
		s = s + p.toString() + "\n";
	    }
	    return s;
	}
    }
}
