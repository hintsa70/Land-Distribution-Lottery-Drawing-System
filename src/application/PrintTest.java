package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PrintTest extends Application {
TableView table=new TableView();
  private NodePrinter printer = new NodePrinter();

  private Node nodeToPrint;

  private Rectangle printRectangle;

  private PrinterJob job;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    job = PrinterJob.createPrinterJob();
   // print();
  }

  void print(TableView table) {
	  this.table=table;
	  printer.setScale(3);
	    printer.setPrintRectangle(getPrintRectangle());
	    boolean success = printer.print(job, true, getNodeToPrint());
	    if (success) {
	      job.endJob();
	    }
  }

  private Rectangle getPrintRectangle() {
    if (printRectangle == null) {
      printRectangle = new Rectangle(600, 500, null);
      printRectangle.setStroke(Color.BLACK);
    }
    return printRectangle;
  }

  private Node getNodeToPrint() {
    if (nodeToPrint == null) {

      Group group = new Group();
      group.getChildren().addAll(table);

      nodeToPrint = group;
    }
    return nodeToPrint;
  }
}