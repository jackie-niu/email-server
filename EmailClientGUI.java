import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmailClientGUI implements ActionListener {
    private JFrame frame = null;
    private JPanel panel1 = null;
    private JPanel panel2 = null;
    private JPanel panel3 = null;
    
    private EmailClientPane eMailClientPane = null;
    
    private JButton fromOrTo = null;
    private JButton dateTime = null;
    private JButton subject  = null;
    private JButton compose  = null;
    private JButton delete   = null;
    private JButton inBox    = null;
    private JButton outBox   = null;
    private JButton serverShutdown = null;
    
    private Icon triangleUp = null;
    private Icon triangleDn = null;
    
    private int currentScreen = Globals.RECEIVER_ID;
    private int inBoxSort  = Globals.DATE_TIME_POS;
    private int outBoxSort = Globals.DATE_TIME_POS;
    private boolean inBoxOrderAscending = true;
    private boolean outBoxOrderAscending = true;
    
    private void drawTopButtons() {
    }
    
    public EmailClientGUI() {  
	currentScreen = Globals.RECEIVER_ID; 
	
	frame = new JFrame("Bloor CI Email Client Version 2019.1");
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setLocation(100, 50);
	frame.setResizable(false);
	// frame.addWindowListener(new WindowEventHandler());

	Container contentPane = frame.getContentPane();
	BoxLayout contentPaneLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
	contentPane.setLayout(contentPaneLayout);
	
	panel1 = new JPanel();
	panel2 = new JPanel();
	panel3 = new JPanel();
	
	contentPane.add(panel1);
	contentPane.add(panel2);
	contentPane.add(panel3);
	
	// set up the first panel
	FlowLayout panel1Layout = new FlowLayout(FlowLayout.LEFT);
	panel1.setLayout(panel1Layout);
	
	triangleUp = new ImageIcon("triangle up.jpg");
	triangleDn = new ImageIcon("triangle dn.jpg");
	
	fromOrTo = new JButton("From");
	fromOrTo.setHorizontalTextPosition(JButton.LEFT);
	fromOrTo.setPreferredSize(new Dimension(85, 14));
	
	dateTime  = new JButton("Date Sent");
	dateTime.setHorizontalTextPosition(JButton.LEFT);
	dateTime.setPreferredSize(new Dimension(235, 14));
	
	subject = new JButton("Subject");
	subject.setHorizontalTextPosition(JButton.LEFT);
	subject.setPreferredSize(new Dimension(445, 14));

	drawTopButtons();
	
	fromOrTo.addActionListener(this);
	dateTime.addActionListener(this);
	subject.addActionListener(this);

	panel1.add(fromOrTo);
	panel1.add(dateTime);
	panel1.add(subject);
	
	// set up the second panel
	FlowLayout panel2Layout = new FlowLayout(FlowLayout.LEFT);
	panel2.setLayout(panel2Layout);
	currentScreen = Globals.RECEIVER_ID;
	eMailClientPane = new EmailClientPane(currentScreen);
	panel2.add(eMailClientPane.getSplitPane());
	
	// set up the third panel
	FlowLayout panel3Layout = new FlowLayout(FlowLayout.CENTER);
	panel3.setLayout(panel3Layout);
	panel3.setPreferredSize(new Dimension(800, 40));

	compose = new JButton("Compose");
	inBox   = new JButton("InBox");
	outBox  = new JButton("OutBox");
	delete  = new JButton("Delete");
	serverShutdown = new JButton("Server Shutdown");

	compose.addActionListener(this);
	inBox.addActionListener(this);
	outBox.addActionListener(this);
	delete.addActionListener(this);
	serverShutdown.addActionListener(this);
	
	panel3.add(compose);
	panel3.add(inBox);
	panel3.add(outBox);
	panel3.add(delete);
	panel3.add(serverShutdown);
	
	frame.pack();
	frame.setVisible(true);
    }
    
    private void refreshPane() {
	panel2.remove(eMailClientPane.getSplitPane());
	eMailClientPane = new EmailClientPane(currentScreen);
	panel2.add(eMailClientPane.getSplitPane());
	frame.pack();
	frame.setVisible(true);              
    }
    
    public void actionPerformed(ActionEvent event) {

	Object buttonPressed = event.getSource();
	
	if (buttonPressed == compose) {
	    EmailClientComposeMessage c = new EmailClientComposeMessage();
	}
	else if (buttonPressed == delete) {
	    JOptionPane.showMessageDialog(null, "Delete Message", "ICS", JOptionPane.ERROR_MESSAGE);
	}
	else if (buttonPressed == inBox) {
 int error = 0;
	    if (error == Globals.PROCESS_OK) {
		fromOrTo.setText("From");
		currentScreen = Globals.RECEIVER_ID;
		SearchAndSort.selectionSort(Globals.boxMessages, inBoxSort, inBoxOrderAscending);
		refreshPane();
	    }
	    else if (Globals.DEBUG_ON) {
		System.out.println("Error loading boxMessages: Globals.boxMessages[] is now not consistent with display");
	    }
	}
	else if (buttonPressed == outBox) {
 int error = 0;
	    if (error == Globals.PROCESS_OK) {
		fromOrTo.setText("To");
		currentScreen = Globals.SENDER_ID;
		SearchAndSort.selectionSort(Globals.boxMessages, inBoxSort, outBoxOrderAscending);
		refreshPane();
	    }
	    else if (Globals.DEBUG_ON) {
		System.out.println("Error loading boxMessages: Globals.boxMessages[] is now not consistent with display");
	    }
	}
	else if (buttonPressed == serverShutdown) {
	}
	else if (buttonPressed == fromOrTo) {
	    if (currentScreen == Globals.RECEIVER_ID) {
		inBoxOrderAscending = !inBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.SENDER_POS, inBoxOrderAscending);
		inBoxSort = Globals.SENDER_POS;
	    }
	    else {
		outBoxOrderAscending = !outBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.RECEIVER_POS, outBoxOrderAscending);    
		outBoxSort = Globals.RECEIVER_POS;
	    }
	    refreshPane();
	}
	else if (buttonPressed == subject) {
	    if (currentScreen == Globals.RECEIVER_ID) {
		inBoxSort = Globals.SUBJECT_POS;
		inBoxOrderAscending = !inBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.SUBJECT_POS, inBoxOrderAscending);
	    }
	    else {
		outBoxSort = Globals.SUBJECT_POS;
		outBoxOrderAscending = !outBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.SUBJECT_POS, outBoxOrderAscending);
	    }
	    refreshPane();
	}
	else if (buttonPressed == dateTime) {
	    if (currentScreen == Globals.RECEIVER_ID) {
		inBoxSort = Globals.DATE_TIME_POS;
		inBoxOrderAscending = !inBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.DATE_TIME_POS, inBoxOrderAscending);

	    }
	    else {
		outBoxSort = Globals.DATE_TIME_POS;
		outBoxOrderAscending = !outBoxOrderAscending;
		SearchAndSort.selectionSort(Globals.boxMessages, Globals.DATE_TIME_POS, outBoxOrderAscending);
	    }
	    refreshPane();
	}
    }
    
    private static void updateBoxMessages(int itemIndex) {
	int i = itemIndex;
	for (i = itemIndex; i < (Globals.boxMessages.length - 1) && !Globals.boxMessages[i].equals(Globals.EMPTY_CLIENT_MESSAGE); i++) {
	    Globals.boxMessages[i] = Globals.boxMessages[i + 1];
	}
	Globals.boxMessages[i] = Globals.EMPTY_CLIENT_MESSAGE;
    }
}
