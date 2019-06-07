package application;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

import javafx.beans.binding.Bindings;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class CurrentPrinter {

public void print(TableView winnersTable, String roundName, String title){
	  javax.swing.UIManager.put("Printer.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
       javax.swing.UIManager.put("Printer.messageFont", new Font("Nyala", Font.PLAIN, 14));
       Printer printer = Printer.getDefaultPrinter(); //get the default printer

	    javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT); //create a pagelayout.  I used Paper.NA_LETTER for a standard 8.5 x 11 in page.
	    PrinterJob job = PrinterJob.createPrinterJob();//create a printer job

	    if(job.showPrintDialog(Main.primaryStage.getScene().getWindow()))// this is very useful it allows you to save the file as a pdf instead using all of your printer's paper. A dialog box pops up, allowing you to change the "name" option from your default printer to Adobe pdf. 
	    {
	        double pagePrintableWidth = pageLayout.getPrintableWidth(); //this should be 8.5 inches for this page layout.
	        double pagePrintableHeight = pageLayout.getPrintableHeight();// this should be 11 inches for this page layout.


			winnersTable.prefHeightProperty().bind(Bindings.size(winnersTable.getItems()).multiply(35));// If your cells' rows are variable size you add the .multiply and play with the input value until your output is close to what you want. If your cells' rows are the same height, I think you can use .multiply(1). This changes the height of your tableView to show all rows in the table.
	        winnersTable.minHeightProperty().bind(winnersTable.prefHeightProperty());//You can probably play with this to see if it's really needed.  Comment it out to find out.
	        winnersTable.maxHeightProperty().bind(winnersTable.prefHeightProperty());//You can probably play with this to see if it' really needed.  Comment it out to find out.

	        double scaleX = pagePrintableWidth / winnersTable.getBoundsInParent().getWidth();//scaling down so that the printing width fits within the paper's width bound.
	        double scaleY = scaleX; //scaling the height using the same scale as the width. This allows the writing and the images to maintain their scale, or not look skewed.
	        double localScale = scaleX; //not really needed since everything is scaled down at the same ratio. scaleX is used thoughout the program to scale the print out.

	        double numberOfPages = Math.ceil((winnersTable.getPrefHeight() * localScale) / pagePrintableHeight);//used to figure out the number of pages that will be printed.



	        //System.out.println("pref Height: " + winnersTable.getPrefHeight());
	        //System.out.println("number of pages: " + numberOfPages);


	        winnersTable.getTransforms().add(new Scale(scaleX, (scaleY)));//scales the printing. Allowing the width to say within the papers width, and scales the height to do away with skewed letters and images.
	        winnersTable.getTransforms().add(new Translate(0, 0));// starts the first print at the top left corner of the image that needs to be printed

	        //Since the height of what needs to be printed is longer than the paper's heights we use gridTransfrom to only select the part to be printed for a given page.
	        Translate gridTransform = new Translate();
	        winnersTable.getTransforms().add(gridTransform);

	        //now we loop though the image that needs to be printed and we only print a subimage of the full image.
	        //for example: In the first loop we only pint the printable image from the top down to the height of a standard piece of paper. Then we print starting from were the last printed page ended down to the height of the next page. This happens until all of the pages are printed. 
	        // first page prints from 0 height to -11 inches height, Second page prints from -11 inches height to -22 inches height, etc. 
	    	Image header=new Image("images/printHeader.jpg");
			Label headerLb=new Label();
			headerLb.setGraphic(new ImageView(header));
		
	        VBox page=new VBox();
	        Label titleLb=new Label();
	        titleLb.setStyle("-fx-font: 18px \"Geez Able\"; -fx-background-color: gray");//font AMHARIC 
	        titleLb.setText("  "+roundName+"  "+title);
	        titleLb.setPrefWidth(1300);
	        
            page.getChildren().addAll(headerLb, new Label(), titleLb, new Label(), winnersTable);
    		
	        for(int i = 0; i < numberOfPages; i++)
	        {
	            gridTransform.setY(-i * (pagePrintableHeight / localScale));
	          	winnersTable.setStyle("-fx-font: 18px \"Nyala\";");//font AMHARIC 
	    			job.printPage(pageLayout, page);
	        }

	        job.endJob();

	    }
}



public void print(TableView winnersTable, String roundName){
	  javax.swing.UIManager.put("Printer.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
       javax.swing.UIManager.put("Printer.messageFont", new Font("Nyala", Font.PLAIN, 14));
		
       Printer printer = Printer.getDefaultPrinter(); //get the default printer

	    javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT); //create a pagelayout.  I used Paper.NA_LETTER for a standard 8.5 x 11 in page.
	    PrinterJob job = PrinterJob.createPrinterJob();//create a printer job

	    if(job.showPrintDialog(Main.primaryStage.getScene().getWindow()))// this is very useful it allows you to save the file as a pdf instead using all of your printer's paper. A dialog box pops up, allowing you to change the "name" option from your default printer to Adobe pdf. 
	    {
	        double pagePrintableWidth = pageLayout.getPrintableWidth(); //this should be 8.5 inches for this page layout.
	        double pagePrintableHeight = pageLayout.getPrintableHeight();// this should be 11 inches for this page layout.


			winnersTable.prefHeightProperty().bind(Bindings.size(winnersTable.getItems()).multiply(35));// If your cells' rows are variable size you add the .multiply and play with the input value until your output is close to what you want. If your cells' rows are the same height, I think you can use .multiply(1). This changes the height of your tableView to show all rows in the table.
	        winnersTable.minHeightProperty().bind(winnersTable.prefHeightProperty());//You can probably play with this to see if it's really needed.  Comment it out to find out.
	        winnersTable.maxHeightProperty().bind(winnersTable.prefHeightProperty());//You can probably play with this to see if it' really needed.  Comment it out to find out.

	        double scaleX = pagePrintableWidth / winnersTable.getBoundsInParent().getWidth();//scaling down so that the printing width fits within the paper's width bound.
	        double scaleY = scaleX; //scaling the height using the same scale as the width. This allows the writing and the images to maintain their scale, or not look skewed.
	        double localScale = scaleX; //not really needed since everything is scaled down at the same ratio. scaleX is used thoughout the program to scale the print out.

	        double numberOfPages = Math.ceil((winnersTable.getPrefHeight() * localScale) / pagePrintableHeight);//used to figure out the number of pages that will be printed.



	        //System.out.println("pref Height: " + winnersTable.getPrefHeight());
	        //System.out.println("number of pages: " + numberOfPages);


	        winnersTable.getTransforms().add(new Scale(scaleX, (scaleY)));//scales the printing. Allowing the width to say within the papers width, and scales the height to do away with skewed letters and images.
	        winnersTable.getTransforms().add(new Translate(0, 0));// starts the first print at the top left corner of the image that needs to be printed

	        //Since the height of what needs to be printed is longer than the paper's heights we use gridTransfrom to only select the part to be printed for a given page.
	        Translate gridTransform = new Translate();
	        winnersTable.getTransforms().add(gridTransform);

	        //now we loop though the image that needs to be printed and we only print a subimage of the full image.
	        //for example: In the first loop we only pint the printable image from the top down to the height of a standard piece of paper. Then we print starting from were the last printed page ended down to the height of the next page. This happens until all of the pages are printed. 
	        // first page prints from 0 height to -11 inches height, Second page prints from -11 inches height to -22 inches height, etc. 
	    	Image header=new Image("images/printHeader.jpg");
			Label headerLb=new Label();
			headerLb.setGraphic(new ImageView(header));
		
	        VBox page=new VBox();
	        Label titleLb=new Label();
	        titleLb.setStyle("-fx-font: 18px \"Geez Able\"; -fx-background-color: gray");//font AMHARIC 
	        titleLb.setText("    ኣብ "+roundName+"  ዝወፀ ዕፃ መሰረት ብምግባር ብሲስተም ዝተዳለወ ሪፖርት");
	        titleLb.setPrefWidth(1300);
Label bottomLabel=new Label();
bottomLabel.setStyle("-fx-font: 18px \"Geez Able\";");//font AMHARIC 
bottomLabel.setText("ሽምን ፊርማን ተዓዘብቲ\nታ.ቑ \t ሽም \t\t ፊርማ ");
page.getChildren().addAll(headerLb, new Label(), titleLb, new Label(), winnersTable);    
page.getChildren().add(bottomLabel);
	        for(int i = 0; i < numberOfPages; i++)
	        {
	            gridTransform.setY(-i * (pagePrintableHeight / localScale));
	          	winnersTable.setStyle("-fx-font: 18px \"Nyala\";");//font AMHARIC 
	    			job.printPage(pageLayout, page);
	        }

	        job.endJob();

	    }
  
}

}
