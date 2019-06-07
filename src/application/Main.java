package application;
	
import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements Initializable{
	static DatabaseManager databaseManager=new DatabaseManager();
	public static Stage primaryStage=new Stage();
	public static Stage homepage=new Stage();

	public Main(){
	 databaseManager=new DatabaseManager();
	 primaryStage=new Stage();
	 homepage.hide();
         
         Import_Export.insertExcelData();
	}
	@Override
	public void start(Stage primaryStage) { 
		
		Platform.runLater(new Runnable(){
			public void run(){
				Platform.setImplicitExit(true); 
			}
		});
		
		this.primaryStage=primaryStage;
		try {
		Parent root=null, root2=null;
		Controller.initializerString="login";
		root=FXMLLoader.load(getClass().getResource("FXML_Pages/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.primaryStage.setScene(scene);
			this.primaryStage.setResizable(true);
			this.primaryStage.setTitle("ናይ መንበሪ ኣባይቲ ዕፃ መውፅኢ ሲስተም");
			this.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	if(databaseManager.CONNECTED)//checks for database connection before loading
		launch(args);
	else
		JOptionPane.showMessageDialog(null, "ምስ ዳታቤዝ ምርኻብ ኣይተኽኣለን");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUserAgentStylesheet("application.css");
		
	}
}
