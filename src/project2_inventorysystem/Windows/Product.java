/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import project2_inventorysystem.Windows.MyComponents.*;
import java.awt.Color;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author user
 */
public class Product extends JFrame{
  int user_ID;
  Connection conn;
  Header header;
  JPanel product_form_panel;
  ButtonBuilder add_btn,
                delete_btn,
                update_btn,
                restock_btn,
                add_category_btn;
  TextFieldBuilder product_id_field,
                   product_name_field, 
                   category_name_field,
                   unit_price_field, 
                   stock_quantity_field;
  String sql;
  ResultSet rs;
  PreparedStatement pstmt;
  TableBuilder products_tbl;
  JScrollPane product_tbl_scroll_pane;
  
  LabelBuilder     product_id_field_label,
                   product_name_field_label, 
                   category_name_field_label,
                   unit_price_field_label, 
                   stock_quantity_field_label;
 
    Product(int userID, Connection conn){
      try{
      this.conn = conn;
      user_ID = userID;
      header = new Header();
      
      add_btn = new ButtonBuilder("ADD", 30, 370, 200, 50,15);
      delete_btn = new ButtonBuilder("DELETE", 250, 370, 200, 50,15);
      update_btn = new ButtonBuilder("UPDATE", 30, 450, 200, 50,15);
      restock_btn = new ButtonBuilder("RESTOCK", 250, 450, 200, 50,15);
      add_category_btn = new ButtonBuilder("ADD CATEGORY", 1040, 25, 200, 50,15);
      
      product_id_field = new TextFieldBuilder(false, 200, 50, 250, 50, 15); 
      product_name_field = new TextFieldBuilder(true, 200, 110, 250, 50, 15);
      category_name_field = new TextFieldBuilder(true, 200, 170, 250, 50, 15);
      unit_price_field = new TextFieldBuilder(true, 200, 230, 250, 50, 15);
      stock_quantity_field = new TextFieldBuilder(true, 200, 290, 250, 50, 15);
      
      
      product_id_field_label = new LabelBuilder("Product ID: ",30,50,200,50,15);
      product_name_field_label = new LabelBuilder("Product Name: ",30,110,200,50,15);
      category_name_field_label = new LabelBuilder("Category Name: ",30,170,200,50,15);
      unit_price_field_label = new LabelBuilder("Unit Price: ",30,230,200,50,15);
      stock_quantity_field_label = new LabelBuilder("Stock: ",30,290,200,50,15);
      
      header.add(add_category_btn);             
                   
      product_form_panel = new JPanel();
      product_form_panel.setBounds(0, 100, 500, 550);
      product_form_panel.setBackground (new Color(0XB58863));
      product_form_panel.setLayout(null);
      
      product_form_panel.add(product_id_field);
      product_form_panel.add(product_name_field);
      product_form_panel.add(category_name_field);
      product_form_panel.add(unit_price_field);
      product_form_panel.add(stock_quantity_field);
    
      product_form_panel.add(add_btn);
      product_form_panel.add(delete_btn);
      product_form_panel.add(update_btn);
      product_form_panel.add(restock_btn);
      
      product_form_panel.add (product_id_field_label);
      product_form_panel.add (product_name_field_label); 
      product_form_panel.add (category_name_field_label);
      product_form_panel.add (unit_price_field_label); 
      product_form_panel.add (stock_quantity_field_label);
      
      sql = """
            SELECT * FROM products;
            """;
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      products_tbl = new TableBuilder(rs);
      product_tbl_scroll_pane = new JScrollPane(products_tbl);
      product_tbl_scroll_pane.setBounds(510, 150, 700, 400);
      
      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          new Dashboard(user_ID,conn); 
          dispose();
        }
      });
      
      
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setTitle("PRODUCT");
      this.setLayout(null);
      this.setResizable(false);
      this.setSize(1270,650);
      this.getContentPane().setBackground(new Color(0xD3C3B9));
      this.add(header);
      this.add(product_form_panel);
      this.add(product_tbl_scroll_pane);
                    
      this.setVisible(true);
      
      }
      catch(Exception ex) {
          
      }
  }
}
