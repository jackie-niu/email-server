public class LearningLinkedLists {
    public static void main(String[] args) {
	/*SNode head = null;
	
	head = new SNode("Emma", "007", 39, null);
	
	SNode p = new SNode("Malcolm", "911", 2, null);
	head.setNext(p);
	
	p = new SNode("Raymond", "TTC", 0, null);
	head.getNext().setNext(p);
	
	for (p = head; p != null; p = p.getNext()) {
	    System.out.println(p);
	}*/
	
       /* LList list = new LList();
	SNode p = new SNode("Emma", "123", -23, null);
	
	list.insertNode(p);
	p = new SNode("David", "000", 37, null);
	list.insertNode(p);
	System.out.println(list);
	list.deleteNode(p);
	System.out.println(list);*/
	
	LList list = new LList();
	System.out.println(list);
	
	SNode p = new SNode("Emma", "000", 39, null);
	list.insertNode(p);
	
	p = new SNode("Samuel", "123", 60, null);
	list.insertNode(p);
	
	p = new SNode("Diego", "400", 78, null);
	list.insertNode(p);
	
	p = new SNode("Benjamin", "238", 75, null);
	list.insertNode(p);
	
	System.out.println(list);
	
	p = list.findNode("Michal");
	if (p != null) {
	    System.out.println(p);
	}
	else {
	    System.out.println("key not in list");
	}
    }
}
