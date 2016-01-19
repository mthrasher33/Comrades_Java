package nhcc.CSCI2001.Thrasher.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import nhcc.CSCI2001.Thrasher.models.UserModel;
import nhcc.CSCI2001.Thrasher.utilities.Utilities;
import nhcc.CSCI2001.Thrasher.views.CommunistProfileView;
import nhcc.CSCI2001.Thrasher.views.MatchesView;
import nhcc.CSCI2001.Thrasher.views.UserView;

public class UserController {
	
	private UserView useView;
	private UserModel useModel;
	private MatchesView matView;
	private CommunistProfileView comView;
	
	
	public UserController(UserView useView, MatchesView matView, CommunistProfileView comView, UserModel useModel){
		this.useView = useView;
		this.useModel = useModel;
		this.comView = comView;
		this.matView = matView;
		
		this.useView.addSubmitListener(new SubmitListener());
		this.comView.addMessageSendListener(new MessageSendListener());
		this.comView.addBackButtonListener(new BackButtonListener());
	}
	
	//action to send a message in the chat room. whenever a chat is sent, a comrade responds.
	class MessageSendListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			 if (getCommunistProfileView().getMessageBox().getText().length() < 1) {
	                // do not send a message if the message box is blank
	            } 
	         else {
	                getCommunistProfileView().getChatBox().append("You say:  " + 
	                		getCommunistProfileView().getMessageBox().getText()
	                        + "\n");
	                getCommunistProfileView().getMessageBox().setText("");
	                
	                //timer delays chat responses in order to simulate a real chat experience
	                new java.util.Timer().schedule( 
	                        new java.util.TimerTask() {
	                            @Override
	                            public void run() {
	            	                try {
	            	                	String userName = getUserModel().getCommunistProfileInView().getUserName();
	            						getCommunistProfileView().getChatBox().append(userName + " says: "
	            								+ getUserModel().getCommunistProfileInView().chat() + "\n");
	            					} catch (IOException e) {
	            						e.printStackTrace();
	            					} 
	                            }
	                        }, 
	                        Utilities.randomChatTime()
	                );
	            }
	            getCommunistProfileView().getMessageBox().requestFocusInWindow();
		}
	}
	
	//action to leave chat room and return to main site
	class BackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			getCommunistProfileView().getChatBox().setText("");
			getCommunistProfileView().getMessageBox().setText("");
			getCommunistProfileView().dispose();
			getMatchesView().setVisible(true);
			getMatchesView().setLocation(getCommunistProfileView().getLocation());
		}
	}
	
	
	//action for submit button user registration splash page
	class SubmitListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
			//create new user
			getUserModel().createNewUser(getUserView().getFirstName(), getUserView().getLastName(), 
					getUserView().getUserName(), getUserView().getAge(), getUserView().getHobbies());
				
			//populate the JTable for the matches view
			getMatchesView().setComrades(getUserModel().populateJTable());
			getMatchesView().setMatches();
			getMatchesView().setLocation(getUserView().getLocation());
			getUserView().dispose();

			getMatchesView().setVisible(true);		
			//have to add the mouse listener after data has been generated so that it can attach to a filled table
			getMatchesView().addMouseListener(new MouseClickListener());

		}
	}
	
	//action for clicking on a comrade in the main site
	class MouseClickListener implements MouseListener{		
			public void mouseClicked (MouseEvent evt){	
					//determine which communist profile to access
					int row = matView.getMatches().rowAtPoint(evt.getPoint());				
					if(row<getUserModel().getUser().getStrongComrades().size()){
						getCommunistProfileView().setFirstNameLabel(useModel.getUser().getStrongComrades().get(row).getFirstName());
						getCommunistProfileView().setLastNameLabel(useModel.getUser().getStrongComrades().get(row).getLastName());
						getCommunistProfileView().setUserNameLabel(useModel.getUser().getStrongComrades().get(row).getUserName());
						getCommunistProfileView().setAgeLabel(useModel.getUser().getStrongComrades().get(row).getAge());
						//this line lets us know which communist is in view. this will be used for the chat feature
						getUserModel().setCommunistProfileInView(useModel.getUser().getStrongComrades().get(row));
					}
					if(row>= useModel.getUser().getStrongComrades().size()+useModel.getUser().getModerateComrades().size()){
						getCommunistProfileView().setFirstNameLabel(useModel.getUser().getWeakComrades().get(row-useModel.getUser().getStrongComrades().size()-useModel.getUser().getModerateComrades().size()).getFirstName());
						getCommunistProfileView().setLastNameLabel(useModel.getUser().getWeakComrades().get(row-useModel.getUser().getStrongComrades().size()-useModel.getUser().getModerateComrades().size()).getLastName());
						getCommunistProfileView().setUserNameLabel(useModel.getUser().getWeakComrades().get(row-useModel.getUser().getStrongComrades().size()-useModel.getUser().getModerateComrades().size()).getUserName());
						getCommunistProfileView().setAgeLabel(useModel.getUser().getWeakComrades().get(row-useModel.getUser().getStrongComrades().size()-useModel.getUser().getModerateComrades().size()).getAge());
						getUserModel().setCommunistProfileInView(useModel.getUser().getWeakComrades().get(row-useModel.getUser().getStrongComrades().size()-useModel.getUser().getModerateComrades().size()));

					}
					if(row>getUserModel().getUser().getStrongComrades().size() && row< getUserModel().getUser().getStrongComrades().size() + getUserModel().getUser().getModerateComrades().size()) {
						getCommunistProfileView().setFirstNameLabel(useModel.getUser().getModerateComrades().get(row-useModel.getUser().getStrongComrades().size()).getFirstName());
						getCommunistProfileView().setLastNameLabel(useModel.getUser().getModerateComrades().get(row-useModel.getUser().getStrongComrades().size()).getLastName());
						getCommunistProfileView().setUserNameLabel(useModel.getUser().getModerateComrades().get(row-useModel.getUser().getStrongComrades().size()).getUserName());
						getCommunistProfileView().setAgeLabel(useModel.getUser().getModerateComrades().get(row-useModel.getUser().getStrongComrades().size()).getAge());
						getUserModel().setCommunistProfileInView(useModel.getUser().getModerateComrades().get(row-useModel.getUser().getStrongComrades().size()));

					}
					
					matView.dispose();
					
					comView.updateLabels();
					comView.repaint();
					comView.revalidate();
					comView.setVisible(true);
					comView.setLocation(matView.getLocation());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}			
		}

		
		
	
	//getters and setters
	public MatchesView getMatchesView(){
		return matView;
	}
	
	public CommunistProfileView getCommunistProfileView(){
		return comView;
	}
	
	public UserView getUserView(){
		return useView;
	}
	
	public UserModel getUserModel(){
		return useModel;
	}
	
	
}
