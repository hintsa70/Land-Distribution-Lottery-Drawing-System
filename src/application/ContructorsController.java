package application;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ContructorsController implements Initializable{
	CommonMethodsClass commonMethodsClass = new CommonMethodsClass(); 
	static DatabaseManager databaseManager=new DatabaseManager();
	
	static String initializerString="";
	static String contructorName, contructorPhone, contructorAddress, contructorRank, contructorRoundName;
	static boolean backToCreateContructors=false;
	@FXML Label registerContructorsHeader, registerContructorsFooter, editContructorsHeader, editContructorsFooter;
	@FXML Label manageContructorsHeader, manageContructorsFooter, addContructorsHeader, addContructorsFooter;
	@FXML Button registerContructorsBackBtn, manageContructorsBackBtn, addContructorsBackBtn, editContructorsBackBtn;
	@FXML ComboBox otherRegistrationTypesCb;
	String contructorsLevelCbText="ደረጃ ኮንትራክተር ይምረፁ";
	int roundId=0;
	String roundName=null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	  ///     javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
	     //  javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
	     
		if(initializerString.equalsIgnoreCase("registerContructors")){
			otherRegistrationTypesCb.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			otherRegistrationTypesCb.setValue("ካልኦት ዓይነታት ምዝገባ...");
			
			registerContructorsRACb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
			registerContructorsRACb.setValue(contructorsLevelCbText);
			
			Image backImage=new Image("images/app_back_button.jpg");
			registerContructorsBackBtn.setGraphic(new ImageView(backImage));	
			Image header=new Image("images/headerFl.jpg");
			registerContructorsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			registerContructorsFooter.setGraphic(new ImageView(footer));
		}

		if(initializerString.equalsIgnoreCase("addContructors")){
			registerContructorsRACb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
			registerContructorsRACb.setValue(contructorsLevelCbText);

			Image backImage=new Image("images/app_back_button.jpg");
			addContructorsBackBtn.setGraphic(new ImageView(backImage));
			
			Image header=new Image("images/headerFl.jpg");
			addContructorsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			addContructorsFooter.setGraphic(new ImageView(footer));
		}
		if(initializerString.equalsIgnoreCase("list")){	
			contructorsTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			//otherRegistrationTypesCb.setValue("ካልኦት ዓይነታት ምዝገባ...");

			Image backImage=new Image("images/app_back_button.jpg");
			manageContructorsBackBtn.setGraphic(new ImageView(backImage));
			
			Image header=new Image("images/headerFl.jpg");
			manageContructorsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			manageContructorsFooter.setGraphic(new ImageView(footer));
			
			listAllContructors();
		}
		if(initializerString.equalsIgnoreCase("edit")){
			editContructorsRACb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
			editContructorsRACb.setValue(contructorsLevelCbText);

			editContractorsRoundTf.setText(contructorRoundName); 
			editContructorsCNTf.setText(contructorName); 
			editContructorsPNTf.setText(contructorPhone+""); 
			editContructorsAddTf.setText(contructorAddress+""); 						
			int roundId=databaseManager.getRoundId(contructorRoundName);
			editContructorsRACb.setValue(databaseManager.getLevelName(databaseManager.getContructorRank("Round_1___"+contructorName, roundId)));
			editContructorsRACb.getSelectionModel().select(databaseManager.getContructorRank("Round_1___"+contructorName, roundId));
			
			Image backImage=new Image("images/app_back_button.jpg");
			editContructorsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			editContructorsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			editContructorsFooter.setGraphic(new ImageView(footer));
		}
	}
	
	public void manageContructors(){
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");
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
				return;
			}
			//new Controller().dashboardListOfWinnersBtnAction();//reloads table
			this.listAllContructors();
		}
		

	//CONTRUCTURS TABLE

	@FXML TableView<Contructor> contructorsTable=new TableView<>();

	@FXML TableColumn<Contructor, Integer> contructorRowNoCol;
	@FXML TableColumn<Contructor, String> contructorNameCol;
	@FXML TableColumn<Contructor, String> contructorPhoneCol;
	@FXML TableColumn<Contructor, String> contructorAddressCol;
	@FXML TableColumn<Contructor, String> contructorRankCol;
	@FXML TableColumn<Contructor, String> contructorRoundNameCol;
	
	@FXML Button addContructorBtn;
	@FXML Button editContructorBtn;
	@FXML Button deleteContructorBtn;	

	public void listAllContructors(){

		contructorRowNoCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

		contructorNameCol.setCellValueFactory(new PropertyValueFactory<>("contructorName"));

		contructorPhoneCol.setCellValueFactory(new PropertyValueFactory<>("contructorPhone"));

		contructorAddressCol.setCellValueFactory(new PropertyValueFactory<>("contructorAddress"));

		contructorRankCol.setCellValueFactory(new PropertyValueFactory<>("contructorRank"));

		contructorRoundNameCol.setCellValueFactory(new PropertyValueFactory<>("contructorRoundName"));

	//sets Associations TABLE_VIEW
		contructorsTable.setItems(getContructors());//getContructors method populates table data
		
		contructorsTable.setFixedCellSize(25);
	


	}

	public void addContructors(){
		initializerString="addContructors";
		commonMethodsClass.loadExistingWindowFxmlPage("addContructors.fxml");
	
	}

	public void editContructors(){
		
		if(contructorsTable.getSelectionModel().getSelectedIndex()==-1)
		{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ኮንትራክተር ይምረፁ");
			return;
		}
		contructorName=contructorsTable.getSelectionModel().getSelectedItem().getContructorName();
		contructorPhone=contructorsTable.getSelectionModel().getSelectedItem().getContructorPhone();
		contructorAddress=contructorsTable.getSelectionModel().getSelectedItem().getContructorAddress();
		contructorRank=contructorsTable.getSelectionModel().getSelectedItem().getContructorRank();
		contructorRoundName=contructorsTable.getSelectionModel().getSelectedItem().getContructorRoundName();
		
						if(contructorsTable.getSelectionModel().getSelectedIndex()==-1)
						{
							CommonMethodsClass.showNotificationMessages("መጀመርያ ሽም ኮንትራክተር ይምረፁ");
							return;
						}
						contructorName=contructorsTable.getSelectionModel().getSelectedItem().getContructorName();
				
	initializerString="edit";
	commonMethodsClass.loadExistingWindowFxmlPage("editContructors.fxml");

	}
	
	public void deleteContructors(){
		if(contructorsTable.getSelectionModel().getSelectedIndex()==-1)
		{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ኮንትራክተር ይምረፁ");
			return;
		}
		String contructorName=contructorsTable.getSelectionModel().getSelectedItem().getContructorName();	
		contructorRoundName=contructorsTable.getSelectionModel().getSelectedItem().getContructorRoundName();
		contructorName="Round_"+databaseManager.getRoundId(contructorRoundName)+"___"+contructorName;
		
int roundId=databaseManager.getRoundId(contructorsTable.getSelectionModel().getSelectedItem().getContructorRoundName());
String message=(String) contructorName.subSequence((9+String.valueOf(roundId).length()), contructorName.length());		
boolean deleteOrNot=new CommonMethodsClass().showConfirmMessages("ኮንትራክተር  "+message+"  ንምስራዝ ርግፅኛ ድዮም?");
if(deleteOrNot==false)
	return;
		
int contructorId=databaseManager.getContructorId(contructorName, databaseManager.getRoundId(contructorRoundName));
databaseManager.deleteRow("Contructors", contructorId);
	databaseManager.dropTable(contructorName);
		commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");

	}

