package application;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HouseWinnerAssociationsController implements Initializable {
	CommonMethodsClass commonMethodsClass = new CommonMethodsClass(); 
	static DatabaseManager databaseManager=new DatabaseManager();
	PrinterClass printerClass=null;
	SoundClass sound;
	String roundName=null;
	ArrayList<String> associationNames=new ArrayList<>();

	static String initializerString="";
	static 	LinkedHashMap<String, Integer> winnersNameAndBlockNumber=new LinkedHashMap<String, Integer>();
	static int lotteryId=1;//lottery id for housewinners is 1 in lottery rounds table
	
	@FXML Label winnersNameLb, winnersBlockNumberLb, lotteryNoticeLb;
	@FXML TextField numberOfBlocksTf, lotteryRoundTf;
	@FXML Button generateWinnersBtn, printHouseWinnerBtn, refreshHouseLotteryBtn;
	static int winnerIndex=0;
	long startTime=0, displayInterval=1000;
	int numberOfBlocks=0, roundId=0;
	boolean startNewPage=false;
	int numberOfRoundCandidateAssocitions=0;
	static int savePointIndex=0;
	@FXML Label generateWinnerAssociationsHeader, generateHouseWinnersFooter, listWaitingAssociationsHeader, listWaitingAssociationsFooter;
	@FXML Button generateWinnerAssociationsBackBtn, waitingAssociationsBackBtn;
	int wonBlockNumber, offset_size=0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	      // javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
	       //javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
	     
	     printerClass=new PrinterClass();
       	//sound=new SoundClass();
		if(!initializerString.equalsIgnoreCase("listWaitingAssociations")){
       	disableButton(printHouseWinnerBtn);
		emptyComponents();
		
	/*	Image backImage=new Image("images/app_back_button.jpg");
		generateWinnerAssociationsBackBtn.setGraphic(new ImageView(backImage));
		Image header=new Image("images/headerFL.jpg");
		generateWinnerAssociationsHeader.setGraphic(new ImageView(header));
		Image footer=new Image("images/footerFL.jpg");
		generateHouseWinnersFooter.setGraphic(new ImageView(footer));
		*/
		}
		
		if(initializerString.equalsIgnoreCase("generateHouseWinners"))
		{
			javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
		    javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
		    javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
		     
		    javax.swing.UIManager.put("PrinterClass.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
		       javax.swing.UIManager.put("PrinterClass.messageFont", new Font("Nyala", Font.PLAIN, 14));

			winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			
			
			Image backImage=new Image("images/app_back_button.jpg");
			generateWinnerAssociationsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			generateWinnerAssociationsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			generateHouseWinnersFooter.setGraphic(new ImageView(footer));
			//Image reportImg=new Image("images/report.jpg");
			//printHouseWinnerBtn.setGraphic(new ImageView(reportImg));
			
			if(savePointIndex>0){
				//int blockNum=databaseManager.getLotteryRoundBlockNumber(databaseManager.getHouseWinnersSavePointRoundId(), lotteryId);
				int blockNum=databaseManager.getAllHouseWinnerAssociationsData(databaseManager.getHouseWinnersSavePointRoundId()).size();
				int saveP=databaseManager.getHouseWinnersSavePoint();
				numberOfBlocksTf.setText(blockNum+"");
				lotteryRoundTf.setText(databaseManager.getRoundName(databaseManager.getHouseWinnersSavePointRoundId()));
				lotteryNoticeLb.setText("ዝተቋረፀ ዕፃ ይቕፅሉ: ካብ "+blockNum+" እቶም  "+(blockNum-saveP)+"  ዕድለኛታት ገና ኣይተፈለጡን");
				disableTextField(numberOfBlocksTf);
				disableTextField(lotteryRoundTf);
			}
				
		}
	if(initializerString.equalsIgnoreCase("listWaitingAssociations")){
		waitingAssociationsTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 

		/*Image backImage=new Image("images/app_back_button.jpg");
		waitingAssociationsBackBtn.setGraphic(new ImageView(backImage));
		Image header=new Image("images/headerFL.jpg");
		listWaitingAssociationsHeader.setGraphic(new ImageView(header));
		Image footer=new Image("images/footerFL.jpg");
		listWaitingAssociationsFooter.setGraphic(new ImageView(footer));
*/

		Image backImage=new Image("images/app_back_button.jpg");
		waitingAssociationsBackBtn.setGraphic(new ImageView(backImage));
		Image header=new Image("images/headerFl.jpg");
		listWaitingAssociationsHeader.setGraphic(new ImageView(header));
		Image footer=new Image("images/footerFl.jpg");
		listWaitingAssociationsFooter.setGraphic(new ImageView(footer));
		
		listAllWaitingAssociations();
		initializerString="";
	}
	}
	
	public void displayAssociationsHouseLotteryPage(){
		initializerString="generateHouseWinners";
		commonMethodsClass.loadExistingWindowFxmlPage("generateHouseWinners.fxml");
	}
	
	TableView<WinnerAssociation> winnersTable;
	TableColumn<WinnerAssociation, Integer> rowCol;
	TableColumn<WinnerAssociation, String> winnerUnionNameCol;
	TableColumn<WinnerAssociation, String> wonBlockNumberCol;
	TableColumn<WinnerAssociation, String> nameOfChairmanCol;
	TableColumn<WinnerAssociation, String> phoneNumberCol;


	public void generateWinnersBtnAction(){
		String numberOfBlocksTfString=numberOfBlocksTf.getText();
		roundName=lotteryRoundTf.getText();
		
		if(numberOfBlocksTfString.equalsIgnoreCase("")){
			CommonMethodsClass.showNotificationMessages("በዝሒ ብሎክ ኣይኣተወን");
			return;
		}
		
		if(!numberOfBlocksTfString.matches("^(-?)\\d+$")){
			CommonMethodsClass.showNotificationMessages("በዝሒ ብሎክ ትክክል ኣይኮነን");
			return;
		}
		
		if(!databaseManager.tableExists("LotteryRounds"))
			databaseManager.createLotteryRoundsTable();
		
		numberOfBlocks=Integer.parseInt(numberOfBlocksTfString);
		roundId=0;
		if(roundName.equalsIgnoreCase("")){
			CommonMethodsClass.showNotificationMessages("ናይ ምዝገባ ዙር ኣአትው");
			return;
		}
		else{
			 roundId=databaseManager.getRoundId(roundName);
			 	if(!(roundId>=1)){
			 		CommonMethodsClass.showNotificationMessages("እዚ ዙር ኣይተመዝገበን \n በይዘኦም ትክክለኛ ዙር ምዝገባ የእትው");
					return;	
			}
		}
		numberOfRoundCandidateAssocitions=databaseManager.getAllAssociationNames(roundId).size();
	/*
		if(numberOfBlocks>numberOfRoundCandidateAssocitions)
		{
			CommonMethodsClass.showNotificationMessages("በዝሒ ብሎካት  ካብ ን "+roundName.toUpperCase()+" በዝሒ ዝተመዝገቡ ማሕበራት በሊፁ\n\n በዝሒ ን   "+roundName.toUpperCase()+" "+" ዝተመዝገቡ ማሕበራት "+numberOfRoundCandidateAssocitions+" እዩ");
			return;
		}
		*/
		disableButton(generateWinnerAssociationsBackBtn);

		if(!databaseManager.tableExists("HouseWinnerAssociations"))
			databaseManager.createHouseWinnerAssociations();
		
		initializerString="generateWinners";
	
		databaseManager.insertLotteryRounds(roundId, lotteryId, numberOfRoundCandidateAssocitions);
		associationNames=databaseManager.getAllAssociationNames(roundId);		
if(numberOfBlocks>numberOfRoundCandidateAssocitions)
	numberOfBlocks=numberOfRoundCandidateAssocitions;

winnersNameAndBlockNumber=generateWinners(associationNames, numberOfBlocks);//holds new generated winners union name assocated with won block numbers 
	if(!databaseManager.tableExists("housewinnerssavepoint"))
		databaseManager.createHouseWinnersSavePointTable();
	if(savePointIndex>0){
		int roundId=databaseManager.getHouseWinnersSavePointRoundId();
winnersNameAndBlockNumber=databaseManager.getAllHouseWinnerAssociationsData(roundId);//reads winners that was generated before if savePoint
}
	if(!databaseManager.isUnique("roundId", roundId, "HouseWinnerAssociations")){
		databaseManager.deleteRow("HouseWinnerAssociations", "roundId", roundId);
	databaseManager.deleteRow("WinnerContructors", "roundId", roundId);
	}
	
	//sound.play();
	disableButton(generateWinnersBtn);
	disableTextField(numberOfBlocksTf);
	disableTextField(lotteryRoundTf);


//sets Winners TABLE_VIEW
	winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
	winnersTable.setItems(getWinners(roundId));//getWinners method populates table data
	winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 

	winnersTable.getColumns().addAll(rowCol, winnerUnionNameCol, wonBlockNumberCol, nameOfChairmanCol, phoneNumberCol);
	
	winnersTable.setFixedCellSize(25);
	winnersTable.prefHeightProperty().bind(winnersTable.fixedCellSizeProperty().multiply(Bindings.size(winnersTable.getItems()).add(5.05)));
	winnersTable.minHeightProperty().bind(winnersTable.prefHeightProperty());
	winnersTable.maxHeightProperty().bind(winnersTable.prefHeightProperty());
////////////////////
	
//Displays Winners using Timer
	new Thread(new Runnable(){
		public void run(){	
			int savePointInitial=savePointIndex;
			//Saves winners to database
			for(Entry<String, Integer> winner: winnersNameAndBlockNumber.entrySet()){
				String winnerAssociationName=winner.getKey();
				int wonBlockNumber= winner.getValue();
			 roundId=databaseManager.getRoundId(roundName);
			wonBlockNumber+=offset_size;
                        databaseManager.insertHouseWinnerAssociation(databaseManager.getAssociationId(winnerAssociationName, roundId), Import_Export.blocksList.get(wonBlockNumber-1), roundId);
			}
		///////////////////////////////////////////////	

		//Displays Winners in to Public using Timer
			winnerIndex-=1;
			for(Entry<String, Integer> winner: winnersNameAndBlockNumber.entrySet()){
				winnerIndex++;
			
                        sound=new SoundClass();
                        
				Platform.runLater(new Runnable(){
					public void run(){
						if(winnerIndex<savePointIndex){
							return;
						}
						String winnerAssociationName=winner.getKey();
						 wonBlockNumber= winner.getValue();
						 wonBlockNumber+=offset_size;
                                                 
                                                 winnerAssociationName= (String) winnerAssociationName.subSequence((9+String.valueOf(roundId).length()), winnerAssociationName.length());
						 savePointIndex=winnerIndex;
						winnersNameLb.setText(winnerAssociationName);
						//winnersBlockNumberLb.setText("Block Number  "+String.valueOf(wonBlockNumber));
						
                                                winnersBlockNumberLb.setText(Import_Export.blocksList.get(wonBlockNumber-1));
						lotteryNoticeLb.setText("ዕድለኛ ቁፅሪ -"+winnerIndex);
						databaseManager.insertHouseWinnersSavePoint(roundId, savePointIndex);		
					}
				});

						try {
							System.out.println("sp1 "+savePointInitial+"   wi "+winnerIndex);

							if(winnerIndex>savePointInitial){
							Thread.sleep(displayInterval);//Timer sleep
							System.out.println("sp2 "+winnerIndex+"   wi "+winnerIndex);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		/*
					startTime=System.currentTimeMillis();
				while(System.currentTimeMillis()-startTime<displayInterval){
					System.out.println("looping");
				}
			
		//*/
				System.out.println("un: "+winner.getKey()+"   bn  "+winner.getValue());
			}

Platform.runLater(new Runnable(){
				public void run(){
					savePointIndex=0;
					databaseManager.insertHouseWinnersSavePoint(roundId, savePointIndex);//resets savepoint to 0			 
					enableButton(generateWinnerAssociationsBackBtn);
					 winnersNameLb.setText("");
					 winnersBlockNumberLb.setText("");
					lotteryNoticeLb.setText("ዕፃ ተፈፂሙ");
					enableButton(printHouseWinnerBtn);					
					showWinnersTable();
				}
			});
		}
	}).start();
	
	
}
	
	public void printHouseWinnersBtnAction(){
		 javax.swing.UIManager.put("PrinterClass.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	       javax.swing.UIManager.put("PrinterClass.messageFont", new Font("Nyala", Font.PLAIN, 14));
			
			new CurrentPrinter().print(winnersTable, roundName);
		//printerClass.printSetup(winnersTable, Main.primaryStage);//prints table
		enableButton(refreshHouseLotteryBtn);
	}
	
	public void refreshHouseLotteryBtnAction(){//restarts new lottery
		emptyComponents();
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public LinkedHashMap<String, Integer> generateWinners(ArrayList<String> unionNames, int numberOfBlocks){
LinkedHashMap<String, Integer> winnersMap=new LinkedHashMap<String, Integer>();
ArrayList<Integer> unionIndexesList=new ArrayList<>();
ArrayList<Integer> blockIndexesList=new ArrayList<>();

int randomUnionIndex, randomBlockIndex, numberOfUnions=unionNames.size();
Random rand=new Random();

//GENERATES RANDOM UNIONS
for(int i=1; i<=numberOfBlocks; i++){
randomUnionIndex=rand.nextInt(numberOfUnions)+1;
while(isRepeated(randomUnionIndex, unionIndexesList)){
randomUnionIndex=rand.nextInt(numberOfUnions)+1;
//System.out.println("\trepetead "+random);
}
unionIndexesList.add(randomUnionIndex);

//GENERATES RANOM BLOCKS
randomBlockIndex=rand.nextInt(numberOfBlocks)+1;
while(isRepeated(randomBlockIndex, blockIndexesList)){
randomBlockIndex=rand.nextInt(numberOfBlocks)+1;
//System.out.println("\trepetead "+random);
}
blockIndexesList.add(randomBlockIndex);
//SET WINNERS LinkedHashMap
winnersMap.put(unionNames.get(unionIndexesList.get(i-1)-1), blockIndexesList.get(i-1));
//System.out.println(unionNames.get(unionIndexesList.get(i-1)-1)+"  bl "+blockIndexesList.get(i-1));
}

/*	
for(Entry<String, Integer> winner: winnersMap.entrySet()){
System.out.println("yes  "+winner.getKey()+"   "+winner.getValue());
}
*/

return winnersMap;
}


public ObservableList<WinnerAssociation> getWinners(int roundId){
	ObservableList<WinnerAssociation> winners=FXCollections.observableArrayList();
Integer rowNo=1;
	for(Entry<String, Integer> winner: winnersNameAndBlockNumber.entrySet()){
		String winnerName=(String) winner.getKey().subSequence((9+String.valueOf(roundId).length()), winner.getKey().length());
		int blockNumber=winner.getValue()+offset_size;
		winners.add(new WinnerAssociation(rowNo, winnerName, Import_Export.blocksList.get(blockNumber-1), roundId));
		rowNo++;
	}
	
	return winners;
}

public void showWinnersTable(){
	VBox tableLayout=new VBox();
	tableLayout.getChildren().addAll(printHouseWinnerBtn, new ScrollPane(winnersTable));
	Scene tableScene=new Scene(tableLayout, 650, 600);
	Stage tableStage=new Stage();
	tableStage.setScene(tableScene);
        tableStage.initModality(Modality.APPLICATION_MODAL);
        tableStage.show();
}

public boolean isRepeated(int newNum, ArrayList<Integer> numbersList){
	for(int n: numbersList){
		if(newNum==n)
			return true;
	}
	return false;
}

public boolean notUnique(String newString, ArrayList<String> unionNames){
	for(String unionName: unionNames){
		System.out.println("uniqueness err  un "+unionName+"  ns "+newString);
		if(newString.equalsIgnoreCase(unionName))
			return true;
	}
	return false;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
public void enableTextField(TextField textField){
		textField.setEditable(true);
		textField.setStyle("-fx-background-color: #ffffff; -fx-border-color:#34ffff");
	}
	
public void disableTextField(TextField textField){
		//textField.setText("");
		textField.setStyle("-fx-background-color: #eeeeee; -fx-border:#000000; -fx-border-width:3");
		textField.setEditable(false);
		
	}
public void enableButton(Button button){
		button.setDisable(false);
		//button.setStyle("-fx-background-color: #ffffff; -fx-border-color:#34ffff");
	}
	
public void disableButton(Button button){
		button.setDisable(true);
	//button.setStyle("-fx-background-color: #ffffff; -fx-border-color:#34ffff");
		
	}

public void generateWinnerAssociationsBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}
	///////////////////////////////////////////////////////////////////////////////////////////////

public void emptyComponents(){
	winnerIndex=1;
	winnersNameLb.setText("");
	winnersBlockNumberLb.setText("");
	numberOfBlocks=0;
	numberOfRoundCandidateAssocitions=0;
	startNewPage=true;
	associationNames=null;
	
	winnersNameAndBlockNumber=new LinkedHashMap<String, Integer>();
	databaseManager=new DatabaseManager();
	printerClass=new PrinterClass();
	//sound=new SoundClass();
	
	associationNames=new ArrayList<String>();
	
	winnersTable=new TableView<>();
	
	
	
	rowCol=new TableColumn<>("ተራ ቑ");
	rowCol.setMinWidth(30);
	rowCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

	winnerUnionNameCol=new TableColumn<>("ሽም ማሕበር");
	winnerUnionNameCol.setMinWidth(250);
	winnerUnionNameCol.setCellValueFactory(new PropertyValueFactory<>("unionName"));
	
	wonBlockNumberCol=new TableColumn<>("ብሎክ ቁፅሪ");
	wonBlockNumberCol.setMinWidth(120);
	wonBlockNumberCol.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));

	nameOfChairmanCol=new TableColumn<>("ሽም ኣቦ ወንበር");
	nameOfChairmanCol.setMinWidth(120);
	nameOfChairmanCol.setCellValueFactory(new PropertyValueFactory<>("nameOfChairman"));

	phoneNumberCol=new TableColumn<>("ስልኪ ቁፅሪ");
	phoneNumberCol.setMinWidth(120);
	phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

	lotteryNoticeLb.setText("");
	winnersNameLb.setAlignment(Pos.CENTER);
	winnersBlockNumberLb.setAlignment(Pos.CENTER);

	enableButton(generateWinnersBtn);
	disableButton(printHouseWinnerBtn);
	disableButton(refreshHouseLotteryBtn);
	enableTextField(numberOfBlocksTf);
	enableTextField(lotteryRoundTf);
	numberOfBlocksTf.setText("");
	lotteryRoundTf.setText("");
	
	numberOfBlocksTf.setStyle("-fx-border-color: black");
	lotteryRoundTf.setStyle("-fx-border-color: black");
}







//getAssociations method to fill the table
	@FXML TextField searchWinnersByRoundTf;
	boolean started=false;
	public void searchWinnersByRoundTfAction(){
		started=true;
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
		this.listAllWaitingAssociations();
	}
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//LISTS WAITING ASSOCIATIONS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@FXML TableView<WinnerAssociation> waitingAssociationsTable=new TableView<>();

@FXML TableColumn<WinnerAssociation, Integer> waitingRowNoCol;
@FXML TableColumn<WinnerAssociation, String> waitingAssociationsNameCol;
@FXML TableColumn<WinnerAssociation, String> waitingAssociationRegisterationRoundCol;


public void listAllWaitingAssociations(){

waitingRowNoCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

waitingAssociationsNameCol.setCellValueFactory(new PropertyValueFactory<>("unionName"));

waitingAssociationRegisterationRoundCol.setCellValueFactory(new PropertyValueFactory<>("registrationRound"));

//sets Associations TABLE_VIEW
waitingAssociationsTable.setItems(getAllWaitingAssociations());//getContructors method populates table data

waitingAssociationsTable.setFixedCellSize(25);
}


public ObservableList<WinnerAssociation> getAllWaitingAssociations(){
	ObservableList<WinnerAssociation> notWinners=FXCollections.observableArrayList();
Integer rowNo=1;
	for(int id: databaseManager.getAllAssociationIds()){
		if(!isWon(id)){
			String assName=databaseManager.getAssociationName(id);
			String regRound=databaseManager.getAssociationRegistrationRound(id);
			if(started && !regRound.equalsIgnoreCase(roundName))
				continue;
		 assName=(String) assName.subSequence((9+String.valueOf(databaseManager.getRoundId(regRound)).length()), assName.length());
		 notWinners.add(new WinnerAssociation(rowNo, assName, regRound));
		rowNo++;
		}
	}
	
	return notWinners;
}

public boolean isWon(int associationId){
	for(int id: databaseManager.getAllHouseWinerAssociationIds())
		if(id==associationId)
			return true;
	return false;
}

public void waitingAssociationsBackBtnAction(){
	Controller.initializerString="layout";
	new CommonMethodsClass().loadExistingWindowFxmlPage("layout.fxml");
}

public void waitingAssociationsPrintBtnAction(){
		if(!started)
		new CurrentPrinter().print(this.waitingAssociationsTable, "", "ዝርዝር ዘይበፅሖም  ማሕበራት");
		else
		new CurrentPrinter().print(this.waitingAssociationsTable, roundName, "ዝርዝር ዘይበፅሖም  ማሕበራት ");

		this.roundName=null;
		new Controller().dashboardListOfWaitingBtnAction();
	}

private void dashboardListWaitingAssociations() {
	// TODO Auto-generated method stub
	
}

}
