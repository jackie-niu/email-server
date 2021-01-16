public class TestingLinkedListMethods {
    public static void main(String[] args) {
	LList list = new LList();
	
	SNode p = new SNode("1000", "AAA", 1000, null);
	list.insertNode(p);
	
	p = new SNode("3000", "CCC", 3000, null);
	list.insertNode(p);
	
	p = new SNode("2000", "BBB", 2000, null);
	list.insertNode(p);
	
	p = new SNode("5000", "EEE", 5000, null);
	list.insertNode(p);
	
	p = new SNode("4000", "DDD", 4000, null);
	list.insertNode(p);
	
	System.out.println(list);
	
	for (int times = 1; times <= 50; times++) {
	    for (int i = 1; i <= 1000; i++) {
		int n = (int) (Math.random() * 9000) + 1000;
		String key = "" + n;
	    
		p = new SNode(key, "ZZZ", n, null);
		list.insertNode(p);
	    
		n = (int) (Math.random() * 9000) + 1000;
		key = "" + n;
	    
		if (!(key.equals("1000") ||
		      key.equals("2000") ||
		      key.equals("3000") ||
		      key.equals("4000") ||
		      key.equals("5000"))) {
		      p = list.findNode(key);
		      list.deleteNode(p);
		}
	    }
	}
	System.out.println(list);

	
	
	/*
	perform insertions and deletions at random
	check that five nodes are still on list
	
	delete all nodes except original fiv
	check that five nodes are remainig on list
	*/
    }
}
