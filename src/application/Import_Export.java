package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
 
public class Import_Export {
 public static DatabaseManager databaseManager=new DatabaseManager();
 private static int numberOf_Block_Rows=347, num_cols=8;
 public static List<String> blocksList=new ArrayList<String>();
 
    public static void main(String[] args)
    {
        insertExcelData();    
        for(int i=0; i<344; i++)
             System.out.println(blocksList.get(i));
}

    public static void insertExcelData(){
        //insert blocks data    
        getBlocksData();
            try{
//Import Data from Excel File
        String[][] data = importData("JARS/input.xls", 0);
  //Export Data to Excel File
        exportData("JARS/output.xls","i+j",data);
 
        //Display Data from File
        for(int i = 4; i < data.length; i++)
        {
            for(int j = 0; j < num_cols; j++){
                
                if(j>=2 && j<=4)
                    continue;
            }
 
            System.out.println();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
         public static void getBlocksData(){
       try{
        String[][] data = importBlocksData("JARS/input.xls", 1);
  //Export Data to Excel File
        //Display Data from File
        for(int i = 3; i < data.length; i++)
        {
            if(i>=numberOf_Block_Rows)
                break;
           for(int j = 0; j < 1; j++){
                blocksList.add(data[i][j]);
            }
 
            System.out.println();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
    public static void exportData(String fileName, String tabName, String[][] data) throws FileNotFoundException, IOException
    {
        //Create new workbook and tab
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(fileName);
        Sheet sheet = wb.createSheet(tabName);
 
        //Create 2D Cell Array
        Row[] row = new Row[data.length];
        Cell[][] cell = new Cell[row.length][];
 
        //Define and Assign Cell Data from Given
    databaseManager.deleteTableContents("associations");
        for(int i = 4; i < row.length; i ++)
        {
            row[i] = sheet.createRow(i);
            cell[i] = new Cell[data[i].length];
 
            for(int j = 0; j < num_cols; j ++)
            {
                if(j>=2 && j<=4)
                    continue;
                cell[i][j] = row[i].createCell(j);
                cell[i][j].setCellValue(data[i][j]);
            }
    
    int roundId=databaseManager.getRoundId(cell[i][num_cols-1].getStringCellValue());
    String associationName=cell[i][1].getStringCellValue();
    associationName="Round_"+roundId+"___"+associationName;
    String registrationNumber=cell[i][0].getStringCellValue();
    String nameOfChairman=cell[i][5].getStringCellValue();
    String phoneNumber=cell[i][6].getStringCellValue();
    int numberOfMembers=20;
    if(cell[i][0].getStringCellValue().equals("870") || "".equals(registrationNumber))
            continue;
    //databaseManager.insertAssociations(roundId, associationName, registrationNumber, numberOfMembers);
    databaseManager.insertAssociations(roundId, associationName, registrationNumber, nameOfChairman, phoneNumber);
 
        }
 
        //Export Data
        wb.write(fileOut);
        fileOut.close();
 
    }
 
    public static String[][] importBlocksData(String fileName, int tabNumber) throws FileNotFoundException, IOException, InvalidFormatException
    {
        String[][] data;
 
        //Create Workbook from Existing File
        InputStream fileIn = new FileInputStream(fileName);
        Workbook wb = WorkbookFactory.create(fileIn);
        Sheet sheet = wb.getSheetAt(tabNumber);
 
        //Define Data & Row Array and adjust from Zero Base Numer
        data = new String[sheet.getLastRowNum()+1][];
        Row[] row = new Row[sheet.getLastRowNum()+1];
        Cell[][] cell = new Cell[row.length][];
 
        //Transfer Cell Data to Local Variable
        for(int i = 3; i < row.length; i++)
        {
            if(i>=numberOf_Block_Rows)
                break;
            
            row[i] = sheet.getRow(i);
        if(row[i]==null)
            continue;
 
            //Note that cell number is not Zero Based
            cell[i] = new Cell[row[i].getLastCellNum()];
            data[i] = new String[row[i].getLastCellNum()];
 
            //only the first col
            for(int j = 0; j < 1; j++)
            {
           cell[i][j] = row[i].getCell(j);
           if(cell[i][j]==null)
               continue;
            cell[i][j].setCellType(Cell.CELL_TYPE_STRING);
                data[i][j] = cell[i][j].getStringCellValue();
            }
 
        }
 
        fileIn.close();
        return data;
    }
 
    public static String[][] importData(String fileName, int tabNumber) throws FileNotFoundException, IOException, InvalidFormatException
    {
 
        String[][] data;
 
        //Create Workbook from Existing File
        InputStream fileIn = new FileInputStream(fileName);
        Workbook wb = WorkbookFactory.create(fileIn);
        Sheet sheet = wb.getSheetAt(tabNumber);
 
        //Define Data & Row Array and adjust from Zero Base Numer
        data = new String[sheet.getLastRowNum()+1][];
        Row[] row = new Row[sheet.getLastRowNum()+1];
        Cell[][] cell = new Cell[row.length][];
 
        //Transfer Cell Data to Local Variable
        for(int i = 4; i < row.length; i++)
        {
            row[i] = sheet.getRow(i);
 
            //Note that cell number is not Zero Based
            cell[i] = new Cell[row[i].getLastCellNum()];
            data[i] = new String[row[i].getLastCellNum()];
 
            for(int j = 0; j < num_cols; j++)
            {
                if(j>=2 && j<=4)
                    continue;
           cell[i][j] = row[i].getCell(j);
            cell[i][j].setCellType(Cell.CELL_TYPE_STRING);
        //skip row 870
        if(cell[i][0].getStringCellValue().equals("870"))
            continue;
        if(j==num_cols-1)
            data[i][j]="ዙር 2";
        else{
            //data[i][j] = cell[i][j].getNumericCellValue();
                data[i][j] = cell[i][j].getStringCellValue();
        }
            }
 
        }
 
        fileIn.close();
        return data;
    }
 
}
