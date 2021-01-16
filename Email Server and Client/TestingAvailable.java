public class TestingAvailable {
    public static void main(String[] args) {
        Available head = new Available(5);
    
        Available p = new Available(27);
        head.setNext(p);
    
        Available q = new Available(16);
        p.setNext(q);
    
        p = new Available(38);
        p.setNext(p);
        
        for (p = head; p != null; p = p.getNext()) {
            System.out.println(p);
        }
    }
}
