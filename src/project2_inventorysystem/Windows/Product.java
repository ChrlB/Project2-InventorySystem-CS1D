/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import project2_inventorysystem.Windows.MyComponents.*;
import project2_inventorysystem.Windows.Forms.ReStock;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.sql.*;
import project2_inventorysystem.Windows.Forms.DeductStock;

/**
 *
 * @author user
 */
public class Product extends JFrame{
  int user_ID;
  Connection conn;
  Header header;
  JPanel product_form_panel;
  ButtonBuilder deduct_btn,
                delete_btn,
                update_btn,
                restock_btn,
                category_window_btn,
                add_product_btn;
  
  TextFieldBuilder product_id_field,
                   product_name_field, 
                   unit_price_field, 
                   stock_quantity_field;
  String sql;
  ResultSet rs,
            products_rs;
  PreparedStatement pstmt;
  TableBuilder products_tbl;
  JScrollPane product_tbl_scroll_pane;
  
  LabelBuilder     product_id_field_label,
                   product_name_field_label, 
                   category_name_field_label,
                   unit_price_field_label, 
                   stock_quantity_field_label;
  
  ComboBoxBuilder product_category_combobox;
  Object[] selected_record;
  
  Product(int userID, Connection conn){
    try{
      this.conn = conn;
      user_ID = userID;
      
      header = new Header();


      sql = """
        SELECT 
          categoryName
        FROM categories;
      """;

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      product_category_combobox = new ComboBoxBuilder(175, 170, 275, 50);
      while(rs.next()){
        product_category_combobox.addItem(rs.getString("categoryName"));
      }
      
      category_window_btn = new ButtonBuilder("PRODUCT CATEGORY", 1040, 25, 200, 50,15);
      add_product_btn = new ButtonBuilder("ADD PRODUCT", 800, 25, 200, 50,15);

      delete_btn = new ButtonBuilder("DELETE", 30, 370, 200, 50,15);
      update_btn = new ButtonBuilder("UPDATE", 250, 370, 200, 50,15);
      deduct_btn = new ButtonBuilder("DEDUCT", 30, 450, 200, 50,15);
      restock_btn = new ButtonBuilder("RESTOCK", 250, 450, 200, 50,15);
      
      category_window_btn.addActionListener( (a) -> { new Category();} );
      add_product_btn.addActionListener( (a) -> {} );
      
      deduct_btn.addActionListener( (a) -> { deduct();} );
      delete_btn.addActionListener( (a) -> {} );
      update_btn.addActionListener( (a) -> {} );
      restock_btn.addActionListener( (a) -> { restock();} );
      

      product_id_field = new TextFieldBuilder(false, 175, 50, 275, 50, 15); 
      product_name_field = new TextFieldBuilder(true, 175, 110, 275, 50, 15);
      unit_price_field = new TextFieldBuilder(true, 175, 230, 275, 50, 15);
      stock_quantity_field = new TextFieldBuilder(false, 175, 290, 275, 50, 15);


      product_id_field_label = new LabelBuilder("Product ID: ",30,50,200,50,15);
      product_name_field_label = new LabelBuilder("Product Name: ",30,110,200,50,15);
      category_name_field_label = new LabelBuilder("Category Name: ",30,170,200,50,15);
      unit_price_field_label = new LabelBuilder("Unit Price: ",30,230,200,50,15);
      stock_quantity_field_label = new LabelBuilder("Stock: ",30,290,200,50,15);

      header.add(category_window_btn);   
      header.add(add_product_btn);

      product_form_panel = new JPanel();
      product_form_panel.setBounds(0, 100, 480, 550);
      product_form_panel.setBackground (new Color(0XB58863));
      product_form_panel.setLayout(null);

      product_form_panel.add(product_id_field);
      product_form_panel.add(product_name_field);
      product_form_panel.add(product_category_combobox);
      product_form_panel.add(unit_price_field);
      product_form_panel.add(stock_quantity_field);

      product_form_panel.add(deduct_btn);
      product_form_panel.add(delete_btn);
      product_form_panel.add(update_btn);
      product_form_panel.add(restock_btn);

      product_form_panel.add (product_id_field_label);
      product_form_panel.add (product_name_field_label); 
      product_form_panel.add (category_name_field_label);
      product_form_panel.add (unit_price_field_label); 
      product_form_panel.add (stock_quantity_field_label);

      sql = """
        SELECT 
            p.productID as ID,
            p.productName,
            p.categoryName as category,
            p.unitPrice,
            c.unit,
            p.stockQuantity as stock,
            DATE_FORMAT(p.dateCreated,"%Y-%d-%m") as dateCreated
        FROM products as p
        inner join categories as c 
            on  p.categoryName = c.categoryName
        WHERE p.isActive = 1;
      """;
      
      pstmt = conn.prepareStatement(sql);
      products_rs = pstmt.executeQuery();

      products_tbl = new TableBuilder(products_rs);
      
      products_tbl.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {  
            showSelectedRecord();
        }
      });
      
      product_tbl_scroll_pane = new JScrollPane(products_tbl);
      product_tbl_scroll_pane.setBounds(490, 150, 740, 455);

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
      this.setSize(1270,680);
      this.getContentPane().setBackground(new Color(0xD3C3B9));
      this.setLocationRelativeTo(null);
      
      this.add(header);
      this.add(product_form_panel);
      this.add(product_tbl_scroll_pane);

      this.setVisible(true);

    } catch(Exception ex) {

    }
  }
  
  public void showSelectedRecord(){
    try{
      int row = products_tbl.getSelectedRow();

      if (row != -1) {

        selected_record = new Object[] {
          products_tbl.getValueAt(row, 0),
          products_tbl.getValueAt(row, 1), 
          products_tbl.getValueAt(row, 2), 
          products_tbl.getValueAt(row, 3),
          products_tbl.getValueAt(row, 5)  
        };
      }
      
      product_id_field.setText(""+selected_record[0]);
      product_name_field.setText(""+selected_record[1]);
      product_category_combobox.setSelectedItem(selected_record[2]);
      unit_price_field.setText(""+selected_record[3]);
      stock_quantity_field.setText(""+selected_record[4]);
        
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  public void refreshTable(){
    try{
      sql = """
        SELECT 
            p.productID as ID,
            p.productName,
            p.categoryName as category,
            p.unitPrice,
            c.unit,
            p.stockQuantity as stock,
            DATE_FORMAT(p.dateCreated,"%Y-%d-%m") as dateCreated
        FROM products as p
        inner join categories as c 
            on  p.categoryName = c.categoryName
        WHERE p.isActive = 1;
      """;
      
      pstmt = conn.prepareStatement(sql);
      products_tbl.refreshTable(pstmt.executeQuery());
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
  void deduct(){
    try{
      int row = products_tbl.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null,
          "Please select a record first.",
          "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
      } 
      
      selected_record = new Object[]{
        products_tbl.getValueAt(row, 0),
      };
      
      new DeductStock(this, (int)selected_record[0], conn);
      this.setEnabled(false);
    }catch(Exception ex){
      ex.printStackTrace();
    
    }
  }
          
  void restock(){
    try{
      int row = products_tbl.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null,
          "Please select a record first.",
          "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
      } 
      
      selected_record = new Object[]{
        products_tbl.getValueAt(row, 0),
      };
      
      new ReStock(this, (int)selected_record[0], conn);
      this.setEnabled(false);
    }catch(Exception ex){
      ex.printStackTrace();
    
    }
  }
}
