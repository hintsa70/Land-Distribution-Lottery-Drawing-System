package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinnerAssociationsAndContructors implements Initializable{
	static DatabaseManager databaseManager=new DatabaseManager();
	CommonMethodsClass commonMethodsClass = new CommonMethodsClass(); 

	@FXML Label listWinnersHeader, listWinnersFooter;
	@FXML Button listWinnersBackBtn, manageAssociationsPrintBtn;
	
	 int roundId=0;
	 String roundName=null;	

	//CONTRUCTURS TABLE

	@FXML TableView<AssociationBlockContructor> winnerAssociationsAndContructorsTable=new TableView<>();
	@FXML TableColumn<AssociationBlockContructor, Integer> winnersRowNoCol;
	@FXML TableColumn<AssociationBlockContructor, String> winnerAssociationNameCol;
	@FXML TableColumn<AssociationBlockContructor, Integer> wonBlockNumberCol;
	@FXML TableColumn<AssociationBlockContructor, String> winnerContructorNameCol;
	@FXML TableColumn<AssociationBlockContructor, String> winnerContructorLevelCol;	
	@FXML TableColumn<AssociationBlockContructor, String> roundNameCol;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		winnerAssociationsAndContructorsTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
		//otherRegistrationTypesCb.setValue("ካልኦት ዓይነታት ምዝገባ...");

		Image backImage=new Image("images/app_back_button.jpg");
		listWinnersBackBtn.setGraphic(new ImageView(backImage));
		
		Image header=new Image("images/headerFl.jpg");
		listWinnersHeader.setGraphic(new ImageView(header));
		Image footer=new Image("images/footerFl.jpg");
		listWinnersFooter.setGraphic(new ImageView(footer));
		
		listAllWinners();
	}


	public void listAllWinners(){

		winnersRowNoCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

		winnerAssociationNameCol.setCellValueFactory(new PropertyValueFactory<>("associationName"));

		wonBlockNumberCol.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));

		winnerContructorNameCol.setCellValueFactory(new PropertyValueFactory<>("contructorName"));
		winnerContructorLevelCol.setCellValueFactory(new PropertyValueFactory<>("winnerContructorLevel"));
		roundNameCol.setCellValueFactory(new PropertyValueFactory<>("roundName"));

	//sets Associations TABLE_VIEW
		winnerAssociationsAndContructorsTable.setItems(getBothWinners());//getContructors method populates table data
		
		winnerAssociationsAndContructorsTable.setFixedCellSize(25);
	


	}
	
	
	//getAssociations method to fill the table
	@FXML TextField searchWinnersByRoundTf;
	public void searchWinnersByRoundTfAction(){
		 roundName=searchWinnersByRoundTf.getText();
		 if(roundName.isEmpty())
			 return;
		if(databaseManager.getRoundId(roundName)>=1){
			roundId=databaseManager.getRoundId(roundName);
		//	commonMethodsClass.showNotificationMessages(roundId+" "+roundName);

		}
		else
		{
			commonMethodsClass.showNotificationMessages(searchWinnersByRoundTf.getText()+" ኣይተመዝገበን");
			roundId=0;
			roundName=null;
			return;
		}
		//new Controller().dashboardListOfWinnersBtnAction();//reloads table
		this.listAllWinners();
	}
	String winnerContructorLevel;
	public ObservableList<AssociationBlockContructor> getBothWinners(){
	///*
		winnerContructorLevel=null;
				
	 	if(!(roundId>0)){
				roundId=databaseManager.getIdSequence("rounds", 1).get(0);
				roundName=databaseManager.getRoundName(roundId);
			}
			//*/
		ObservableList<AssociationBlockContructor> AllWinnerDetails=FXCollections.observableArrayList();
		Integer rowNo=1;
		LinkedHashMap<Integer, String> associationNameAndBlockNumbers=null;
		LinkedHashMap<Integer, Integer> blockNumberAndContructorNames=null;
		
		associationNameAndBlockNumbers=databaseManager.getAllWinnerAssociations(roundId);
		blockNumberAndContructorNames=databaseManager.getAllBlocksAndContructors(roundId);
		
			for(Entry<Integer, String> entry: associationNameAndBlockNumbers.entrySet()){
				String associationName=databaseManager.getAssociationName(entry.getKey());
				if(associationName==null)
					continue;//skips this iteration step
				String blockNumber=entry.getValue();
				 associationName=(String) associationName.subSequence((9+String.valueOf(roundId).length()), associationName.length());
				String contructorName=null;
				if(blockNumberAndContructorNames.size()>0){
				 contructorName=databaseManager.getContructorName(blockNumberAndContructorNames.get(blockNumber));
				 if(contructorName!=null){
					 winnerContructorLevel=databaseManager.getLevelName(databaseManager.getContructorRank(databaseManager.getContructorId(contructorName, roundId)));
					 contructorName=(String) contructorName.subSequence((9+String.valueOf(roundId).length()), contructorName.length());
				 }
				 }
			AllWinnerDetails.add(new AssociationBlockContructor(rowNo, associationName, blockNumber, contructorName, winnerContructorLevel, roundName));
			rowNo++;
			}
//			*/
			return AllWinnerDetails;
		}

public void listWinnersBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}

public void winnersPrintBtnAction(){

	new CurrentPrinter().print(winnerAssociationsAndContructorsTable, roundName);

	this.roundName=null;
	new Controller().dashboardListOfWinnersBtnAction();
}
}
