package application;

import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class  Controller implements Initializable {
	static DatabaseManager databaseManager=new DatabaseManager();
	AssociationsController associationsController = new AssociationsController(); 
	ContructorsController contructorsController = new ContructorsController(); 
	HouseWinnerAssociationsController houseWinnerAssociationsController=new HouseWinnerAssociationsController();
	WinnerContructorsController winnerContructorsController=new WinnerContructorsController();
	@FXML Button dashboardRegistrationBtn, dashboardManageAssociationsBtn, dashboardManageContructorsBtn;
	@FXML Button dashboardGenerateWinnersBtn, dashboardGenerateWinnerContructorsBtn, dashboardListOfWinnersBtn, dashboardListOfWaitingBtn;
	@FXML Button dashboardBackBtn, homepageAssociationsBtn, homepageCondominiumsBtn;
	@FXML Label dashboardHeader, dashboardFooter, homepageHeader, homepageFooter, loginStatusLb;
	@FXML BorderPane loginPageBg;
	@FXML Button loginBtn;
	@FXML TextField usernameTf, passwordTf;
	
	static String initializerString="homepage";
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	  //     javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
	    //   javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
	     
	    if(initializerString.equalsIgnoreCase("login")){
	    	//Image buttonBg=new Image("images/loginbutton.jpg");
	    	//loginBtn.setGraphic(new ImageView(buttonBg));
	    	initializerString="";
	    }
	    	   
		if(initializerString.equalsIgnoreCase("homepage")){
			Image headerImage=new Image("images/header.jpg");
			homepageHeader.setGraphic(new ImageView(headerImage));
			
			Image footer=new Image("images/footer.jpg");
			homepageFooter.setGraphic(new ImageView(footer));

			Image homepageAssociationsImg=new Image("images/assocations.jpg");
			homepageAssociationsBtn.setGraphic(new ImageView(homepageAssociationsImg));

			Image homepageCondominiuminiumsImg=new Image("images/condominium.jpg");
			homepageCondominiumsBtn.setGraphic(new ImageView(homepageCondominiuminiumsImg));
			 
		}
		
		if(initializerString.equals("layout")){
		Image headerImage=new Image("images/headerFl.jpg");
		dashboardHeader.setGraphic(new ImageView(headerImage));
		
		Image footer=new Image("images/footerFl.jpg");
		dashboardFooter.setGraphic(new ImageView(footer));
		
		Image houseImage=new Image("images/regstration.jpg");
		 dashboardRegistrationBtn.setGraphic(new ImageView(houseImage));
		 
		  houseImage=new Image("images/manageassocation.jpg");
		 dashboardManageAssociationsBtn.setGraphic(new ImageView(houseImage));
		 
		  houseImage=new Image("images/managecontructor.png");
		  dashboardManageContructorsBtn.setGraphic(new ImageView(houseImage));
		 
		  houseImage=new Image("images/generatewinnerhouse.png");
		 dashboardGenerateWinnersBtn.setGraphic(new ImageView(houseImage));
		 
		 houseImage=new Image("images/assigncontructor.jpg");
		 dashboardGenerateWinnerContructorsBtn.setGraphic(new ImageView(houseImage));
		 
		///*
		 houseImage=new Image("images/winner.gif");
		 dashboardListOfWinnersBtn.setGraphic(new ImageView(houseImage));
		
		 houseImage=new Image("images/waiting.jpg");
		 dashboardListOfWaitingBtn.setGraphic(new ImageView(houseImage));
		
		 
		 Image backImage=new Image("images/app_back_button.jpg");
		 dashboardBackBtn.setGraphic(new ImageView(backImage));
		}
	}

	//REGISTRATION
	public void dashboardRegistrationBtnAction(){
		associationsController.registerAssociations();
	}
	
	//MANAGE ASSOCIATIONS
	public void dashboardManageAssociationsBtnAction(){
		if(!databaseManager.tableExists("Associations"))
		{
			 CommonMethodsClass.showNotificationMessages("ዝተመዝገቡ ዝመሓደሩ ማሕበራት የለውን");
			return;
		}
		
		associationsController.manageAssociations();

	}	
	
	//MANAGE CONTRUCTORS
	public void dashboardManageContructorsBtnAction(){
		if(!databaseManager.tableExists("Contructors"))
		{
			CommonMethodsClass.showNotificationMessages("ዝመሓደሩ ኮንትራክተራት የለውን");
			return;
		}
		contructorsController.manageContructors();

	}
	public void dashboardLoadHomepage(){
	Platform.runLater(new Runnable(){
			public void run(){

				initializerString="homepage";
				new CommonMethodsClass().loadHomepage("homepage.fxml");
		        Main.primaryStage.hide();

			}
		});
	}
	
	//GENERATE WINNERS
	public void dashboardGenerateWinnersBtnAction(){
		if(!databaseManager.tableExists("housewinnerssavepoint"))//CHECKS FOR HOUSE WINNERS SAVE POINT
			databaseManager.createHouseWinnersSavePointTable();
		HouseWinnerAssociationsController.savePointIndex=databaseManager.getHouseWinnersSavePoint();
		//CommonMethodsClass.showNotificationMessages("Resume Lottery starting from Winner "+HouseWinnerAssociationsController.savePointIndex);

		
		if(!databaseManager.tableExists("Associations"))
		{
			CommonMethodsClass.showNotificationMessages("በዚ ዙር ዝተመዝገቡ ማሕበራት የለውን");
			return;
		}
		
		houseWinnerAssociationsController.displayAssociationsHouseLotteryPage();

	}


	//GENERATE WINNERS
	public void dashboardGenerateWinnerContructorsBtnAction(){
		if(!databaseManager.tableExists("winnerContructorsSavepoint"))//CHECKS FOR HOUSE WINNERS SAVE POINT
			databaseManager.createWinnerContructorsSavePointTable();
		WinnerContructorsController.savePointIndex=databaseManager.getWinnerContructorsSavePoint();
		//CommonMethodsClass.showNotificationMessages("Resume Lottery starting from Winner "+HouseWinnerAssociationsController.savePointIndex);

		
		if(!databaseManager.tableExists("Contructors"))
		{
			CommonMethodsClass.showNotificationMessages("በዚ ዙር ዝተመዝገቡ ማሕበራት የለውን");
			return;
		}
		
		winnerContructorsController.displayContructorsLotteryPage();

	}
	public void dashboardListOfWinnersBtnAction(){
		new CommonMethodsClass().loadExistingWindowFxmlPage("WinnerAssociationsAndContructors.fxml");
	}
	
	public void dashboardListOfWaitingBtnAction(){
		HouseWinnerAssociationsController.initializerString="listWaitingAssociations";
		new CommonMethodsClass().loadExistingWindowFxmlPage("waitingAssociationsList.fxml");

	}
	
	public void dashboardBackBtnAction(){
		initializerString="homepage";
		CommonMethodsClass.newCreatedStage.close();
		dashboardLoadHomepage();
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void homepageAssociationsBtnAction(){
		initializerString="layout";
			new CommonMethodsClass().loadExistingWindowFxmlPage("layout.fxml");
			Main.homepage.hide();
	}

	public void homepageCondominiumsBtnAction(){
		initializerString="";
		new register_room().setVisible(true);;
		Main.homepage.hide();
	}
	
	
	 public void loginBtnAction(){
		 String username=usernameTf.getText();
		 String password=passwordTf.getText();
		 if(username.equalsIgnoreCase("")|| password.equalsIgnoreCase("")){
			 loginStatusLb.setText("ክፍቲ ቦታ ኣይተመልአን");
			 return;
		 }
		 if(!databaseManager.tableExists("Users")){
			 databaseManager.createUsersTable();
		 }
		 if(!databaseManager.getPassword(username).equals(password)){
			 loginStatusLb.setText("ዝተሰሓሓተ መለለይ ሽም ወይ መሕለፊ ቃል");
			 return;
		 }
			 
		initializerString="homepage";
		dashboardLoadHomepage();
	}
	
	public void manageAssociationsPrintBtnAction(){
		
	}

public void winnersPrintBtnAction(){

	new WinnerAssociationsAndContructors().winnersPrintBtnAction();
}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

