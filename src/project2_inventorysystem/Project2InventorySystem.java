/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project2_inventorysystem;
import project2_inventorysystem.Windows.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.*;
/**
 *
 * @author 
 * Bonaobra
 * Palado, Syrill John
 * Villanueva, Juan Victor
 */
public class Project2InventorySystem {

  /**
   * @param args the command line arguments
   */
  
  public static void main(String[] args) {
    
    try{
      Properties prop = new Properties();
      prop.load(new FileInputStream("db.properties"));

      String url = prop.getProperty("DB_URL");
      String user = prop.getProperty("DB_USER");
      String pass = prop.getProperty("DB_PASS");
      
      Connection conn = DriverManager.getConnection( url, user, pass);
      
      new Login(conn);
//      new Dashboard();
      System.out.println(conn);
      
    
    }catch(Exception ex){
      System.out.print(ex);
    }
    //new Dashboard();
  }
  
}
