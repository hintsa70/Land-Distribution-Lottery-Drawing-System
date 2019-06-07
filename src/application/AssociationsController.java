package application;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
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
import javafx.stage.Stage;

public class AssociationsController implements Initializable{
	CommonMethodsClass commonMethodsClass = new CommonMethodsClass(); 
	Path p=new Path() {
		@Override
		public FileSystem getFileSystem() {
			return null;
		}

		@Override
		public boolean isAbsolute() {
			return false;
		}

		@Override
		public Path getRoot() {
			return null;
		}

		@Override
		public Path getFileName() {
			return null;
		}

		@Override
		public Path getParent() {
			return null;
		}

		@Override
		public int getNameCount() {
			return 0;
		}

		@Override
		public Path getName(int index) {
			return null;
		}

		@Override
		public Path subpath(int beginIndex, int endIndex) {
			return null;
		}

		@Override
		public boolean startsWith(Path other) {
			return false;
		}

		@Override
		public boolean startsWith(String other) {
			return false;
		}

		@Override
		public boolean endsWith(Path other) {
			return false;
		}

		@Override
		public boolean endsWith(String other) {
			return false;
		}

		@Override
		public Path normalize() {
			return null;
		}

		@Override
		public Path resolve(Path other) {
			return null;
		}

		@Override
		public Path resolve(String other) {
			return null;
		}

		@Override
		public Path resolveSibling(Path other) {
			return null;
		}

		@Override
		public Path resolveSibling(String other) {
			return null;
		}

		@Override
		public Path relativize(Path other) {
			return null;
		}

		@Override
		public URI toUri() {
			return null;
		}

		@Override
		public Path toAbsolutePath() {
			return null;
		}

		@Override
		public Path toRealPath(LinkOption... options) throws IOException {
			return null;
		}

		@Override
		public File toFile() {
			return null;
		}

		@Override
		public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
			return null;
		}

		@Override
		public WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException {
			return null;
		}

		@Override
		public Iterator<Path> iterator() {
			return null;
		}

