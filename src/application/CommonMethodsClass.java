package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CommonMethodsClass {
	static Stage newCreatedStage=new Stage();

	
	//FXML LOADER FUNCTIONs
	public void loadExistingWindowFxmlPage(String fxmlName){
		 try {
			 
			    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Pages/"+fxmlName));
			            Parent root = (Parent) fxmlLoader.load();
			          Scene newScene=new Scene(root, 1360, 700);
			          newCreatedStage.setScene(newScene);
			          newCreatedStage.show();
			          
			    } catch(Exception e) {
			       e.printStackTrace();
			      }
	}
	public void loadHomepage(String fxmlName){
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Pages/"+fxmlName));
			            Parent root = (Parent) fxmlLoader.load();
			          Scene newScene=new Scene(root, 900, 650);
			          Main.homepage.setScene(newScene);
			          Main.homepage.show();
			          
			    } catch(Exception e) {
			       e.printStackTrace();
			      }
	}

		public void loadNewWindowFxmlPage(String fxmlName){
			 try {
				    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Pages/"+fxmlName));
				            Parent root = (Parent) fxmlLoader.load();
				            newCreatedStage.setScene(new Scene(root));  
				            newCreatedStage.setResizable(false);
				            newCreatedStage.showAndWait();
				    } catch(Exception e) {
				       e.printStackTrace();
				      }
		}
		public boolean loadNewWindowInitModalFxmlPage(String fxmlName){
			 try {
				    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Pages/"+fxmlName));
				            Parent root = (Parent) fxmlLoader.load();
				            newCreatedStage.initModality(Modality.APPLICATION_MODAL);
				            newCreatedStage.setScene(new Scene(root));  
				            newCreatedStage.setResizable(false);
				            if(!newCreatedStage.isShowing())
				            	newCreatedStage.showAndWait();
				    } catch(Exception e) {
				       e.printStackTrace();
				       return false;
				      }
			 return true;
		}
	
		public static boolean showNotificationMessages(String message){
			 try {
				  Stage messageStage=new Stage();
				  messageStage.sizeToScene();
				 // messageStage.setWidth(400);
				 // messageStage.setHeight(300);
				  messageStage.initModality(Modality.APPLICATION_MODAL);
				  Button confirmBtn=new Button();
				  confirmBtn.setOnAction(e->{
					  messageStage.close();
				  });
				  confirmBtn.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
				  confirmBtn.setText("እሺ");
				 
				  Label messageLb=new Label();
				  messageLb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
				  messageLb.setText(message);
				  VBox vBox=new VBox();
				  vBox.getChildren().addAll(messageLb, new Label(), new Label(), new Label(), confirmBtn);
				  messageStage.setScene(new Scene(vBox));  
				  messageStage.setResizable(false);
				            if(!messageStage.isShowing())
				            	messageStage.showAndWait();
				    } catch(Exception e) {
				       e.printStackTrace();
				       return false;
				      }
			 return true;
		}
		
		static boolean tof=false;
		public static boolean showConfirmMessages(String message){
			 try {
				  Stage messageStage=new Stage();
				  messageStage.sizeToScene();
				 // messageStage.setWidth(400);
				 // messageStage.setHeight(300);
				  messageStage.initModality(Modality.APPLICATION_MODAL);
				  Button yesBtn=new Button();
				  yesBtn.setOnAction(e->{
					  messageStage.close();
					  tof=true;
				  });
				  
				  Button noBtn=new Button();
				  noBtn.setOnAction(e->{
					  messageStage.close();
					  tof=false;
				  });
				  yesBtn.setStyle("-fx-font: 20px \"Geez Able\"; -fx-border-color:red;");//font AMHARIC 
				 // yesBtn.setStyle(""); 
				  yesBtn.setText("እወ ሰርዝ");
				  
				  noBtn.setStyle("-fx-font: 20px \"Geez Able\"; -fx-border-color: blue;");//font AMHARIC 
				  noBtn.setText("ኣይ ተመለስ");
				 
				  Label messageLb=new Label();
				  messageLb.setStyle("-fx-font: 20px \"Geez Able\";");//font AMHARIC 
				  messageLb.setText(message);
				  VBox vBox=new VBox();
				  vBox.getChildren().addAll(messageLb, new Label(), new Label(), new Label(), new HBox(yesBtn, new Label("\t\t"), noBtn));
				  messageStage.setScene(new Scene(vBox));  
				  messageStage.setResizable(false);
				            if(!messageStage.isShowing())
				            	messageStage.showAndWait();
				    } catch(Exception e) {
				       e.printStackTrace();
				      }
			 return tof;
		}
}
