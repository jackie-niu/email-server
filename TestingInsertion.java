public class TestingInsertion {
    public static void main(String[] args) {
	LList list = new LList();
	
	SNode p = new SNode("David", "007", 103, null);
	
	for (int i = 1000; i < 5000; i++) {
	    p = new SNode("" + (int) (Math.random() * 1000), "" + (10 * i), i % 100, null);
	    list.insertNode(p);
	}
	
	System.out.println(list); 
    }
}
