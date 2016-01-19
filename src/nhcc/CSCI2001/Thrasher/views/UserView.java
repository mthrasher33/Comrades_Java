package nhcc.CSCI2001.Thrasher.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nhcc.CSCI2001.Thrasher.profiles.Hobby;
import nhcc.CSCI2001.Thrasher.profiles.HobbyDirectory;
import nhcc.CSCI2001.Thrasher.utilities.Utilities;


	@SuppressWarnings("serial")
	public class UserView extends JFrame{
		

		private JTextField firstNameField, lastNameField, userNameField, ageField;
		private JButton submit;
		private JLabel firstNameLabel, lastNameLabel, userNameLabel, ageLabel;
		private JPanel inputFields, hobbieBoxes, bottomPanel;
		private JCheckBox checkBox;
		private GridLayout inputFieldGrid=new GridLayout(5, 2);//Create grid layout object

		private ArrayList<JCheckBox> allCheckBoxes = new ArrayList<JCheckBox>();  //used to see which checkboxes are checked
		
		public UserView() throws IOException{
			super("Welcome to Comrades!");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
			setLayout(new BorderLayout());

			//JTextFields
			firstNameField=new JTextField(12);
			firstNameField.setFont(new Font("Century", 14, 14));	

			lastNameField=new JTextField(12);
			lastNameField.setFont(new Font("Century", 14, 14));	

			userNameField = new JTextField(12);
			userNameField.setFont(new Font("Century", 14, 14));	

			ageField = new JTextField(6);
			ageField.setFont(new Font("Century", 14, 14));	

			
			//set up labels and fields for input
			firstNameLabel=new JLabel("First Name: ");	
			firstNameLabel.setFont(new Font("Century", 14, 14));	

			lastNameLabel=new JLabel("Last Name: ");
			lastNameLabel.setFont(new Font("Century", 14, 14));	

			userNameLabel=new JLabel("Screen Name: ");
			userNameLabel.setFont(new Font("Century", 14, 14));	

			ageLabel = new JLabel("Age: ");	
			ageLabel.setFont(new Font("Century", 14, 14));	

			//submit button
			submit=new JButton("Submit");
			submit.setFont(new Font("Century", 12, 12));
			submit.setBackground(new Color(20,20,60));
			submit.setForeground(new Color(0,0,0));
			
			//create panel and add labels and fields to panel
			inputFields=new JPanel();
			inputFields.setLayout(inputFieldGrid);
			inputFields.setBackground(new Color(255,245,238));
			inputFields.add(firstNameLabel);
			inputFields.add(firstNameField);
			inputFields.add(lastNameLabel);
			inputFields.add(lastNameField);
			inputFields.add(userNameLabel);
			inputFields.add(userNameField);
			inputFields.add(ageLabel);
			inputFields.add(ageField);
			
			//create panel and add hobbies to panel
			hobbieBoxes = new JPanel();
			hobbieBoxes.setBackground(new Color(255,255,240));
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new GridLayout(1,1));
			bottomPanel.setBackground(new Color(255,255,240));

			
			hobbieBoxes.setLayout(new GridLayout(HobbyDirectory.values().length,2,15,1));
			
			//instantiate a check box and label for each hobby
			for(int hobby = 0; hobby<HobbyDirectory.values().length; hobby++){
				checkBox = new JCheckBox(HobbyDirectory.values()[hobby].getName());
				checkBox.setFont(new Font("Century", 12, 12));
				allCheckBoxes.add(checkBox); //add the check box to the array of check boxes
				hobbieBoxes.add(checkBox);
			}

			bottomPanel.add(submit);
			
			//add panels to content frame 
			add(inputFields, BorderLayout.NORTH); //this 
			add(hobbieBoxes, BorderLayout.CENTER);
			add(bottomPanel, BorderLayout.SOUTH);
			
		}//end constructor
		
		//add action listeners
		public void addSubmitListener(ActionListener listenForSubmitButton){
			submit.addActionListener(listenForSubmitButton);
		}

		//getters and setters
		public String getFirstName(){
			return firstNameField.getText();
		}
		
		public String getLastName(){
			return lastNameField.getText();
		}
		
		public String getUserName(){
			return userNameField.getText();
		}
		
		public int getAge(){
			return Integer.parseInt(ageField.getText());
			
		}
		//determines which check boxes are checked and adds them to an array of hobbies
		//the hobbies are then instantiated in the model.
		public ArrayList<Hobby> getHobbies(){
			ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
			for(int checkbox = 0; checkbox<getAllCheckBoxes().size(); checkbox++ ){
				if(getAllCheckBoxes().get(checkbox).isSelected()){
					hobbies.add(Utilities.getAllHobbies().get(checkbox)); 
				}
			}
			return hobbies;
		}
		
		public ArrayList<JCheckBox> getAllCheckBoxes(){
			return allCheckBoxes;
		}
		
	}