//REGISTER CONTRUCTORS
	@FXML Label registerContructorsLb;
	@FXML TextField registerContructorsCNTf, registerContructorsPNTf, registerContructorsAddTf, registerContractorsRoundTf;
	@FXML Button registreContructorsBtn;
	@FXML
	public void registerContructorsButtonAction(){
		String roundName=registerContractorsRoundTf.getText();
		int roundId=1;
		
		//start Validations
		if(registerContractorsRoundTf.getText().equals("")){
			registerContructorsLb.setText("ዙር ምዝገባ ኣይኣተወን ");
			return;
		}
		else{
			if(!databaseManager.tableExists("Rounds")){
				databaseManager.createRoundsTable();
				registerContructorsLb.setText("ማህደር ዙር ተፈጢሩ");
			}
			else{
				if(databaseManager.isUnique("roundName", roundName, "Rounds"))
					databaseManager.insertRounds(roundName);
				
				roundId=databaseManager.getRoundId(roundName);
			}

		}

	if(!databaseManager.tableExists("Contructors")){
	databaseManager.createContructorsTable();
	registerContructorsLb.setText("ማህደር ኮንትራክተራት ተፈጢሩ");
	}
	if(registerContructorsCNTf.getText().equals("")|| registerContructorsPNTf.getText().equals("")|| registerContructorsAddTf.getText().equals(""))
	{
		registerContructorsLb.setText("ክፍቲ ቦታታት ኣይተመልኡን ");
	return;
	}

	String contructorName=registerContructorsCNTf.getText();
	contructorName="Round_"+roundId+"___"+contructorName;

	String phone=registerContructorsPNTf.getText();
	String address=registerContructorsAddTf.getText();
	int rank=registerContructorsRACb.getSelectionModel().getSelectedIndex();

	if(!(rank>=0)){
		System.out.println("or   "+this.contructorName+"  "+contructorName);
		this.contructorName="";
		contructorName="";
		registerContructorsLb.setText("ደረጃ ኮንራክተር ምረፅ");
	return;
	}
	rank=rank+1;//as starts from 0
	if(!databaseManager.isUnique("contructorName", contructorName, "Contructors"))
	{
		this.contructorName="";
		contructorName="";
		registerContructorsLb.setText("ሽም ኮንትራክተር ተደጊሙ");
	return;
	}
	//end of validation	
	databaseManager.insertContructors(roundId, contructorName, phone, address, rank);
	registerContructorsLb.setText("ኮንትራክተር ብትክክል ተመዝጊቡ");

	//reset TextFields
	contructorName="";
	registerContructorsCNTf.setText("");
	registerContructorsPNTf.setText("");
	registerContructorsAddTf.setText("");
	registerContructorsRACb.getSelectionModel().clearSelection();

	if(initializerString.equalsIgnoreCase("addContructors")){
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");
	}
	}


	//EDIT CONTRUCTORS
	@FXML Label editContructorsLb, editContructorsCounterLb;
	@FXML TextField editContractorsRoundTf, editContructorsCNTf,editContructorsPNTf, editContructorsAddTf;
	@FXML ComboBox editContructorsRACb;
	@FXML Button editContructorsBtn;
	@FXML
	public void editContructorsButtonAction(){
		String roundName=editContractorsRoundTf.getText();
		int roundId=1;
		
		//start Validations
		if(editContractorsRoundTf.getText().equals("")){
			editContructorsLb.setText("ዙር ምዝገባ ኣይኣተወን ");
			return;
		}
		else{
			if(!databaseManager.tableExists("Rounds")){
				databaseManager.createRoundsTable();
				editContructorsLb.setText("ማህደር ዙር ተፈጢሩ");
			}
			else{
				if(databaseManager.isUnique("roundName", roundName, "Rounds"))
					databaseManager.insertRounds(roundName);
				
				roundId=databaseManager.getRoundId(roundName);
			}

		}
			
		if(!databaseManager.tableExists("Contructors")){
		databaseManager.createAssociationsTable();
		editContructorsLb.setText("ማህደር ኮንትራክተራት ተፈጢሩ");
		}
		if(editContructorsCNTf.getText().equals("")|| editContructorsPNTf.getText().equals("")|| editContructorsAddTf.getText().equals(""))
		{
			editContructorsLb.setText("ክፍቲ ቦታታት ኣይተመልኡን ");
		return;
		}
			
		String contructorName=editContructorsCNTf.getText();
		System.out.println("first  "+this.contructorName);
		String tempContructorName=this.contructorName;
		this.contructorName="Round_"+roundId+"___"+this.contructorName;
		contructorName="Round_"+roundId+"___"+contructorName;
System.out.println("second  "+this.contructorName);
		
		String phone=editContructorsPNTf.getText();
		String address=editContructorsAddTf.getText();
		int rank=editContructorsRACb.getSelectionModel().getSelectedIndex();

		if(!(rank>=0)){
			this.contructorName=tempContructorName;
			contructorName=tempContructorName;
			editContructorsLb.setText("ደረጃ ኮንትራክተር ምረፅ");
		return;
		}
		rank=rank+1;//as starts from 0
		System.out.println("yes editing "+contructorName+"  "+this.contructorName);

		if(!databaseManager.isUnique("contructorName", contructorName, "Contructors") && !this.contructorName.equalsIgnoreCase(contructorName) )
		{
		editContructorsLb.setText("ሽም ኮንትራክተር ተደጊሙ");
		return;
		}
		//end of validation	
		
		databaseManager.setContructorName(contructorName, databaseManager.getContructorId(this.contructorName, roundId));
		databaseManager.setContructorRegistrationRound(roundId, contructorName);
		databaseManager.setContructorPhone(phone, contructorName);
		databaseManager.setContructorAddress(address, contructorName);
		databaseManager.setContructorRank(rank, contructorName);

		editContructorsLb.setText("ኮንትራክተር ብትክክል ተደጊሙ");
		this.contructorName="";
		//reset TextFields
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");

		
	}
	

	//getAssociations method to fill the table
	public ObservableList<Contructor> getContructors(){
		ObservableList<Contructor> contructors=FXCollections.observableArrayList();
	Integer conRowNo=1;
	ArrayList<String> contructorNames=databaseManager.getAllStrings("contructorName", "Contructors");
	ArrayList<String> contructorPhones=databaseManager.getAllStrings("phone", "Contructors");
	ArrayList<String> contructorAddresses=databaseManager.getAllStrings("address", "Contructors");
	ArrayList<Integer> contructorRanks=databaseManager.getAllIntegers("rank", "Contructors");
	ArrayList<Integer> roundIds=databaseManager.getAllIntegers("roundId", "Contructors");

		for(int i=0;i<contructorNames.size(); i++){
			String roundName2=databaseManager.getRoundName(roundIds.get(i));
			if(!roundName2.equalsIgnoreCase(roundName) && started)
				continue;
		if(!started)
			roundName=databaseManager.getContructorRegistrationRound(databaseManager.getAllContructorsIds().get(conRowNo-1));
			String rankName=databaseManager.getLevelName(contructorRanks.get(i));
	String contructorName=(String) contructorNames.get(i).subSequence((9+String.valueOf(roundIds.get(i)).length()), contructorNames.get(i).length());
	contructors.add(new Contructor(conRowNo, contructorName, contructorPhones.get(i), contructorAddresses.get(i), rankName, roundName));
			conRowNo++;
		}
//		*/
		return contructors;
	}

	public void registerContructorsBackBtnAction(){
		AssociationsController.initializerString="registerAssociations";
		commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
	}