		@Override
		public int compareTo(Path other) {
			return 0;
		}
	};
	static DatabaseManager databaseManager=new DatabaseManager();
	static String associationName, registrationRoundName;
	static int memberAdded=0, associationRegisteredNumber=0;
	static int registrationNumber, numberOfMembers;
	static String initializerString="";
	@FXML Button addAssociationsBackBtn, registerAssociationsBackBtn, registerAssociationMembersBackBtn, createLevelsBackBtn, createRolesBackBtn;
	@FXML Button manageAssociationsBackBtn, editAssociationsBackBtn;
	@FXML Label addAssociationHeader, addAssociationFooter, createLevelsHeader, createLevelsFooter;
	@FXML Label createRolesHeader, createRolesFooter, editAssociationsHeader, editAssociationsFooter;
	@FXML Label registerAssociationsHeader, registerAssociationsFooter, registerAssociationMembersHeader, registerAssociationMembersFooter;
	@FXML Label manageAssociationsHeader, manageAssociationsFooter;

	String roundName=null;
	int roundId=0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//	javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	  //     javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
	    //   javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
	     
		if(initializerString.equalsIgnoreCase("registerAssociations")){
			otherRegistrationTypesCb.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			otherRegistrationTypesCb.setValue("ካልኦት ዓይነታት ምዝገባ...");
			
			Image backImage=new Image("images/app_back_button.jpg");
			registerAssociationsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			registerAssociationsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			registerAssociationsFooter.setGraphic(new ImageView(footer));
			initializerString="";
		}
		
		
		if(initializerString.equalsIgnoreCase("registerAssociationMembers")){
			memberRoleCb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
			memberRoleCb.setValue("ሓላፍነት ኣባል ይምረፁ...");
			
			Image backImage=new Image("images/app_back_button.jpg");
			registerAssociationMembersBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			registerAssociationMembersHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			registerAssociationMembersFooter.setGraphic(new ImageView(footer));
			initializerString="";
		}
		
		if(initializerString.equalsIgnoreCase("addAssociation")){
			Image backImage=new Image("images/app_back_button.jpg");
			addAssociationsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			addAssociationHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			addAssociationFooter.setGraphic(new ImageView(footer));
		
		}
		
		if(initializerString.equalsIgnoreCase("list")){
			associationsTable.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
			//otherRegistrationTypesCb.setValue("ካልኦት ዓይነታት ምዝገባ...");

			Image backImage=new Image("images/app_back_button.jpg");
			manageAssociationsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			manageAssociationsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			manageAssociationsFooter.setGraphic(new ImageView(footer));
			
			listAllAssociations();
		}
		else if(initializerString.equalsIgnoreCase("edit"))
		{
			Image backImage=new Image("images/app_back_button.jpg");
		    editAssociationsBackBtn.setGraphic(new ImageView(backImage));
			editAssociationsRoundTf.setText(registrationRoundName); 
			editAssociationsANTf.setText(associationName); 
			editAssociationsRNTf.setText(registrationNumber+""); 
			editAssociationsNMTf.setText(numberOfMembers+""); 	
			editAssociationsLb.setText("");
			
			Image header=new Image("images/headerFl.jpg");
			editAssociationsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			editAssociationsFooter.setGraphic(new ImageView(footer));
		}
		else if(initializerString.equalsIgnoreCase("createLevels")){
			Image backImage=new Image("images/app_back_button.jpg");
			createLevelsBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			createLevelsHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			createLevelsFooter.setGraphic(new ImageView(footer));
		}
		else if(initializerString.equalsIgnoreCase("createRoles")){
			Image backImage=new Image("images/app_back_button.jpg");
			createRolesBackBtn.setGraphic(new ImageView(backImage));
			Image header=new Image("images/headerFl.jpg");
			createRolesHeader.setGraphic(new ImageView(header));
			Image footer=new Image("images/footerFl.jpg");
			createRolesFooter.setGraphic(new ImageView(footer));
		}

	}
	
	
	public void registerAssociations(){
		initializerString="registerAssociations";
		commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
	}
	
	public void manageAssociations(){
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
	}
	

	@FXML TableView<Association> associationsTable=new TableView<>();

	@FXML TableColumn<Association, Integer> rowNoCol;
	@FXML TableColumn<Association, String> associationNameCol;
	@FXML TableColumn<Association, Integer> registrationNumberCol;
	@FXML TableColumn<Association, Integer> nameOfChairmanCol;
	@FXML TableColumn<Association, Integer> phoneNumberCol;
	//@FXML TableColumn<Association, String> roundNameCol;

	@FXML Button addMembersBtn;
	@FXML Button addAssociationBtn;
	@FXML Button editAssociationBtn;
	@FXML Button deleteAssociationBtn;
	

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
			this.listAllAssociations();
		}
		

	public void listAllAssociations(){
		rowNoCol.setCellValueFactory(new PropertyValueFactory<>("rowNo"));

		associationNameCol.setCellValueFactory(new PropertyValueFactory<>("associationName"));

		registrationNumberCol.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

		nameOfChairmanCol.setCellValueFactory(new PropertyValueFactory<>("nameOfChairman"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		//roundNameCol.setCellValueFactory(new PropertyValueFactory<>("roundName"));

		//sets Associations TABLE_VIEW
		associationsTable.setItems(getAssociations());//getAssociations method populates table data
			
		associationsTable.setFixedCellSize(25);
	}
	
	public void addAssociations(){
		initializerString="addAssociation";
		commonMethodsClass.loadExistingWindowFxmlPage("addAssociations.fxml");
	}

	public void editAssociations(){


		initializerString="edit";
		
		if(associationsTable.getSelectionModel().getSelectedIndex()==-1)
		{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ማሕበር ይምረፁ");
			return;
		}
		associationName=associationsTable.getSelectionModel().getSelectedItem().getAssociationName();
		registrationNumber=associationsTable.getSelectionModel().getSelectedItem().getRegistrationNumber();
		registrationRoundName=associationsTable.getSelectionModel().getSelectedItem().getRoundName();
		numberOfMembers=associationsTable.getSelectionModel().getSelectedItem().getNumberOfMembers();
		if(associationsTable.getSelectionModel().getSelectedIndex()==-1)
						{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ሽም ማሕበር ይምረፁ");
							return;
						}
						associationName=associationsTable.getSelectionModel().getSelectedItem().getAssociationName();
			commonMethodsClass.loadExistingWindowFxmlPage("editAssociations.fxml");
	}
	
	public void deleteAssociations(){
		if(associationsTable.getSelectionModel().getSelectedIndex()==-1)
		{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ማሕበር ይምረፁ");
			return;
		}
		
		String associationName=associationsTable.getSelectionModel().getSelectedItem().getAssociationName();
		String registration=associationsTable.getSelectionModel().getSelectedItem().getRoundName();
		associationName="Round_"+databaseManager.getRoundId(registrationRoundName)+"___"+associationName;
	
int roundId=databaseManager.getRoundId(associationsTable.getSelectionModel().getSelectedItem().getRoundName());
String message=(String) associationName.subSequence((9+String.valueOf(roundId).length()), associationName.length());		
boolean deleteOrNot=new CommonMethodsClass().showConfirmMessages("ማሕበር "+message+"  ንምስራዝ ርግፅኛ ድዮም?");
if(deleteOrNot==false)
	return;

int associationId=databaseManager.getAssociationId(associationName, databaseManager.getRoundId(registrationRoundName));
		databaseManager.deleteRow("Associations", associationId);
		databaseManager.dropTable(associationName);
		
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
	
	}
	
	public void addAssociationMembers(){

		if(associationsTable.getSelectionModel().getSelectedIndex()==-1)
		{
			CommonMethodsClass.showNotificationMessages("መጀመርያ ሽም ማሕበር ይምረፁ");
			return;
		}
	   int roundId=databaseManager.getRoundId(associationsTable.getSelectionModel().getSelectedItem().getRoundName());
	    associationName=associationsTable.getSelectionModel().getSelectedItem().getAssociationName();
		associationName="Round_"+roundId+"___"+associationName;
	   int associationId=databaseManager.getAssociationId(associationName, roundId);
		    associationRegisteredNumber=databaseManager.getRegisteredNumber(associationName);
		if(associationRegisteredNumber>=databaseManager.getAssociationNumberOfMembers(associationId)){
			CommonMethodsClass.showNotificationMessages(" ማሕበር  "+associationName+"  ዝለዓለ ክሕዞ ዝክእል ዓቕሚ  "+associationRegisteredNumber+"  በፂሑ");
				return;
			}
		initializerString="registerAssociationMembers";
		commonMethodsClass.loadExistingWindowFxmlPage("registerAssociationMembers.fxml");
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
//////////////////////////////////////////////////////////////////////////////////////////////////////

	//REGISTER ASSOCIATIONS
	@FXML Label registerAssociationsLb, registerAssociationsCounterLb;
	@FXML TextField registerAssociationsANTf;
	@FXML TextField registerAssociationsRNTf;
	@FXML TextField registerAssociationsNMTf;
	@FXML TextField registerAssociationsRoundTf;
	@FXML Button registerAssociationsBtn;
	@FXML
	public void registerAssociationsButtonAction(){
		String roundName=registerAssociationsRoundTf.getText();
		int roundId=1;
		
		//start Validations
		if(registerAssociationsRoundTf.getText().equals("")){
			registerAssociationsLb.setText("ዙር ምዝገባ ኣይኣተወን ");
			return;
		}
		else{
			if(!databaseManager.tableExists("Rounds")){
				databaseManager.createRoundsTable();
			registerAssociationsLb.setText("ማህደር ዙር ተፈጢሩ");
			}
			else{
				if(databaseManager.isUnique("roundName", roundName, "Rounds"))
					databaseManager.insertRounds(roundName);
				
				roundId=databaseManager.getRoundId(roundName);
			}

		}
			
		if(!databaseManager.tableExists("Associations")){
		databaseManager.createAssociationsTable();
		registerAssociationsLb.setText("ማህደር ማሕበራት ተፈጢሩ");
		}
		if(registerAssociationsANTf.getText().equals("")|| registerAssociationsRNTf.getText().equals("")|| registerAssociationsNMTf.getText().equals(""))
		{
		registerAssociationsLb.setText("ክፍቲ ቦታታት ኣይተመልኡን ");
		return;
		}
		
		if(!registerAssociationsRNTf.getText().matches("^(-?)\\d+$")){
		registerAssociationsLb.setText("ናይ ምዝገባ ቑፅሪ ተደጊሙ");
		return;
		}
		if(!registerAssociationsNMTf.getText().matches("^(-?)\\d+$")){
		registerAssociationsLb.setText("በዝሒ ኣባላት ቑፅሪ ክኸውን ኣለዎ");
		return;
		}
		
		 String associationName=registerAssociationsANTf.getText();
			associationName="Round_"+roundId+"___"+associationName;
		 associationName=associationName.replaceAll("\\s+","_");//replaces all white spaces by underscore from association name in order to create table
		 int registrationNumber=Integer.valueOf(registerAssociationsRNTf.getText());
		 int numberOfMembers=Integer.valueOf(registerAssociationsNMTf.getText());
		
		
		if(!(registrationNumber>0)){
		registerAssociationsLb.setText("መመዝገቢ ቑፅሪ ካብ ባዶ ን ላዕሊ ክኸውን ኣለዎ");
		return;
		}
		if(!(numberOfMembers>0)){
		registerAssociationsLb.setText("በዝሒ ኣባላት ካብ ባዶ ክበልፅ ኣለዎ");
		return;
		}
		
		if(!databaseManager.isUnique("associationName", associationName, "Associations", roundId))
		{
		registerAssociationsLb.setText("ሽም ማሕበር ተደጊሙ");
		return;
		}
		if(!databaseManager.isUnique("registrationNumber", registrationNumber, "Associations", roundId))
		{
		registerAssociationsLb.setText("መመዝገቢ ቑፅሪ ተደጊሙ");
		return;
		}
		//end of validation	
		databaseManager.insertAssociations(roundId, associationName, registrationNumber, numberOfMembers);
		registerAssociationsLb.setText("ማሕበር ብትክክል ተመዝጊቡ");
		//reset TextFields
		registerAssociationsANTf.setText("");
		registerAssociationsRNTf.setText("");
		registerAssociationsNMTf.setText("");

		if(databaseManager.tableExists(associationName))
			databaseManager.dropTable(associationName);
		databaseManager.createAssociationMembersTable(associationName);

		if(initializerString.equalsIgnoreCase("addAssociation")){//if from add Button it has to return to manage associations page
			initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
		}
	
	}



	//EDIT ASSOCIATION
	@FXML Label editAssociationsLb, editAssociationsCounterLb;
	@FXML TextField editAssociationsANTf;
	@FXML TextField  editAssociationsRNTf;
	@FXML TextField editAssociationsNMTf;
	@FXML TextField editAssociationsRoundTf;
	@FXML Button editAssociationsBtn;
	@FXML
	public void editAssociationsButtonAction(){
		String roundName=editAssociationsRoundTf.getText();
		int roundId=1;
		//start Validations
		if(editAssociationsRoundTf.getText().equals("")){
			editAssociationsLb.setText("ዙር ምዝገባ ኣይኣተወን");
			return;
		}
		else{
			if(!databaseManager.tableExists("Rounds")){
				databaseManager.createRoundsTable();
				editAssociationsLb.setText("ማህደር ዙር ተፈጢሩ");
			}
			else{
				if(databaseManager.isUnique("roundName", roundName, "Rounds"))
					databaseManager.insertRounds(roundName);

				roundId=databaseManager.getRoundId(roundName);
			}

		}
			
		if(!databaseManager.tableExists("Associations")){
		databaseManager.createAssociationsTable();
		editAssociationsLb.setText("ማህደር ማሕበራት ተፈጢሩ");
		}
		if(editAssociationsANTf.getText().equals("")|| editAssociationsRNTf.getText().equals("")|| editAssociationsNMTf.getText().equals(""))
		{
			editAssociationsLb.setText("ክፍቲ ቦትታት ኣይተመልኡን ");
		return;
		}
		
		if(!editAssociationsRNTf.getText().matches("^(-?)\\d+$")){
			editAssociationsLb.setText("መመዝገቢ ቑፅሪ ካ ባዶ ንላዕሊ ክኸውን ኣለዎ");
		return;
		}
		if(!editAssociationsNMTf.getText().matches("^(-?)\\d+$")){
			editAssociationsLb.setText("በዝሒ ኣባላት ካብ ባዶ ን ላዕሊ ክኸውን ኣለዎ");
		return;
		}
		
		 String associationName=editAssociationsANTf.getText();
		 associationName=associationName.replaceAll("\\s+","_");//removes all white spaces from association name in order to create table
		this.associationName="Round_"+roundId+"___"+this.associationName;
		 associationName="Round_"+roundId+"___"+associationName;

		 int registrationNumber=Integer.valueOf(editAssociationsRNTf.getText());
		 int numberOfMembers=Integer.valueOf(editAssociationsNMTf.getText());
		
		if(!(registrationNumber>0)){
			editAssociationsLb.setText("መመዝገቢ ቑፅሪ ካብ ባዶ ን ላዕሊ ክኸውን ኣለዎ");
		return;
		}
		if(!(numberOfMembers>0)){
			editAssociationsLb.setText("በዝሒ ኣባላት ካብ ባዶ ን ላዕሊ ክኸውን ኣለዎ");
		return;
		}
		
		if(!databaseManager.isUnique("associationName", associationName, "Associations", roundId) && !this.associationName.equalsIgnoreCase(associationName) )
		{
			new CommonMethodsClass().showNotificationMessages("ሽም ማሕበር እብ ዙር "+roundName+" ተደጊሙ");
			return;
		}
		if(!databaseManager.isUnique("registrationNumber", registrationNumber, "Associations", roundId) && this.registrationNumber!=registrationNumber)
		{
		editAssociationsLb.setText("መመዝገቢ ቑፅሪ ተደጊሙ");
		return;
		}
		//end of validation	

		if(!associationName.equalsIgnoreCase(this.associationName)){//rename table as change appears
			 if(!databaseManager.renameTable(this.associationName, associationName))
			 {
			this.associationName="";
				return;
			 }
		 }
		
		 int associationId=databaseManager.getAssociationId(this.associationName, databaseManager.getRoundId(this.registrationRoundName));
		 databaseManager.setAssociationName(associationName, associationId);
		databaseManager.setAssociationRegistrationRound(roundId, associationId);
		databaseManager.setAssociationRegistrationNumber(registrationNumber, associationId);
		databaseManager.setAssociationNumberOfMembers(numberOfMembers, associationId);

		editAssociationsLb.setText("ሓድ ማሕበር ተስተኻኺሉ");
		//reset TextFields
		this.associationName="";
		editAssociationsANTf.setText("");
		editAssociationsRNTf.setText("");
		editAssociationsNMTf.setText("");
		
		initializerString="list";
		commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
	
	}

	@FXML ComboBox memberRoleCb;
	@FXML
	public void memberRoleCbAction(){
		ObservableList<String> lists=FXCollections.observableArrayList(databaseManager.getAllRoleTypes());
		memberRoleCb.setItems(lists);	
	}
	
		//getAssociations method to fill the table
	public ObservableList<Association> getAssociations(){
		if(!databaseManager.tableExists("Associations"))
		{
			CommonMethodsClass.showNotificationMessages("ዝተመዝገቡ ማሕበራት የለውን");
			return null;
		}
		ObservableList<Association> associations=FXCollections.observableArrayList();
	Integer rowNo=1;
	ArrayList<String> associationNames=databaseManager.getAllStrings("associationName", "Associations");
	ArrayList<Integer> registrationNumbers=databaseManager.getAllIntegers("registrationNumber", "Associations");
	ArrayList<String> nameOfChairman=databaseManager.getAllStrings("nameOfChairman", "Associations");
	ArrayList<String> phoneNumber=databaseManager.getAllStrings("phoneNumber", "Associations");
	//ArrayList<Integer> numberOfRegisteredMembers=databaseManager.getAllRegisteredNumber("associationName", "Associations");
	ArrayList<Integer> roundIds=databaseManager.getAllIntegers("roundId", "Associations");
	ArrayList<String> tableNames=databaseManager.getAllAssociationNames();

		for(int i=0;i<associationNames.size(); i++){
			String roundName2=databaseManager.getRoundName(roundIds.get(i));
		if(!roundName2.equalsIgnoreCase(roundName) && started)
			continue;
	int registerednumber=databaseManager.getRegisteredNumber(tableNames.get(i));
String associationName=(String) associationNames.get(i).subSequence((9+String.valueOf(roundIds.get(i)).length()), associationNames.get(i).length());
	associations.add(new Association(rowNo, associationName, registrationNumbers.get(i), nameOfChairman.get(i), phoneNumber.get(i), roundName2));
			rowNo++;
		}
//		*/
		return associations;
	}


	//Register Association Members
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML TextField registerAssociationMemberFNTf;
	@FXML TextField registerAssociationMemberLNTf;
	@FXML TextField registerAssociationMemberMIDTf;
	@FXML TextField registerAssociationMemberPNTf;
	@FXML TextField registerAssociationMemberAddTf;
	@FXML Label memberMegistrationStatusLb;
	@FXML
	public void registerAssociationMembersAction(){

		if(!databaseManager.tableExists(String.format("%s", associationName))){
			databaseManager.createAssociationMembersTable(associationName);
			}

		if(registerAssociationMemberFNTf.getText().equals("")|| registerAssociationMemberLNTf.getText().equals("")|| registerAssociationMemberMIDTf.getText().equals("")|| registerAssociationMemberPNTf.getText().equals("")|| registerAssociationMemberAddTf.getText().equals(""))
			{
			memberMegistrationStatusLb.setText("ክፍቲ ቦታታት ኣይተመልኡን ");
			return;
			}

		String firstName=registerAssociationMemberFNTf.getText();
		String lastName=registerAssociationMemberLNTf.getText();
		String memberId=registerAssociationMemberMIDTf.getText();
			String phone=registerAssociationMemberPNTf.getText();
			String address=registerAssociationMemberAddTf.getText();
			int role=memberRoleCb.getSelectionModel().getSelectedIndex();

			if(!(role>=0)){
				memberMegistrationStatusLb.setText("ሓላፍነት ኣባል ኣይመረፁን");
			return;
			}
			role=role+1;//as starts from 0
			if(!databaseManager.isUnique("memberId", memberId, String.format("%s", associationName)))//associationName is TABLE NAME
			{
				memberMegistrationStatusLb.setText("መለለይ ኣባል ተደጊሙ");
			return;
			}
			//end of validation	

			databaseManager.insertAssociationMembers(associationName, firstName, lastName, memberId, phone, address, role);
			memberMegistrationStatusLb.setText("ኣባል ብትክክል ተመዝጊቡ");
					
			//reset TextFields
			associationName="";
			registerAssociationMemberFNTf.setText("");
			registerAssociationMemberLNTf.setText("");
			registerAssociationMemberMIDTf.setText("");
			registerAssociationMemberPNTf.setText("");
			registerAssociationMemberAddTf.setText("");
			memberRoleCb.getSelectionModel().clearSelection();
		
initializerString="list";
			commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");

	}

	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////
//NB: BEGIN  METHODS EXIST AT BOTH cONTROLLERS associations and Contructors
/////////////////////////////////////////////////////////////////////////////////////////////////////	
//RADIO BUTTON EVENTS
/////////////////////////////////////////////////////////////////////////////////////////////////////
public void registerAssociationsRbAction(){
	initializerString="registerAssociations";
	commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
}
public void registerContructorsRbAction(){
	ContructorsController.initializerString="registerContructors";
	commonMethodsClass.loadExistingWindowFxmlPage("registerContructors.fxml");
}
@FXML ComboBox otherRegistrationTypesCb;
public void otherRegistrationTypesCbSetValues(){
	ObservableList<String> otherRegistrationTypes=FXCollections.observableArrayList("ደረጃ ኮንታክተራት ", "ዓይነት ሓላፍነት ኣባላት");
	otherRegistrationTypesCb.setItems(otherRegistrationTypes);
}
public void otherRegistrationTypesCbAction(){
	if(otherRegistrationTypesCb.getSelectionModel().getSelectedIndex()==0){
		initializerString="createLevels";
		commonMethodsClass.loadExistingWindowFxmlPage("CreateLevels.fxml");
	}
	if(otherRegistrationTypesCb.getSelectionModel().getSelectedIndex()==1){
		initializerString="createRoles";
		commonMethodsClass.loadExistingWindowFxmlPage("CreateRoles.fxml");
	}
	}

public void registerAssociationsBackBtnAction(){
	initializerString="";
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}
public void registerAssociationMembersBackBtnAction(){
	initializerString="list";
	commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
}

public void addAssociationsBackBtnAction(){
	initializerString="list";
	commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
}

public void createLevelsBackBtnAction(){
	initializerString="registerAssociations";
if(ContructorsController.backToCreateContructors){
		commonMethodsClass.loadExistingWindowFxmlPage("registerContructors.fxml");	
		ContructorsController.backToCreateContructors=false;
	}
	commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
}

public void createRolesBackBtnAction(){
	initializerString="registerAssociations";
commonMethodsClass.loadExistingWindowFxmlPage("registerAssociations.fxml");
}

public void editAssociationsBackBtnAction(){
	initializerString="list";
	commonMethodsClass.loadExistingWindowFxmlPage("manageAssociations.fxml");
}
public void manageAssociationsBackBtnAction(){
	initializerString="";
	commonMethodsClass.loadExistingWindowFxmlPage("layout.fxml");
}

@FXML ComboBox registerContructorsRACb;
@FXML
public void contructorRankCbAction(){
	ObservableList<String> lists=FXCollections.observableArrayList(databaseManager.getAllLevelNames());
	//if(editContructorsButtonClicked){	
	//editContructorsRACb.setItems(lists);
	//editContructorsButtonClicked=false;
	//}
	//else
		registerContructorsRACb.setItems(lists);

}

@FXML TextField createRoleTypeTf;
@FXML Label createRolesLb;

public void createRolesButtonAction(){
	initializerString="createRoles";
	String roleType=createRoleTypeTf.getText();
	if(roleType.equals("")){
		createRolesLb.setText("ክፍቲ ቦታ ኣይተመልኣን");
		return;
	}
	
	if(!databaseManager.isUnique("roleType", roleType, "Roles")){
		createRolesLb.setText("ሓላፍነት ኣባል ተደጊሙ");
		return;
	}
	
	if(!databaseManager.tableExists("Roles"))
		databaseManager.createRolesTable();
	databaseManager.insertRoles(roleType);
	
	createRolesLb.setText("ሓላፍነት ኣባል ተፈጢሩ");
	createRoleTypeTf.setText("");
	
}


@FXML TextField createLevesTf;
@FXML Label createLevelsLb;
public void createLevelsButtonAction(){
	initializerString="createLevels";
	String levelName=createLevesTf.getText();
	if(levelName.equals("")){
		createLevelsLb.setText("ክፍቲ ቦታ ኣይተመልኣን");
		return;
	}
	if(!databaseManager.isUnique("levelName", levelName, "Levels")){
		createLevelsLb.setText("ደረጃ ኮንትራክተር ተፈጢሩ");
		return;
	}
	
	if(!databaseManager.tableExists("Levels"))
		databaseManager.createLevelsTable();
	databaseManager.insertLevels(levelName);
	
	createLevesTf.setText("");
	createLevelsLb.setText("ደረጃ ኮንትራክተር ተፈጢሩ");
}
////////////////////////////////////////////////////////////////////////////////////////////////////
//END  OF COMMON
/////////////////////////////////////////////////////////////////////////////////////////////////////



public void manageAssociationsPrintBtnAction(){
	if(!started)
	new CurrentPrinter().print(this.associationsTable, "", "ዝርዝር ዝተመዝገቡ  ማሕበራት");
	else
	new CurrentPrinter().print(this.associationsTable, roundName, "ዝርዝር ዝተመዝገቡ ማሕበራት ");

	this.roundName=null;
	this.manageAssociations();
}

}






