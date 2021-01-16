public class TestingTree {
    public static void main(String[] args) {
	TreeNode p = new TreeNode("AK13F5", 200, null, null, null);
	TreeNode q = new TreeNode("BH01H4", 140, null, null, null);
	
	p.setRight(q);
	q.setParent(p);
	
	System.out.println(p);
	System.out.println(q);
	System.out.println(p.getRight());
	System.out.println(q.getParent());
    }
}
