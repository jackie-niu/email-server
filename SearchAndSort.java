public class SearchAndSort {
    public static void printStrings(String[] string) {
	for (int i = 0; i < string.length; i++) {
	    System.out.println(string[i]);
	}
    }
    
    public static int indexOfLargest(String[] list, int end, int positionOfKeyword, boolean ascending) {
	int positionOfLargest = 0;
	for (int i = 0; i <= end; i++) {
	    if (ascending) {
		positionOfLargest = list[i].substring(positionOfKeyword).compareTo(list[positionOfLargest].substring(positionOfKeyword)) > 0 ? i : positionOfLargest;           
	    }
	    else {
		positionOfLargest = list[i].substring(positionOfKeyword).compareTo(list[positionOfLargest].substring(positionOfKeyword)) < 0 ? i : positionOfLargest; 
	    } 
	}
	return positionOfLargest;
    }
    
    // swapStrings() method assumes a non-null array
    // indices i and j are within bounds of the array
    
    public static void swapStrings(String[] string, int i, int j) {
	String tmp = string[i];
	string[i] = string[j];
	string[j] = tmp;
    }
    
    public static void selectionSort(String[] list, int startByte, boolean ascending) {
	int bottom = 0;
	for (; bottom < list.length && !list[bottom].equals(""); bottom++);
	bottom--;
	
	for (int i = bottom; i > 0; i--) {
		swapStrings(list, indexOfLargest(list, i, startByte, ascending), i);
	}
    }

    public static void bubbleSort(String[] list) {
	boolean passThrough = true;
	for (int limit = list.length - 1; limit >= 1 && passThrough; limit --) {
	    passThrough = false;
	    for (int i = 0; i < limit; i++) {
		if (list[i].compareTo(list[i + 1]) > 0) {
		    swapStrings(list, i, i + 1);
		    passThrough = true;
System.out.println("element swapped");
		}
System.out.println(limit);
	    }
	}
    }
    
    private static void shiftArrayDownOnePosition (String[] list, int start, int end) {
	for (int i = end; i >= start; i--) {
	    list[i + 1] = list[i];
	}
    }
    
    public static void insertionSort(String[] list) {
	for (int i = 1; i < list.length; i++) {
	    boolean largerFound = false;
	    int j = 0;
	    for (j = 0; j < i && !largerFound; j++) {
		largerFound = list[i].compareTo(list[j]) < 0;
	    }
	    if (largerFound) {
		String tmp = list[i];
		shiftArrayDownOnePosition(list, j - 1, i - 1);
		list[j - 1] = tmp;
	    }
	}
    }
    
    public static int linearSearch(String[] list, String key) {
	int i = 0;
	for (; i < list.length && !key.equals(list[i]); i++);
	return i == list.length ? -1 : i;        
    }
    
    public static int binarySearch(String[] list, String key) {
	int start = 0;
	int end = list.length - 1;
	int idx = (start + end) / 2;
	
	while (!key.equals(list[idx]) && start <= end) {
	    if (key.compareTo(list[idx]) > 0) {
		start = idx + 1;
	    }
	    else {
		end = idx - 1;
	    }
	    idx = (start + end) / 2;
	}
	if (start > end) {
	    return -1;
	}
	else {
	    return idx;
	}
    }
    
/*    public static void main(String[] args) {
	String[] list = new String[5];
	list[0] = "Ming";
	list[1] = "Jackie";
	list[2] = "Richard";
	list[3] = "Mahir";
	list[4] = "Keith";
	SearchAndSort.selectionSort(list);
	printStrings(list);
	System.out.println();  
	SearchAndSort.bubbleSort(list); 
	printStrings(list);
	System.out.println(); 
	insertionSort(list);
	printStrings(list);
	System.out.println(); 
	
	String[] numbers = new String[500000];
	for (int i = 100000; i < 600000; i++) {
	    numbers[i - 100000] = "" + i;
	}
	
	long startTime = System.currentTimeMillis();
	for (int i = 0; i < 10; i++) {
	    System.out.println(linearSearch(numbers, "143000"));
	}
	System.out.println(System.currentTimeMillis() - startTime);

	startTime = System.currentTimeMillis();
	for (int i = 0; i < 10; i++) {
	    System.out.println(binarySearch(numbers, "143000"));
	}
	System.out.println(System.currentTimeMillis() - startTime);

    } */
}
