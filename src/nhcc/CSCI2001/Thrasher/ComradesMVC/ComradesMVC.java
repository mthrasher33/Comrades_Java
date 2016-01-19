package nhcc.CSCI2001.Thrasher.ComradesMVC;

import java.io.IOException;

import nhcc.CSCI2001.Thrasher.controllers.UserController;
import nhcc.CSCI2001.Thrasher.models.UserModel;
import nhcc.CSCI2001.Thrasher.views.CommunistProfileView;
import nhcc.CSCI2001.Thrasher.views.MatchesView;
import nhcc.CSCI2001.Thrasher.views.UserView;

public class ComradesMVC {
	
	public ComradesMVC() throws IOException{
		
		UserModel useModel = new UserModel();
		
		UserView useView = new UserView();
		MatchesView matView = new MatchesView();
		CommunistProfileView comView = new CommunistProfileView();
		
		new UserController(useView, matView, comView, useModel);
		
		useView.setVisible(true);
	    useView.setSize(220, 1000);
	    
	}

}
