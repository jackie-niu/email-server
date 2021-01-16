public class Init {
    public static void initializeBoxMessages() {
        for (int i = 0; i < Globals.boxMessages.length; i++) {
            Globals.boxMessages[i] = Globals.EMPTY_CLIENT_MESSAGE;
        }
   }    
    public static int initializeSystem() {
         int  error = Globals.PROCESS_ERROR;
        
           error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
           if (error == Globals.PROCESS_OK) {
            
               error = FileIO.retrieveAvailableList(Globals.AVAILABLE_LIST_FILE);
               if (error == Globals.PROCESS_OK) {
                
                   // Globals.accounts = FileIO.retrieveAccounts(Globals.ACCOUNTS_FILE);
                      // if (Globals.accounts != null) {
                    
                          // these four lines of code for the indices are here temporarily until the retrieval index methods are implemented        
                          Globals.senderIndex = new Tree();
                          Globals.receiverIndex = new Tree();
                          Globals.senderIndex.buildFromMessagesFile();
                          Globals.receiverIndex.buildFromMessagesFile();
                    
                          //error = FileIO.retrieveIndex(Globals.SENDER_INDEX_FILE);
                          if (error == Globals.PROCESS_OK) {
                                 //error = FileIO.retrieveIndex(Globals.RECEIVER_INDEX_FILE);
                                 if (error == Globals.PROCESS_OK) {                         
                                     System.out.println("Data and Index files opened successfully.");
                                 }
                                 else {
                                     ErrorReport.report(5);
                                 }
                          }
                          else {
                                 ErrorReport.report(4);
                          }
                      // }
                      //else {
                      //    ErrorReport.report(3);
                      //}   
               }
               else {
                      ErrorReport.report(2);
               }
           }        
        else {
            ErrorReport.report(1);
        }
        return error;
    }    
}
