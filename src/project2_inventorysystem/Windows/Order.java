/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import project2_inventorysystem.Windows.Dashboard;
import javax.swing.JFrame;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import project2_inventorysystem.Windows.MyComponents.*;

/**
 *
 * @author user
 */
public class Order extends JFrame{
  int user_ID, total_order_price = 0;
  ArrayList<int[]> pendingItems ;
  Header header ;
  JPanel order_form_panel;
  TableBuilder product_tbl;
  JScrollPane product_tbl_scrollpane,
              order_list_scrollpane;
  
  ComboBoxBuilder product_category_combobox;
  SpinnerBuilder  quantity_spinner;
  TextFieldBuilder customer_name_field,
                   productID_field,
                   productName_field;
  JTextArea order_list;
  
  ButtonBuilder add_btn,
                cancel_btn,
                confirm_btn;
  
  PreparedStatement pstmt,
                    batch_pstmt;
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
      pendingItems = new ArrayList<>();

      header = new Header();

      order_form_panel = new JPanel();
      order_form_panel.setLayout(null);
      order_form_panel.setBounds(0,100,700,550);
      order_form_panel.setBackground(new Color(0XB58863));
      
      sql = """
            INSERT INTO  sales(
                  orderID, 
                  productID,
                  quantity) 
            VALUES (?, ?, ?)
            """;
      batch_pstmt = conn.prepareStatement(sql);
      
      add_btn = new ButtonBuilder("ADD",325,330,150,45,15);
      add_btn.addActionListener((a) -> addItemToOrder());
      cancel_btn= new ButtonBuilder("CANCEL",500,330,150,45,15);
      cancel_btn.addActionListener((a) -> cancelOrder());
      confirm_btn= new ButtonBuilder("CONFIRM ORDER",325,405,325,45,15);
      confirm_btn.addActionListener((a) -> confirmOrder());
     
      sql = """
        SELECT 
          DISTINCT categoryName
        FROM products 
        WHERE stockQuantity > 0; 
      """;

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      product_category_combobox = new ComboBoxBuilder("ALL",500,250,150,40);
      while(rs.next()){
        product_category_combobox.addItem(rs.getString("categoryName"));
      }
      product_category_combobox.addActionListener((e) -> filterTable());
      
      quantity_spinner = new SpinnerBuilder();
      quantity_spinner.setBounds(325,250,150,40);
      
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
        public void mouseReleased(MouseEvent e) {  
            showSelectedRecord();
        }
      });
      product_tbl.setRowHeight(35);
      
      product_tbl_scrollpane = new JScrollPane(product_tbl);
      product_tbl_scrollpane.setBounds(0,0,700,200);
      
      
      
      //productID_field = new TextFieldBuilder(false,100,410,200,30);
      productID_field = new TextFieldBuilder(false,100,250,200,40);
      productName_field = new TextFieldBuilder(false,100,330,200,40);
      customer_name_field = new TextFieldBuilder(true,100,410,200,40);
      
      order_list = new JTextArea();
      order_list.setText("ORDER LIST:");
      
      order_list.setEditable(false);
      order_list.setFont(new Font("Arial", Font.BOLD, 20));
      order_list.setForeground(new Color(0x3D4D55));
      order_list.setBackground(new Color(0xD3C3B9));
      
      order_list_scrollpane = new JScrollPane(order_list);
      order_list_scrollpane.setBounds(725, 175, 500, 375);
      

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
      
      order_form_panel.add(productID_field);
      order_form_panel.add(productName_field);
      order_form_panel.add(customer_name_field);
      
      order_form_panel.add(add_btn);
      order_form_panel.add(cancel_btn);
      order_form_panel.add(confirm_btn);

      this.add(header);
      this.add(order_form_panel);
      this.add(order_list_scrollpane);
      this.setVisible(true);
    }catch(Exception ex){
      System.out.println(ex);
    }
  }
  private void filterTable(){
    try {
      String selected_category = product_category_combobox.getSelectedItem().toString();
      
      if(selected_category.equals("ALL")){
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
          product_tbl.getValueAt(row, 0)
//          product_tbl.getValueAt(row, 1), 
//          product_tbl.getValueAt(row, 2), 
//          product_tbl.getValueAt(row, 3)  
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
        
        productName_field.setText(product_rs.getString("productName"));
      }
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  public void addItemToOrder(){
    try{
      int productID = product_rs.getInt("productID");
      int quantity = (int)(quantity_spinner.getValue()); // or however you get qty
      pendingItems.add(new int[]{productID, quantity});
      
      String productName = product_rs.getString("productName");
      String unit = product_rs.getString("unit");
      int unit_price = product_rs.getInt("unitPrice");
      
      int total_item_price = (unit_price * quantity);
      total_order_price += total_item_price;
      
//      order_list.setText(
//              order_list.getText() + "\n"+
//              " "+quantity + unit+
//              " | "+productName+
//              " | total: "+total_item_price
//      );
      order_list.setText(
          order_list.getText() + "\n[" +
          pendingItems.size() + "]  " +
          quantity + unit + "  " +
          productName + " - ₱" + total_item_price
      );
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  public void cancelOrder(){
    try{
      total_order_price = 0;
      pendingItems.clear();
      batch_pstmt.clearBatch();
      order_list.setText("ORDER LIST:");
    }catch(Exception ex){
      ex.printStackTrace(); 
    }
  }
  public void confirmOrder(){
    if (pendingItems.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No items added!");
        return;
    }
    try {
        // Insert order, get generated key
        sql = "INSERT INTO orders(userID,customerName) VALUES (?,?)";
      
        pstmt = conn.prepareStatement(
            sql,
            Statement.RETURN_GENERATED_KEYS
        );
        pstmt.setInt(1, user_ID);
        pstmt.setString(2,customer_name_field.getText());
        pstmt.executeUpdate();
        rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
            int orderID = rs.getInt(1);

            // Build batch
            for (int[] item : pendingItems) {
                batch_pstmt.setInt(1, orderID);   // orderID
                batch_pstmt.setInt(2, (int)item[0]);   // productID
                batch_pstmt.setInt(3, (int)item[1]);   // quantity
                batch_pstmt.addBatch();
            }
        }

        batch_pstmt.executeBatch();
        JOptionPane.showMessageDialog(null, "Order confirmed!");
        cancelOrder();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
  }
}
