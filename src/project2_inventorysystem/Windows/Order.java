/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import project2_inventorysystem.Windows.Dashboard;
import javax.swing.JFrame;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
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
  
  ComboBoxBuilder product_category_combobox;
  SpinnerBuilder  quantity_spinner;
  TextFieldBuilder customer_name_field,
                   productID_field;
          
  PreparedStatement pstmt;
  Connection conn;
  ResultSet rs,
            product_rs;
  String sql;
  
  Object[] selected_record;
  
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
        SELECT 
          DISTINCT categoryName
        FROM products 
        WHERE stockQuantity > 0; 
      """;

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      product_category_combobox = new ComboBoxBuilder("all",325,300,200,25);
      while(rs.next()){
        product_category_combobox.addItem(rs.getString("categoryName"));
      }
      product_category_combobox.addActionListener((e) -> filterTable());
      
      quantity_spinner = new SpinnerBuilder();
      quantity_spinner.setBounds(325,350,200,25);
      
      sql = """
        SELECT productID ,
               productName as name,
               categoryName as category,
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
      order_form_panel.add(product_category_combobox);
      order_form_panel.add(quantity_spinner);
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
  private void filterTable(){
    try {
      String selected_category = product_category_combobox.getSelectedItem().toString();
      
      if(selected_category.equals("all")){
        sql = """
        SELECT productID ,
               productName as name,
               categoryName as category,
               unitPrice as price
        FROM products
        WHERE stockQuantity > 0 ;
        """;
        pstmt = conn.prepareStatement(sql);
      }else{
        sql = """
        SELECT productID ,
               productName as name,
               categoryName as category,
               unitPrice as price
        FROM products
        WHERE stockQuantity > 0 and categoryName = ?;
        """;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, selected_category);
      }
      
      rs = pstmt.executeQuery();
      product_tbl.refreshTable(rs);
    } catch (SQLException ex) {
      Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  public void showSelectedRecord(){
    try{
      int row = product_tbl.getSelectedRow();

      if (row != -1) {

        selected_record = new Object[] {
          product_tbl.getValueAt(row, 0), 
          product_tbl.getValueAt(row, 1), 
          product_tbl.getValueAt(row, 2), 
          product_tbl.getValueAt(row, 3)  
        };

        //System.out.print(selected_record[0]);
      }
      
      sql = """
        SELECT 
            products.productID as productID,
            products.productName as productName,
            products.categoryName as categoryName,
            products.unitPrice as unitPrice,
            products.stockQuantity as stockQuantity,
            categories.unit as unit
        FROM products
        INNER JOIN categories 
            ON products.categoryName = categories.categoryName
        WHERE products.stockQuantity > 0 and products.productID = ?;
      """;
      
      //System.out.println("ID "+(int)selected_record[0]);
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, (int)selected_record[0]);
      product_rs = pstmt.executeQuery();
      
      if(product_rs.next()){
        quantity_spinner.setMax(product_rs.getInt("stockQuantity"));
        productID_field.setText(""+product_rs.getInt("productID"));
      }
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  
}
