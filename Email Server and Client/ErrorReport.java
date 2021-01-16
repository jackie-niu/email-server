public class ErrorReport {
    public static void report(int errorNumber) {
        String errorMessage = "";
        switch(errorNumber) {
            case 0  : errorMessage = "Error initializing system";
                      break;
            case 1  : errorMessage = "01 Error opening messages file" + Globals.MESSAGES_FILE;
                      break;
            case 2  : errorMessage = "02 Error opening available list file " + Globals.AVAILABLE_LIST_FILE;
                      break;
            case 3  : errorMessage = "Error retrieving accounts " + Globals.ACCOUNTS_FILE;
                      break;
            case 4  : errorMessage = "Error opening sender index file " + Globals.SENDER_INDEX_FILE;
                      break;
            case 5  : errorMessage = "Error opening receiver index file " + Globals.RECEIVER_INDEX_FILE;
                      break;
            default : errorMessage = "Unknown error";
                      break;
        }
        System.out.println(errorMessage);
    }
} 
