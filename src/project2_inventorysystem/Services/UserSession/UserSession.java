/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Services.UserSession;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class UserSession {
  
  private static UserSession instance = null;
  private  int userID = 0;
  private  String username = null;
  
  
  public void createSession(ResultSet userInfo){
    try {
      this.userID = userInfo.getInt("userID");
      this.username = userInfo.getString("username");
      
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
  
  public  static UserSession getInstance(){
    if(instance == null) {
      instance = new UserSession();
    }
    return instance;
  }
  
  public int getUserID(){
    return userID;
  }
}
