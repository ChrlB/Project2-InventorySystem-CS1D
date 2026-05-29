/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.DAO;
import java.sql.Connection;
/**
 *
 * @author user
 */
public class OrderDataAccessObject {
  Connection conn;
  
  public OrderDataAccessObject(Connection conn){
    this.conn = conn;
  }
  
  
}
