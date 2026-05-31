/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project2_inventorysystem;
import project2_inventorysystem.UI.Windows.Login;
import project2_inventorysystem.Services.DBConnection.DBConnection;
/**
 *
 * @author user
 * Bonaobra, john chrl
 * Palado, Syrill John
 * Villanueva, Juan Victor 
 */
public class Project2InventorySystem {

  /**
   * @param args the command line arguments
   */
  
  public static void main(String[] args) {
    try{
      
      DBConnection.getInstance();
      new Login();
    
    }catch(Exception ex){
      ex.printStackTrace();
    }
    //new Dashboard();
  }
  
}
