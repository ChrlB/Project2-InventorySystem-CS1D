/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import project2_inventorysystem.Windows.Dashboard;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import project2_inventorysystem.Windows.MyComponents.*;

/**
 *
 * @author user
 */
public class Order extends JFrame{
  int user_ID;
  Header header ;
  JPanel order_form_panel;
  TableBuilder product_tbl;
  JScrollPane product_tbl_scrollpane;
  
  TextFieldBuilder customer_name_field,
                   productID_field;
          
  PreparedStatement pstmt;
  Connection conn;
  ResultSet rs;
  String sql;
  
  String[] selected_record;
  
  //int selected_row;
  
  
  public Order(int userID,Connection conn){
    try{
      this.conn = conn;
      user_ID = userID;

      header = new Header();

      order_form_panel = new JPanel();
      order_form_panel.setLayout(null);
      order_form_panel.setBounds(0,100,700,550);
      order_form_panel.setBackground(new Color(0XB58863));

      sql = """
        SELECT productID ,
               productName as name,
               unitPrice as price
        FROM products
        WHERE stockQuantity > 0;
        """;

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      product_tbl = new TableBuilder(rs);
      product_tbl.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          selected_record = product_tbl.getRecord();
          showSelectedRecord();
        }
      });
      
      product_tbl_scrollpane = new JScrollPane(product_tbl);
      product_tbl_scrollpane.setBounds(0,0,700,200);
      
      customer_name_field = new TextFieldBuilder(true,100,300,200,25);
      productID_field = new TextFieldBuilder(false,100,350,200,25);

      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setTitle("ORDER");
      this.setLayout(null);
      this.setResizable(false);
      this.setSize(1270,650);
      this.getContentPane().setBackground(new Color(0xD3C3B9));

      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          new Dashboard(user_ID,conn); // Call your method here
          dispose();
        }
      });
      
      order_form_panel.add(product_tbl_scrollpane);
      order_form_panel.add(customer_name_field);
      order_form_panel.add(productID_field);

      this.add(header);
      this.add(order_form_panel);
      this.setVisible(true);
    }catch(Exception ex){
      System.out.println(ex);
    }
  }
  
  public void showSelectedRecord(){
    try{
      productID_field.setText(selected_record[0]);
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
}
