public class SNode {
    private String name = "";
    private String id   = "";
    private double avg  = -1;
    private SNode next = null;
    
    public SNode() {
	name = "";
	id = "";
	avg = -1;
	next = null;
    }
    
    public SNode(String n, String i, double a, SNode p) {
	name = n;
	id = i;
	avg = a;
	next = p;
    }
    
    public SNode getNext() {
	return next;
    }
    
    public String getName() {
	return name;
    }
    
    public void setNext(SNode p) {
	next = p;
    }
    
    public String toString() {
	return name + " " + id + " " + avg;
    }
}
