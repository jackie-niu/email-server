public class SearchAndSort{
    public static String[] mergeLists(String[] list1, String[] list2) {
	int p = 0;
	int q = 0;
	int r = 0;
    
	String[] resultList = new String[list1.length + list2.length];
    
	while (r < resultList.length) {
	    if (p == list1.length) {
		resultList[r] = list2[q];
		q++;            
	    }
	    else if (q == list2.length) { 
		resultList[r] = list1[p];
		p++;
	    }
	    else if (list1[p].compareTo(list2[q]) <= 0)  {
		resultList[r] = list1[p];
		p++;
	    }
	    else {
		resultList[r] = list2[q];
		q++;
	    }
	    r++;
	}
	return resultList;
    }

    public static int linearSearch(String[] list, String key) {
	int i = 0;
	for (; i < list.length && !key.equals(list[i]); i++);
	return i == list.length ? -1 : i;
    }
    
    public static int binarySearch(String[] list, String key) {
	int start = 0;
	int end   = list.length - 1;
	int idx   = (start + end) / 2;
	
	while (!key.equals(list[idx]) && start <= end) {
	    if (key.compareTo(list[idx]) > 0) {
		start = idx + 1;
	    }
	    else {
		end = idx - 1;
	    }
	    idx   = (start + end) / 2;
	}
	if (start > end) {
	    return -1;
	}
	else {
	    return idx;
	}
    }

    public static void printStrings(String[] list){
	for(int i = 0; i < list.length; i++){
	    System.out.println(list[i]);
	}
    }
    
    public static int indexOfLargest(String[] list, int end, int sortPosition, boolean ascending){
	int positionOfLargest = 0;
	
	if (ascending) {
	    for (int i = 1; i <= end; i++){
		positionOfLargest = list[i].substring(sortPosition).compareTo(list[positionOfLargest].substring(sortPosition)) > 0 ? i : positionOfLargest;
	    }
	}
	else {
	    for (int i = 1; i <= end; i++){
		positionOfLargest = list[i].substring(sortPosition).compareTo(list[positionOfLargest].substring(sortPosition)) < 0 ? i : positionOfLargest;
	    }
	}
	return positionOfLargest;
    }
    
    public static void selectionSort(String[] list, int sortPosition, boolean ascending){
	int bottom = 0;
	for (; bottom < list.length && !list[bottom].equals(""); bottom++);
	bottom--;
	
	for (int i = bottom; i >= 1; i--){
	    int k = indexOfLargest(list, i, sortPosition, ascending);
	    swapStrings(list, k, i);        
	}
    }

    //swapStrings method assumes that array is non-empty and i and j are within array bounds
    public static void swapStrings(String[] list, int i, int j){
	String tmp = list[i];
	list[i] = list[j];
	list[j] = tmp;
    }
    
    public static void bubbleSort(String[] items) {
	for (int limit = items.length - 1; limit >= 1; limit--) {
	    for (int i = 0; i < limit; i++) {
		if (items[i].compareTo(items[i + 1]) > 0) {
		    swapStrings(items, i, i + 1);
		}
	    }
	}
    }
    
    private static void shiftArrayDownOnePosition(String[] list, int i, int j) {
	for (int k = j; k >= i; k--) {
	    list[k + 1] = list[k];
	}
    }
    
    public static void insertionSort(String[] list) {
	for (int i = 1; i < list.length; i++) {
	    boolean largerItemFound = false;
	    int j = 0;
	    for (; j <= i - 1 && !largerItemFound; j++) {
		largerItemFound = list[i].compareTo(list[j]) < 0;
	    }
	    j--;
	    if (largerItemFound) {
		String temp = list[i];
		shiftArrayDownOnePosition(list, j, i - 1);
		list[j] = temp; 
	    }
	} 
    } 

    private static String[] merge(String[] tList, String[] bList) {
	String[] list = new String[tList.length + bList.length];
	
	int p = 0;
	int q = 0;
	int r = 0;
	
	while (p < tList.length || q < bList.length) {
	    if (p == tList.length) {
		list[r] = bList[q];
		q++;                
	    }
	    else if (q == bList.length) {
		list[r] = tList[p];
		p++;
	    }
	    else if (tList[p].compareTo(bList[q]) <= 0) {
		list[r] = tList[p];
		p++;
	    }
	    else {
		list[r] = bList[q];
		q++;
	    }
	    r++;
	}
	       
	return list;
    }
    
    public static String[] mergeSort(String[] list) {
	if (list.length <= 1) {
	}
	else {
	    String[] tList = new String[list.length / 2];           
	    for (int i = 0; i < tList.length; i++) {
		tList[i] = list[i];
	    }
	    tList = mergeSort(tList);
	    
	    String[] bList = list.length % 2 == 0 ? 
			     new String[list.length / 2] : 
			     new String[list.length / 2 + 1];
	    for (int i = 0; i < bList.length; i++) {
		bList[i] = list[tList.length + i];
	    }             
	    bList = mergeSort(bList);
	    
	    list = merge(tList, bList);
	}
	return list;
    }   
}








