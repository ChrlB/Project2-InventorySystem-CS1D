/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


/**
 *
 * @author user
 */
public class Dashboard extends JFrame{
  JPanel header, 
         button_panel;
  JScrollPane low_stocks_tbl , 
              best_products_tbl;
  ButtonBuilder order_btn,
                product_btn,
                sales_btn,
                user_btn;
  IconBuilder order_icon,
              product_icon,
              sales_icon,
              user_icon;
  
  int user_ID;
  PreparedStatement pstmt;
  Connection conn;
  ResultSet rs;
  String sql;
  
  public Dashboard(int userID, Connection conn){
    try{
      this.conn = conn;

      user_ID = userID;
      header = new JPanel();
      header.setBounds(0,0,1070,100);
      header.setBackground(new Color(0x10232A));
      //header.setBackground(new Color(0X3E2522));
      
      button_panel = new JPanel();
      button_panel.setLayout(null);
      button_panel.setBounds(0,100,400,450);
      //button_panel.setBackground(new Color(0X8C6E63));
      button_panel.setBackground(new Color(0XB58863));
      
      order_btn =   new ButtonBuilder("ORDER"   ,150,50,200,50);
      product_btn = new ButtonBuilder("PRODUCT" ,150,125,200,50);
      sales_btn =   new ButtonBuilder("SALES"   ,150,200,200,50);
      user_btn =    new ButtonBuilder("USER"    ,150,275,200,50);
      
      order_btn.addActionListener(e -> { new Order(user_ID, conn); dispose(); });
//      product_btn.addActionListener(e -> new Product());
//      sales_btn.addActionListener(e -> new Sales());
//      user_btn.addActionListener(e -> new User());
      
      order_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/menu.png",50,40,70,70);
      product_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/product.png",50,115,70,70);
      sales_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/sales.png",50,190,70,70);
      user_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/user.png",50,265,70,70);
      
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setTitle("DASHBOARD");
      setLayout(null);
      setResizable(false);
      setSize(1070,550);
      getContentPane().setBackground(new Color(0xD3C3B9));
      //getContentPane().setBackground(new Color(0xFFE0B2));
      
      sql = """
        SELECT 
            productID,
            productName as name,
            productStocks as stocks 
        FROM products WHERE productStocks < 11""";
      
      //sql ="Select * from categories";
      
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      low_stocks_tbl= new JScrollPane(new TableBuilder(rs));
      low_stocks_tbl.setBounds(420,150,300,350);
      
      sql = """
        SELECT 
            productID,
            productName as name,
            categoryID as category 
        FROM products WHERE productStocks < 11""";
      
      //sql ="Select * from categories";
      
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      best_products_tbl = new JScrollPane(new TableBuilder(rs));
      best_products_tbl.setBounds(730,150,300,350);

      // 2. Add the listener to call your method
      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          logout(user_ID); // Call your method here
          
        }
      });
      
      button_panel.add(order_btn);
      button_panel.add(product_btn);
      button_panel.add(sales_btn);
      button_panel.add(user_btn);
      
      button_panel.add(order_icon);
      button_panel.add(product_icon);
      button_panel.add(sales_icon);
      button_panel.add(user_icon);
      
      add(low_stocks_tbl);
      add(best_products_tbl);
      
      add(header);
      add(button_panel);
      setVisible(true);
    }catch (Exception ex){
      System.out.print(ex);
    }
  }
  
  void logout(int user_ID){
    // Verify a password (do this during login)
    try{
//      sql = "UPDATE userlogs SET logoutDate = CURRENT_TIMESTAMP WHERE userID = ? AND logoutDate IS NULL";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setInt(1, user_ID);
//      pstmt.executeUpdate();
//
      System.out.println("Logout Successful!");
      new Login(conn);
      dispose();
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
}
