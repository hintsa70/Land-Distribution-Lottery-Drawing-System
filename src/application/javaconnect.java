package application;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tedros
 */
public class javaconnect {
    Connection conn= null;
 //   DatabaseManager databaseManager = new DatabaseManager();
    public static Connection ConnecrDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DatabaseManager().connection;//riverManager.getConnection("jdbc:mysql://localhost/houselotory?characterEncoding=UTF-8","root","");
            return conn;
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e); 
          return null;
        }
        
    }
}
