/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.DAO;
import java.sql.Connection;
import project2_inventorysystem.Services.DBConnection;
/**
 *
 * @author user
 */
public class CategoryDAO {
  Connection conn;
  
  public CategoryDAO(){
    this.conn = DBConnection.getInstance().getDBConnection();
  }
  
  
  
}
