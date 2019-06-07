	package application;

	import java.awt.Font;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.Random;
	import java.util.ResourceBundle;
	import java.util.Map.Entry;

	import javax.swing.JOptionPane;
import javax.swing.JTable;
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
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
	import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

	public class WinnerContructorsController implements Initializable {
		CommonMethodsClass commonMethodsClass = new CommonMethodsClass(); 
		static DatabaseManager databaseManager=new DatabaseManager();
		PrinterClass printerClass=null;
		SoundClass sound;
		String roundName=null;

		ArrayList<String> contructorNames=new ArrayList<>();
		ArrayList<Integer> roundCandidateContructorsIds=new ArrayList<>();
		LinkedHashMap<Integer, Integer> levelValues=new LinkedHashMap<>();
		static String initializerString="";
		static 	LinkedHashMap<Integer, String> winnersNameAndBlockNumber=new LinkedHashMap<>();
		static int lotteryId=2;//lottery id for winnerContructors is 2 in lottery rounds table
		
		@FXML ComboBox contructorLevelsCb;
		@FXML Label winnersNameLb, winnersBlockNumberLb, lotteryNoticeLb, enterLevelValuesLb, contructorsLotteryBlocksLb;
		@FXML TextField lotteryRoundTf, enterLevelValuesTf;
		@FXML Button generateWinnersBtn, printWinnerContructorsBtn, refreshContructorsLotteryBtn, generateWinnerContructorsBackBtn;
		static int winnerIndex=0;
		long startTime=0, displayInterval=5000;
		int numberOfBlocks=0, roundId=1;
		static boolean startContructorsLottery=false, startNewPage=false;
		int numberOfRoundCandidateContructors=0, totalContructorsCapacity=0;
		static int savePointIndex=0;
		@FXML Label generateWinnerContructorsHeader, generateWinnerContructorsFooter;
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			  //javax.swing.UIManager.put("PrinterClass.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
		       //javax.swing.UIManager.put("PrinterClass.messageFont", new Font("Nyala", Font.PLAIN, 14));


			
			printerClass=new PrinterClass();
			sound=new SoundClass();
			disableButton(printWinnerContructorsBtn);
			emptyComponents();
			
			
			if(initializerString.equalsIgnoreCase("generateWinnerContructors"))
			{
				javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
			    javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
			    javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
			     
			    javax.swing.UIManager.put("PrinterClass.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
			       javax.swing.UIManager.put("PrinterClass.messageFont", new Font("Nyala", Font.PLAIN, 14));

/*
					Image header=new Image("images/headerFL.jpg");
					generateWinnerContructorsHeader.setGraphic(new ImageView(header));
					
					Image footer=new Image("images/footerFL.jpg");
					generateWinnerContructorsFooter.setGraphic(new ImageView(footer));
					
					Image backImage=new Image("images/app_back_button.jpg");
					generateWinnerContructorsBackBtn.setGraphic(new ImageView(backImage));
					
				*/
					
					Image backImage=new Image("images/app_back_button.jpg");
					generateWinnerContructorsBackBtn.setGraphic(new ImageView(backImage));
					Image header=new Image("images/headerFl.jpg");
					generateWinnerContructorsHeader.setGraphic(new ImageView(header));
					Image footer=new Image("images/footerFl.jpg");
					generateWinnerContructorsFooter.setGraphic(new ImageView(footer));
					
					
					
				winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
				if(savePointIndex==0)
				{
							disableTextField(enterLevelValuesTf);
							disableButton(generateWinnersBtn);

				}
				if(savePointIndex>0){
				disableButton(generateWinnerContructorsBackBtn);
					int blockNum=databaseManager.getLotteryRoundBlockNumber(databaseManager.getWinnerContructorsSavePointRoundId(), lotteryId);
					int saveP=databaseManager.getWinnerContructorsSavePoint();
					lotteryRoundTf.setText(databaseManager.getRoundName(databaseManager.getWinnerContructorsSavePointRoundId()));
					lotteryNoticeLb.setText("ዝተቋረፀ ዕፃ ይቕፅሉ: ካብ "+blockNum+" እቶም  "+(blockNum-saveP)+"  ዕድለኛታት ገና ኣይተፈለጡን");
					disableTextField(lotteryRoundTf);
					disableTextField(enterLevelValuesTf);
				}
				else if(!startContructorsLottery){
					String firstLevel=databaseManager.getLevelSequenceIndex(1);
				lotteryRoundTf.setText("");
					lotteryNoticeLb.setText("");
					disableTextField(enterLevelValuesTf);

				}
					
			}
	}
		
		public void displayContructorsLotteryPage(){
			initializerString="generateWinnerContructors";
			commonMethodsClass.loadExistingWindowFxmlPage("generateWinnerContructors.fxml");
		}


		TableView<WinnerContructor> winnersTable;
		TableColumn<WinnerContructor, Integer> rowCol;
		TableColumn<WinnerContructor, String> winnerUnionNameCol;
		TableColumn<WinnerContructor, Integer> wonBlockNumberCol;
		TableColumn<WinnerContructor, String> levelCol;


		public void generateWinnersBtnAction(){
			disableButton(generateWinnerContructorsBackBtn);

			if(!databaseManager.tableExists("WinnerContructors"))
				databaseManager.createWinnerContructors();
			
			initializerString="generateWinners";
			
			for(String contructorName: databaseManager.getAllContructorNames(roundId)){
			roundCandidateContructorsIds.add(databaseManager.getContructorId(contructorName, roundId));
			}

			numberOfRoundCandidateContructors=databaseManager.getAllContructorNames(roundId).size();
			if(numberOfBlocks>numberOfRoundCandidateContructors)
			{
				//CommonMethodsClass.showNotificationMessages("Number of Blocks is exceeded than Registered  contructors in "+roundName.toUpperCase()+"\n\n Number of Registered contructors in  "+roundName.toUpperCase()+" "+"  is   "+numberOfRoundCandidateContructors);
				//return;
			}
			
			databaseManager.insertLotteryRounds(roundId, lotteryId, numberOfBlocks);
			contructorNames=databaseManager.getAllContructorNames(roundId);		
if(savePointIndex==0){
	winnersNameAndBlockNumber=generateWinners(contructorNames, numberOfBlocks);//holds new generated winners union name assocated with won block numbers 
		if(!databaseManager.tableExists("winnerssavepoint"))
			databaseManager.createWinnerContructorsSavePointTable();
	}
if(savePointIndex>0){
			 roundId=databaseManager.getWinnerContructorsSavePointRoundId();
	winnersNameAndBlockNumber=databaseManager.getAllWinnerContructorsData(roundId);//reads winners that was generated before if savePoint
	}
		
		if(savePointIndex==0 && !databaseManager.isUnique("roundId", roundId, "WinnerContructors"))
			databaseManager.deleteRow("WinnerContructors", "roundId", roundId);
		
		sound.play();
		disableButton(generateWinnersBtn);
		disableTextField(lotteryRoundTf);


	//sets Winners TABLE_VIEW
		winnersTable.setItems(getWinners());//getWinners method populates table data
		winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 

		winnersTable.getColumns().addAll(rowCol, winnerUnionNameCol, wonBlockNumberCol, levelCol);
		
		winnersTable.setFixedCellSize(25);
		winnersTable.prefHeightProperty().bind(winnersTable.fixedCellSizeProperty().multiply(Bindings.size(winnersTable.getItems()).add(5.05)));
		winnersTable.minHeightProperty().bind(winnersTable.prefHeightProperty());
		winnersTable.maxHeightProperty().bind(winnersTable.prefHeightProperty());
	////////////////////
		
	//Displays Winners using Timer
		new Thread(new Runnable(){
			public void run(){	
				int savePointInitial=savePointIndex;
		if(savePointIndex==0){	//Saves winners to database only if new lottery 
				for(Entry<Integer, String> winner: winnersNameAndBlockNumber.entrySet()){
					int wonBlockNumber= winner.getKey();
					String WinnerContructorName=winner.getValue();
				 roundId=databaseManager.getRoundId(roundName);
				databaseManager.insertWinnerContructor(databaseManager.getContructorId(WinnerContructorName, roundId), wonBlockNumber, roundId);
				}
			}
			///////////////////////////////////////////////	

			//Displays Winners in to Public using Timer
				winnerIndex-=1;
				for(Entry<Integer, String> winner: winnersNameAndBlockNumber.entrySet()){
					winnerIndex++;
				
					Platform.runLater(new Runnable(){
						public void run(){
							if(winnerIndex<savePointIndex){
								System.out.println("revealed ");
								return;
							}
							int wonBlockNumber= winner.getKey();
							String WinnerContructorName=winner.getValue();
							 WinnerContructorName= (String) WinnerContructorName.subSequence((9+String.valueOf(roundId).length()), WinnerContructorName.length());
							 savePointIndex=winnerIndex;
							winnersNameLb.setText(WinnerContructorName);
							winnersBlockNumberLb.setText(String.valueOf(wonBlockNumber));
							//winnersBlockNumberLb.setText("Block Number  "+String.valueOf(wonBlockNumber));
							lotteryNoticeLb.setText("ዕድለኛ ቁፅሪ -"+winnerIndex);
							databaseManager.insertWinnerContructorsSavePoint(roundId, savePointIndex);		
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
					System.out.println("un: "+winner.getKey()+"   bn  "+winner.getValue());
				}

	Platform.runLater(new Runnable(){
					public void run(){
						savePointIndex=0;
						enableButton(generateWinnerContructorsBackBtn);
						winnersNameLb.setText("");
						winnersBlockNumberLb.setText("");
						databaseManager.insertWinnerContructorsSavePoint(roundId, savePointIndex);//resets savepoint to 0			 
						
						lotteryNoticeLb.setText("ዕፃ ተፈፂሙ!");
						enableButton(printWinnerContructorsBtn);					
						showWinnersTable();
					}
				});
			}
		}).start();
		
		
	}
/*		
		public void printWinnersBtnAction(){
			winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			new PrintTest().print(winnersTable);//prints table
			enableButton(refreshContructorsLotteryBtn);
		}
		*/
		public void refreshLotteryBtnAction(){//restarts new lottery
			emptyComponents();
		}
		

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public LinkedHashMap<Integer, String> generateWinners(ArrayList<String> unionNames, int numberOfBlocks){

	LinkedHashMap<Integer, String> winnersMap=new LinkedHashMap<>();
	ArrayList<Integer> unionIndexesList=new ArrayList<>();
	ArrayList<Integer> blockIndexesList=new ArrayList<>();

	int randomUnionIndex, randomBlockIndex, numberOfUnions=unionNames.size();
	Random rand=new Random();
	ArrayList<Integer> validRoundCandidateContructorsIds=new ArrayList();
for(int conId: roundCandidateContructorsIds){
	int repeatition=levelValues.get(databaseManager.getLevelId(databaseManager.getLevelSequenceIndex(databaseManager.getContructorRank(conId))));
	if(repeatition>0){
		validRoundCandidateContructorsIds.add(conId);
	System.out.println(databaseManager.getContructorName(conId));
	}
}
	//GENERATES RANDOM UNIONS

int numberOfCons=validRoundCandidateContructorsIds.size();
for(int i=1; i<=numberOfBlocks; i++){
	randomUnionIndex=rand.nextInt(numberOfCons)+1;

	int contructorId=validRoundCandidateContructorsIds.get(randomUnionIndex-1);
	int repeatition=levelValues.get(databaseManager.getLevelId(databaseManager.getLevelSequenceIndex(databaseManager.getContructorRank(contructorId))));
	while(this.isRepeatedMoreThan(randomUnionIndex, unionIndexesList, repeatition)){
		
		randomUnionIndex=rand.nextInt(numberOfCons)+1;
			 contructorId=validRoundCandidateContructorsIds.get(randomUnionIndex-1);
			 repeatition=levelValues.get(databaseManager.getLevelId(databaseManager.getLevelSequenceIndex(databaseManager.getContructorRank(contructorId))));
				System.out.println("rand1 "+randomUnionIndex+"  con name "+databaseManager.getContructorName(contructorId)+"  pro "+repeatition);	

	}
	/*
	String message="";
	for(int n: unionIndexesList)
		message+="\n"+databaseManager.getContructorName(validRoundCandidateContructorsIds.get(n-1));
	CommonMethodsClass.showNotificationMessages(message+"      "+databaseManager.getContructorName(validRoundCandidateContructorsIds.get(randomUnionIndex-1)));
	*/
	unionIndexesList.add(randomUnionIndex);

	//GENERATES RANOM BLOCKS
	randomBlockIndex=rand.nextInt(numberOfBlocks)+1;
	while(isRepeated(randomBlockIndex, blockIndexesList)){
	randomBlockIndex=rand.nextInt(numberOfBlocks)+1;
	//System.out.println("\trepetead "+random);
	}
	blockIndexesList.add(randomBlockIndex);
	//SET WINNERS LinkedHashMap
	winnersMap.put(blockIndexesList.get(i-1), databaseManager.getContructorName(contructorId));
	//System.out.println(unionNames.get(unionIndexesList.get(i-1)-1)+"  bl "+blockIndexesList.get(i-1));

	}

	return winnersMap;
	}
/*
		public LinkedHashMap<Integer, Integer> generateWinners(ArrayList<String> unionNames, int numberOfBlocks){
	LinkedHashMap<String, Integer> winnersMap=new LinkedHashMap<String, Integer>();
	ArrayList<Integer> unionIndexesList=new ArrayList<>();
	ArrayList<Integer> blockIndexesList=new ArrayList<>();

	int randomUnionIndex, randomBlockIndex, numberOfUnions=unionNames.size();
	Random rand=new Random();

	//GENERATES RANDOM UNIONS
	for(int i=1; i<=numberOfBlocks; i++){
	randomUnionIndex=rand.nextInt(numberOfUnions)+1;
	for(Entry<Integer, Integer> entry: levelValues.entrySet())
		System.out.println(" data   "+entry.getKey()+"  "+entry.getValue());
	
	int contructorId=databaseManager.getContructorId(databaseManager.getContructorName(randomUnionIndex), roundId);
	System.out.println("contructor Id ="+contructorId+"  "+databaseManager.getContructorRank(contructorId)+"  "+databaseManager.getLevelSequenceIndex((databaseManager.getContructorRank(contructorId))));
	int repeatition=levelValues.get(databaseManager.getLevelId(databaseManager.getLevelSequenceIndex(databaseManager.getContructorRank(contructorId))));
	
	System.out.println(" details   "+databaseManager.getContructorName(contructorId)+"  rp "+repeatition+"   ci "+contructorId);

	while(this.isRepeatedMoreThan(randomUnionIndex, unionIndexesList, repeatition+1)){
			randomUnionIndex=rand.nextInt(numberOfUnions)+1;
			//System.out.println("\trepetead "+random);
			}
	
	System.out.println("wiiners index   "+randomUnionIndex);
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

	return winnersMap;
	}
*/

	public ObservableList<WinnerContructor> getWinners(){
		ObservableList<WinnerContructor> winners=FXCollections.observableArrayList();
	Integer rowNo=1;
		for(Entry<Integer, String> winner: winnersNameAndBlockNumber.entrySet()){
			String level=databaseManager.getLevelName(databaseManager.getContructorRank(databaseManager.getContructorId(winner.getValue(), roundId)));
			String winnerName=(String) winner.getValue().subSequence((9+String.valueOf(roundId).length()), winner.getValue().length());
			winners.add(new WinnerContructor(rowNo, winnerName, winner.getKey(), level));
			rowNo++;
		}
		
		return winners;
	}


	public void showWinnersTable(){
		winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 

		VBox tableLayout=new VBox();
		tableLayout.getChildren().addAll(winnersTable);
		Scene tableScene=new Scene(tableLayout, 650, 600);
		Stage tableStage=new Stage();
		tableStage.setScene(tableScene);
		tableStage.show();
	}

	public boolean isRepeated(int newNum, ArrayList<Integer> numbersList){
		for(int n: numbersList){
			if(newNum==n)     
				return true;   
		}             
		return false;
	}

	public boolean isRepeatedMoreThan(int newNum, ArrayList<Integer> winnersList, int repeatition){		
		int count=1;
		System.out.println("reptition = "+repeatition);
		for(int num: winnersList){
			if(newNum==num){
				System.out.println("count  "+newNum+"  "+count+"  times");
				count++;
			}
			if(count>repeatition){
				System.out.println("invalid repeat  "+count+"  times "+databaseManager.getContructorName(num));
				//CommonMethodsClass.showNotificationMessages(newNum+"  repeated "+count+"  times");
				return true;
			}
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
			textField.setStyle("-fx-background-color: #ffffff; -fx-border-color:  #000000;");
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
		///////////////////////////////////////////////////////////////////////////////////////////////

	public void emptyComponents(){
		winnerIndex=1;
		winnersNameLb.setText("");
		winnersBlockNumberLb.setText("");
		numberOfBlocks=0;
		startNewPage=true;
		contructorNames=null;
		
		winnersNameAndBlockNumber=new LinkedHashMap<Integer, String>();
		databaseManager=new DatabaseManager();
		printerClass=new PrinterClass();
		sound=new SoundClass();
		
		contructorNames=new ArrayList<String>();
		
		winnersTable=new TableView<>();

		rowCol=new TableColumn<>("ተራ ቑ");
		rowCol.setMinWidth(40);
		rowCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

		winnerUnionNameCol=new TableColumn<>("ሽም ዕድለኛ ኮንትራክተር");
		winnerUnionNameCol.setMinWidth(300);
		winnerUnionNameCol.setCellValueFactory(new PropertyValueFactory<>("contructorName"));
		
		wonBlockNumberCol=new TableColumn<>("ዝበፅሖ ብሎክ ቁፅሪ");
		wonBlockNumberCol.setMinWidth(150);
		wonBlockNumberCol.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
		
		levelCol=new TableColumn<>("ደረጃ ኮንትራክተር");
		levelCol.setMinWidth(150);
		levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));

		lotteryNoticeLb.setText("");
		winnersNameLb.setAlignment(Pos.CENTER);
		winnersBlockNumberLb.setAlignment(Pos.CENTER);

		enableButton(generateWinnersBtn);
		disableButton(printWinnerContructorsBtn);
		disableButton(refreshContructorsLotteryBtn);
		enableTextField(lotteryRoundTf);
		lotteryRoundTf.setText("");
		
		lotteryRoundTf.setStyle("-fx-border-color: black");
	}



	public void refreshContructorsLotteryBtnAction(){
		new Controller().dashboardGenerateWinnerContructorsBtnAction();
		/*emptyComponents();
		roundCandidateContructorsIds.clear();
		 String firstLevel=databaseManager.getLevelSequence(1).get(0);
	     enterLevelValuesLb.setText(firstLevel+" ኮንትራክተር ክበፅሖ ዝክእል በዝሒ ብሎክ ኣእትው");
		lotteryRoundTf.setText("");
		lotteryNoticeLb.setText("");
		contructorsLotteryBlocksLb.setText("");
		disableTextField(enterLevelValuesTf);
	    enableTextField(lotteryRoundTf);
	    */
	}
	
	public void lotteryRoundTfAction(){
		String roundName=lotteryRoundTf.getText();
		if(roundName.equals("")){
				return;
			}
		int roundId=databaseManager.getRoundId(roundName);
	 	if(!(roundId>=1)){
	 		CommonMethodsClass.showNotificationMessages("እዚ ዙር ኣይተመዝገበን ። \n  በይዘኦም ትክክለኛ  ዙር የእትው");
			return;	
	 	}
	 	this.roundId=roundId;
	 	this.roundName=databaseManager.getRoundName(this.roundId);
	 	
	 	if(databaseManager.getHouseWinnersSavePoint(this.roundId)>0){
	 		new CommonMethodsClass().showNotificationMessages(roundName+"  ናይ ማሕበራት ዕፃ ገና ኣይተወድአን\n መጀመሪያ   ናይ  "+roundName+"  ዕፃ ማሕበራት ይወድኡ");
	 		return;
	 	}

		numberOfBlocks=databaseManager.getAllWinnerAssociations(roundId).size();//winner associations determine number of blocks for contructors
		if(!(numberOfBlocks>0)){
			CommonMethodsClass.showNotificationMessages(" በዚ ዙር  ምንም ዝተመዝገቡ ብሎክ የለን");
				return;	
				}
			
		String firstLevel=databaseManager.getLevelSequenceIndex(1);
	 	enterLevelValuesLb.setText(firstLevel+" ኮንትራክተር ክበፅሖ ዝክእል በዝሒ ብሎክ ኣእትው");
	     enterLevelValuesLb.setStyle("-fx-alignment: center");	
	
			
				contructorsLotteryBlocksLb.setText("በዝሒ ብሎክ ናይ እዚ ዕፃ :  "+databaseManager.getAllWinnerAssociations(roundId).size());

	 	enableTextField(enterLevelValuesTf);
		disableTextField(lotteryRoundTf);

}
	
	int i=0, flag=0;
	public void enterLevelValuesTfAction(){
		
		 String valueStr=enterLevelValuesTf.getText();
		int numberOfBlocks=databaseManager.getAllWinnerAssociations(roundId).size();

		if(valueStr.equals("")){
				return;
			}
		if(!valueStr.matches("^(-?)\\d+$")){
		return;
		}
		int value=Integer.valueOf(valueStr);
		if(value<0){
			return;
		}
	
		if(value>0)
			flag=1;
		
		int numberOfLevels=databaseManager.getAllLevelNames().size();
	
		enterLevelValuesTf.setText("");

		ArrayList<Integer> levelIds=databaseManager.getAllLevelIds();
		levelValues.put(levelIds.get(i), value);
		i++;

		int levelId=databaseManager.getLevelId(databaseManager.getLevelSequenceIndex(i));
		int numberOfContructorsWithThisLeevelId=databaseManager.getNumberOfContructorWithLevel(levelId, roundId);
		totalContructorsCapacity+=numberOfContructorsWithThisLeevelId*value;
		//CommonMethodsClass.showNotificationMessages(numberOfContructorsWithThisLeevelId+"  "+totalContructorsCapacity+"  "+numberOfBlocks);
		
		if(i==numberOfLevels-1){//resets values			
			if(totalContructorsCapacity<numberOfBlocks || flag==0){
				CommonMethodsClass.showNotificationMessages("ጠቕላላ ዓቕሚ ኮንትራክተራት "+totalContructorsCapacity+" ብሎካት ጥራሕ እዩ።\n  ዘለና  በዝሒ ብሎክ  ግን  "+numberOfBlocks+" እዩ");
				i=0;
				totalContructorsCapacity=0;
				String nextLevel=databaseManager.getLevelSequenceIndex(i+1);
				enterLevelValuesLb.setText(nextLevel+" ኮንትራክተር ክበፅሖ ዝክእል በዝሒ ብሎክ ኣእትው");
			     enterLevelValuesLb.setStyle("-fx-alignment: center");
			
				return;
			}
			i=0;
			totalContructorsCapacity=0;
			startContructorsLottery=true;
			enableButton(generateWinnersBtn);
			disableTextField(enterLevelValuesTf);
			enterLevelValuesLb.setText("");
		return;
		}
 //next
		String nextLevel=databaseManager.getLevelSequenceIndex(i+1);
		//CommonMethodsClass.showNotificationMessages(nextLevel+"  "+databaseManager.getLevelSequenceIndex(i));
		enterLevelValuesLb.setText(nextLevel+" ኮንትራክተር ክበፅሖ ዝክእል በዝሒ ብሎክ ኣእትው");
	     enterLevelValuesLb.setStyle("-fx-alignment: center");
	}
	
	public void printWinnerContructorsBtnAction(){
		  javax.swing.UIManager.put("PrinterClass.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	       javax.swing.UIManager.put("PrinterClass.messageFont", new Font("Nyala", Font.PLAIN, 14));
			
		new CurrentPrinter().print(winnersTable, roundName);
		enableButton(refreshContructorsLotteryBtn);

		/*	winnersTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
		printerClass.printSetup(winnersTable, Main.primaryStage);//prints table
		enableButton(refreshContructorsLotteryBtn);*/
	}
	
	public void contructorLevelsCbClickedAction(){
		ObservableList<String> levels=FXCollections.observableArrayList(databaseManager.getAllLevelNames());
		contructorLevelsCb.setItems(levels);
	}
	

public void generateWinnerContructorsBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}

}
