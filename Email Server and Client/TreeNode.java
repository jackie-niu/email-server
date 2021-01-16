import java.util.Date;

public class TreeNode {
    private String id = Globals.STR_NULL;
    private int recordNumber = -1;
    private TreeNode left   = null;
    private TreeNode right  = null;
    private TreeNode parent = null;
    
    public TreeNode() {
        id = Globals.STR_NULL;
        recordNumber = -1;
        left   = null;
        right  = null;
        parent = null;
    }
    
    public TreeNode(String i, int rn, TreeNode l, TreeNode r, TreeNode p) {
        id = i;
        recordNumber = rn;
        left   = l;
        right  = r;
        parent = p;
    }
    
    public String getId() {
        return id;
    }
    
    public int getRecordNumber() {
        return recordNumber;
    }
    
    public TreeNode getLeft() {
        return left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
    public TreeNode getParent() {
        return parent;
    }
    
    public void setId(String i) {
        id = i;
    }
    
    public void setRecordNumber(int rn) {
        recordNumber = rn;
    }
    
    public void setLeft(TreeNode l) {
        left = l;
    }
    
    public void setRight(TreeNode r) {
        right = r;
    }
    
    public void setParent(TreeNode p) {
        parent = p;
    }
    
    public String toString() {
        if (this == null)
            return "null";
        else {
            Date date = new Date(Utils.bytesStrToLong(id.substring(Globals.DATE_TIME_POS - 1)));
            return "Id: " + id.substring(0, Globals.DATE_TIME_POS - 1) +
                    date.toString() + 
                    " Record number: " + recordNumber;
        }
    }
}














