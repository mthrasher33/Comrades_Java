package nhcc.CSCI2001.Thrasher.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class CommunistProfileView extends JFrame{
	
	private JLabel firstNameLabel, lastNameLabel, userNameLabel, ageLabel;
	private JPanel profileInfo, chatRoom;
	private JScrollPane scrollPane;
	private JTextArea chatBox;
	private JTextField messageBox;
	private JButton sendMessage, backButton;
	private GridLayout grid=new GridLayout(4, 1);//Create grid layout object
	private GridLayout chatRoomGrid=new GridLayout(4, 1);
	
	public CommunistProfileView() throws IOException{
		super("Send a message to your comrade!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 660);
		
		setLayout(new BorderLayout());//set layout of the JFrame to BorderLayout()
		
		
		//initialize JLabels
		firstNameLabel=new JLabel();
		firstNameLabel.setFont(new Font("Century",14,14));
				
		lastNameLabel=new JLabel();
		lastNameLabel.setFont(new Font("Century",14,14));

		userNameLabel=new JLabel();
		userNameLabel.setFont(new Font("Century",14,14));

		ageLabel = new JLabel();
		ageLabel.setFont(new Font("Century",14,14));
		
		
		//create text area
		chatBox = new JTextArea(8,40);
		scrollPane = new JScrollPane(chatBox);
		scrollPane.setBackground(new Color(255,255,240));
		chatBox.setFont(new Font("Century",14,14));
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Century", 14, 14));
	    chatBox.setLineWrap(true);
	    
	    //create text field
		messageBox = new JTextField(30);
		messageBox.requestFocusInWindow();
		messageBox.setFont(new Font("Century",14,14));

		//create buttons
		sendMessage = new JButton("Send Message");
		sendMessage.setFont(new Font("Century",14,14));
        
        backButton = new JButton("Back");
		backButton.setFont(new Font("Century",14,14));
		
		//create panels
		profileInfo = new JPanel();
		profileInfo.setBackground(new Color(255,245,238));
		profileInfo.setLayout(grid);
	
		chatRoom = new JPanel();
		chatRoom.setBackground(new Color(255,255,240));
		chatRoom.setLayout(chatRoomGrid);
		chatRoom.add(scrollPane);
		chatRoom.add(messageBox);
		chatRoom.add(sendMessage);
		chatRoom.add(backButton);
				
	}
	
	//add action listeners
	public void addMessageSendListener(ActionListener listenForSend){
		sendMessage.addActionListener(listenForSend);
	}
	
	public void addBackButtonListener(ActionListener listenForBack){
		backButton.addActionListener(listenForBack);
	}
	
	//this method called by controller with information taken from model
	public void updateLabels(){
	
		profileInfo.add(userNameLabel);
		profileInfo.add(ageLabel);
		
		//add panels to JFrame
		add(profileInfo, BorderLayout.NORTH);
		add(chatRoom,BorderLayout.SOUTH);
	}
	
	//getters and setters
	public void setFirstNameLabel(String firstName){
		firstNameLabel.setText("First Name : " + firstName);
		firstNameLabel.setFont(new Font("Century",14,14));
	}
		
	public void setLastNameLabel(String lastName){
		firstNameLabel.setText("Last name : " + lastName);
	}
		
	public void setAgeLabel(int age){
		ageLabel.setText("Age : "+Integer.toString(age));
	}
		
	public void setUserNameLabel(String userName){
		userNameLabel.setText("User name : "+ userName);
		userNameLabel.setFont(new Font("Century",14,14));
	}
		
	public String getUserName(){
		return userNameLabel.getText();
	}
	
	public JTextField getMessageBox(){
		return messageBox;
	}
	
	public JTextArea getChatBox(){
		return chatBox;
	}
}
