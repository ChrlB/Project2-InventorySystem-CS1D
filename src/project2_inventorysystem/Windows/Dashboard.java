/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;
import project2_inventorysystem.Windows.MyComponents.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


/**
 *
 * @author user
 */
public class Dashboard extends JFrame{
  Header header; 
  JPanel button_panel;
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
  JLabel low_stocks_label,
         best_products_label;
  
  int user_ID;
  PreparedStatement pstmt;
  Connection conn;
  ResultSet rs;
  String sql;
  
  public Dashboard(int userID, Connection conn){
    try{
      this.conn = conn;
      this.user_ID = userID;
      
      header = new Header();
      //header.setBackground(new Color(0X3E2522));
      
      button_panel = new JPanel();
      button_panel.setLayout(null);
      button_panel.setBounds(0,100,400,550);
      //button_panel.setBackground(new Color(0X8C6E63));
      button_panel.setBackground(new Color(0XB58863));
      
      order_btn =   new ButtonBuilder("ORDER"   ,160,50,220,70,20);
      product_btn = new ButtonBuilder("PRODUCT" ,160,150,220,70,20);
      sales_btn =   new ButtonBuilder("SALES"   ,160,250,220,70,20);
      user_btn =    new ButtonBuilder("USER"    ,160,350,220,70,20);
      
      order_btn.addActionListener(e -> {  new Order(user_ID, conn); this.dispose();  });
      product_btn.addActionListener(e -> {new Product(conn);this.dispose();});
//      sales_btn.addActionListener(e -> new Sales());
      user_btn.addActionListener(e ->{new User(conn); this.dispose();});
      
      order_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/menu.png",30,40,100,90);
      product_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/product.png",30,140,100,90);
      sales_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/sales.png",30,240,100,90);
      user_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/user.png",30,340,100,90);
      
      
      sql = """
        SELECT 
            productID,
            productName as name,
            IF(stockQuantity = 0,'OUT OF STOCK',stockQuantity) as stocks 
        FROM products WHERE stockQuantity < 11;
        """;
      
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      low_stocks_tbl= new JScrollPane(new TableBuilder(rs));
      //low_stocks_tbl.setBounds(420,150,400,350);
      low_stocks_tbl.setBounds(420,200,800,170);
      
      
      sql = """
        SELECT 
            productID,
            productName as name,
            unitPrice as price,
            categoryName as category 
        FROM products WHERE stockQuantity < 11;
        """;
      
      //sql ="Select * from categories";
      
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      best_products_tbl = new JScrollPane(new TableBuilder(rs));
      //best_products_tbl.setBounds(830,150,400,350);
      best_products_tbl.setBounds(420,420,800,170);
      
      low_stocks_label = new JLabel("LOW STOCK PRODUCTS:");
      low_stocks_label.setBounds(420,150,300,50);
      low_stocks_label.setFont(new Font("Arial", Font.BOLD, 16));
      
      best_products_label = new JLabel("MONTHLY BEST PRODUCT:");
      best_products_label.setBounds(420,370,300,50);
      best_products_label.setFont(new Font("Arial", Font.BOLD, 16));
      
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setTitle("DASHBOARD");
      this.setLayout(null);
      this.setResizable(false);
      this.setSize(1270,650);
      this.getContentPane().setBackground(new Color(0xD3C3B9));
      //this.getContentPane().setBackground(new Color(0xFFE0B2));
      
      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          logout(user_ID); 
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
      
      
      
      this.add(low_stocks_tbl);
      this.add(best_products_tbl);
      
      this.add(low_stocks_label);
      this.add(best_products_label);
      this.add(header);
      this.add(button_panel);
      this.setVisible(true);
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
