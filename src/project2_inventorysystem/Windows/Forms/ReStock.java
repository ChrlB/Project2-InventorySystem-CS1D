/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows.Forms;

import java.awt.Color;
import javax.swing.*;
import java.sql.*;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.SpinnerBuilder;
import project2_inventorysystem.Windows.Product;
import project2_inventorysystem.Windows.User;

/**
 *
 * @author user
 */
public class ReStock extends JFrame{
  int productID;
  Connection conn;
  Product parent;
  
  ButtonBuilder confirm_btn, 
                cancel_btn;
  
  SpinnerBuilder restock_spinner;
  LabelBuilder stock_label;
  
  String sql;
  PreparedStatement pstmt;
  
  public ReStock(Product parent, int productID, Connection conn){
    this.productID = productID;
    this.conn = conn;
    this.parent = parent;
    
    
    stock_label = new LabelBuilder("Add Stock: ",30,50,100,50,15);
    
    restock_spinner = new SpinnerBuilder(true);
    restock_spinner.setMax(1000);
    restock_spinner.setBounds(130,50,320,50);
    
    confirm_btn = new ButtonBuilder("CONFIRM",30, 130, 200, 50,15);
    cancel_btn = new ButtonBuilder("CANCEL",250, 130, 200, 50,15);
    
    cancel_btn.addActionListener((a) -> {closeWindow();});
    confirm_btn.addActionListener((a) -> { addStock();});
    
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setLayout(null);
    this.setTitle("RESTOCK");
    this.setSize(500,270);
    this.getContentPane().setBackground(new Color(0x293A3E));
    this.setResizable(false);
    
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        closeWindow();
      }
    });
    this.add(stock_label);
    this.add(restock_spinner);
    this.add(confirm_btn);
    this.add(cancel_btn);
    
    this.setVisible(true);
  }
  
  void closeWindow(){
    parent.setEnabled(true);
    this.dispose();
  }
  
  void addStock(){
    try{
      int stock_to_add = (int)restock_spinner.getValue();
      sql = """
        UPDATE products
        SET stockQuantity = stockQuantity + ?
        WHERE productID = ?;
      """;
      
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, stock_to_add);
      pstmt.setInt(2, productID);
      
      int rowsAffected = pstmt.executeUpdate();
      
      if (rowsAffected > 0) {
          JOptionPane.showMessageDialog(null,
                  "Stock successfully added.",
                  "Success", JOptionPane.INFORMATION_MESSAGE);
          parent.refreshTable();
          closeWindow();
      } else {
          JOptionPane.showMessageDialog(null,
                  "No Stock is added.",
                  "Failed", JOptionPane.WARNING_MESSAGE);
          closeWindow();
      }
      
    }catch(Exception ex){
      ex.printStackTrace();
    }
    
    
  }
}
