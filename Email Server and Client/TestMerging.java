public class TestMerging {
    public static void main(String[] args) {
	String[] list = {"Adib", "Kathy", "Billy", "Hanna", "Zach", "Labib"};
	
	String[] resultList = SearchAndSort.mergeSort(list);
	
	for (int i = 0; i < resultList.length; i++) {
	    System.out.println(resultList[i]);
	}
    }
}