public void createLevelsBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("registerContructors.fxml");
}

public void createRolessBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("registerContructors.fxml");
}

public void manageContructorsBackBtnAction(){
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}
public void addContructorsBackBtnAction(){
	initializerString="list";
commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");
}
public void editContructorsBackBtnAction(){
	initializerString="list";
commonMethodsClass.loadExistingWindowFxmlPage("manageContructors.fxml");
}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////
//NB: BEGIN  METHODS EXIST AT BOTH cONTROLLERS associations and Contructors
/////////////////////////////////////////////////////////////////////////////////////////////////////	
//RADIO BUTTON EVENTS
/////////////////////////////////////////////////////////////////////////////////////////////////////
public void registerAssociationsRbAction(){
	AssociationsController.initializerString="registerAssociations";
commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
}
public void registerContructorsRbAction(){
	initializerString="registerContructors";
commonMethodsClass.loadExistingWindowFxmlPage("registerContructors.fxml");
}

@FXML ComboBox registerContructorsRACb;
@FXML
public void contructorRankCbAction(){
ObservableList<String> lists=FXCollections.observableArrayList(databaseManager.getAllLevelNames());
if(initializerString.equalsIgnoreCase("edit")){	
editContructorsRACb.setItems(lists);
}
else
registerContructorsRACb.setItems(lists);

}

