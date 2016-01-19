package nhcc.CSCI2001.Thrasher.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class MatchesView extends JFrame{
	

	private JPanel main;
	private JTable matches;
	private JScrollPane tableContainer;
	private String columnNames[] =  {"Screen Name", "Age", "Comrade Strength", "Hobby 1", "Hobby 2", "Hobby 3"};
	private String comrades[][] = new String[97][6]; 
	
	public MatchesView() throws IOException{
		super("Welcome to Comrades! Click on the name of any user whose profile you would like to view.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
	
		 main=new JPanel();
	
	}
	
	//add action listeners
	public void addMouseListener(MouseListener listenForClick){
		matches.addMouseListener(listenForClick);
	}

	
	//this method is invoked by the controller
	public void setMatches(){
		 matches = new JTable(getComrades(), columnNames); //controller populates table rows with info from model
		 matches.setFont(new Font("Century", 11, 11));
		 matches.getTableHeader().setFont(new Font("Century", 14, 14));
		 matches.getTableHeader().setBackground(new Color(255,245,238));
		 
		 matches.getColumnModel().getColumn(0).setPreferredWidth(200);
		 matches.getColumnModel().getColumn(1).setPreferredWidth(50);
		 matches.getColumnModel().getColumn(2).setPreferredWidth(200);
		 matches.getColumnModel().getColumn(3).setPreferredWidth(200);
		 matches.getColumnModel().getColumn(4).setPreferredWidth(200);
		 matches.getColumnModel().getColumn(5).setPreferredWidth(200);

	     tableContainer = new JScrollPane(matches);
	     main.add(tableContainer, BorderLayout.CENTER);
	     
	     //add panel to JFrame
	     add(tableContainer, BorderLayout.CENTER); 
		 repaint();
		 revalidate();
	}
	
	//getters and setters
	public void setComrades(String[][] strongComrades){
		this.comrades = strongComrades;
	}
	
	public String[][] getComrades(){
		return comrades;
	}
		
	public JTable getMatches(){
		return matches;

	}
	
}
