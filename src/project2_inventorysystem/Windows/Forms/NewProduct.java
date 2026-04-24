/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows.Forms;

import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.ComboBoxBuilder;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.SpinnerBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;
import project2_inventorysystem.Windows.Product;

/**
 *
 * @author user
 */
public class NewProduct extends JFrame {
  Product parent;
  Connection conn;
  
  String sql;
  ResultSet rs;
  PreparedStatement pstmt;
  
  LabelBuilder   product_name_field_label, 
                 category_name_field_label,
                 unit_price_field_label,
                 stock_quantity_spinner_label;
  
  SpinnerBuilder stock_quantity_spinner;
  
  TextFieldBuilder product_name_field, 
                   unit_price_field;
  
  ComboBoxBuilder product_category_combobox;
  
  ButtonBuilder confirm_btn, 
                cancel_btn;
  
  public NewProduct(Product parent,Connection conn){
    try{
      this.parent = parent;
      this.conn = conn;
      
      sql = """
        SELECT 
          categoryName
        FROM tbl_categories;
      """;

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      product_category_combobox = new ComboBoxBuilder(175, 120, 275, 50);
      while(rs.next()){
        product_category_combobox.addItem(rs.getString("categoryName"));
      }
      
      product_name_field = new TextFieldBuilder(true, 175, 50, 275, 50, 15);
      unit_price_field = new TextFieldBuilder(true, 175, 190, 275, 50, 15);
      stock_quantity_spinner = new SpinnerBuilder(true,1000);
      stock_quantity_spinner.setBounds(175, 260, 275, 50);

      product_name_field_label = new LabelBuilder("Product Name: ",30,50,200,50,15);
      category_name_field_label = new LabelBuilder("Category Name: ",30,120,200,50,15);
      unit_price_field_label = new LabelBuilder("Unit Price: ",30,190,200,50,15);
      stock_quantity_spinner_label = new LabelBuilder("Stock: ",30,260,200,50,15);
      
      product_name_field_label.setForeground(new Color(0XB58863));
      category_name_field_label.setForeground(new Color(0XB58863));
      unit_price_field_label.setForeground(new Color(0XB58863));
      stock_quantity_spinner_label.setForeground(new Color(0XB58863));
      
      confirm_btn = new ButtonBuilder("CONFIRM",30, 330, 200, 50,15);
      cancel_btn = new ButtonBuilder("CANCEL",250, 330, 200, 50,15);

      cancel_btn.addActionListener((a) -> {close();});
      confirm_btn.addActionListener((a) -> {addProduct();});
      
      
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setLayout(null);
      this.setTitle("NEW PRODUCT FORM");
      this.setSize(490,440);
      this.getContentPane().setBackground(new Color(0x293A3E));
      this.setLocationRelativeTo(null);
      this.setResizable(false);

      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          close();
        }
      });
      
      this.add(product_name_field);
      this.add(product_category_combobox);
      this.add(unit_price_field);
      this.add(stock_quantity_spinner);
      
      this.add (product_name_field_label); 
      this.add (category_name_field_label);
      this.add (unit_price_field_label); 
      this.add (stock_quantity_spinner_label);
      
      this.add(confirm_btn);
      this.add(cancel_btn);
    
      this.setVisible(true);
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
   void close(){
    parent.setEnabled(true);
    dispose();
  }
   
  void addProduct(){
    
  }
    
    
    
  
}