//@FXML ComboBox otherRegistrationTypesCb;
public void otherRegistrationTypesCbSetValues(){
	ObservableList<String> otherRegistrationTypes=FXCollections.observableArrayList("ደረጃ ኮንታክተራት ", "ዓይነት ሓላፍነት ኣባላት");
	otherRegistrationTypesCb.setItems(otherRegistrationTypes);
}
public void otherRegistrationTypesCbAction(){
	if(otherRegistrationTypesCb.getSelectionModel().getSelectedIndex()==0){
		AssociationsController.initializerString="createLevels";
		commonMethodsClass.loadExistingWindowFxmlPage("CreateLevels.fxml");
	}
	if(otherRegistrationTypesCb.getSelectionModel().getSelectedIndex()==1){
		AssociationsController.initializerString="createRoles";
		commonMethodsClass.loadExistingWindowFxmlPage("CreateRoles.fxml");
	}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////
//END  OF COMMON
/////////////////////////////////////////////////////////////////////////////////////////////////////

public void manageContructorsPrintBtnAction(){
	if(!started)
	new CurrentPrinter().print(this.contructorsTable, "", "ዝርዝር ዝተመዝገቡ ኮንትራክተራት ");
	else
	new CurrentPrinter().print(this.contructorsTable, roundName, "ዝርዝር ዝተመዝገቡ ኮንትራክተራት ");

	this.roundName=null;
	this.manageContructors();
}
	
}
