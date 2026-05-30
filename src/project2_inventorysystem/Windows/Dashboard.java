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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import static javax.swing.SwingConstants.CENTER;
import project2_inventorysystem.DAO.ProductDataAccessObject;
import project2_inventorysystem.DAO.UserDataAccessObject;
import project2_inventorysystem.Services.DBConnection.DBConnection;
import project2_inventorysystem.Services.UserSession.UserSession;


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
  
  IconBuilder barista_icon,
              order_icon,
              product_icon,
              sales_icon,
              user_icon;
  
  JLabel low_stocks_label,
         best_products_label;
  
  LabelBuilder     user_fullname_label;
          
  int user_ID;
  
  UserDataAccessObject userDAO;
  ProductDataAccessObject productDAO;
  
  Connection conn;
  ResultSet rs;
  
  
  public Dashboard(){
    try{
      this.userDAO = new UserDataAccessObject();
      this.productDAO = new ProductDataAccessObject();
      
      this.conn = DBConnection.getInstance().getDBConnection();
      this.user_ID = UserSession.getInstance().getUserID();
      
      {
      header = new Header();
      
      rs = userDAO.getUserInfoByUserID(user_ID);
      rs.next();
      
      
      System.out.println(rs.getString("fullname"));
      
      button_panel = new JPanel();
      button_panel.setLayout(null);
      button_panel.setBounds(0,100,400,550);
      button_panel.setBackground(new Color(0XB58863));
      
      user_fullname_label = new LabelBuilder(rs.getString("fullname"),1100,55,140,30,15);
      user_fullname_label.setForeground(new Color(0XB58863));
      user_fullname_label.setHorizontalAlignment(CENTER);
      
      order_btn =   new ButtonBuilder("ORDER"   ,160,50,220,70,20);
      product_btn = new ButtonBuilder("PRODUCT" ,160,150,220,70,20);
      sales_btn =   new ButtonBuilder("SALES"   ,160,250,220,70,20);
      user_btn =    new ButtonBuilder("USER"    ,160,350,220,70,20);
      
      order_btn.addActionListener(e -> {    new Order(user_ID, conn); this.dispose();  });
      product_btn.addActionListener(e -> {  new Product(user_ID,conn);  this.dispose(); });
      sales_btn.addActionListener(e -> {    new Sales(user_ID,conn);  this.dispose(); });
      user_btn.addActionListener(e ->{      new User(user_ID,conn); this.dispose(); });
      
      barista_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/barista.png",1150,15,40,40);
      order_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/menu.png",30,40,100,90);
      product_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/product.png",30,140,100,90);
      sales_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/sales.png",30,240,100,90);
      user_icon = new IconBuilder("/project2_inventorysystem/Windows/Icons/user.png",30,340,100,90);
      
      header.add(barista_icon);
      header.add(user_fullname_label);
      
      low_stocks_label = new JLabel("LOW STOCK PRODUCTS:");
      low_stocks_label.setBounds(420,125,300,50);
      low_stocks_label.setFont(new Font("Arial", Font.BOLD, 16));
      
      best_products_label = new JLabel("BEST SELLER:");
      best_products_label.setBounds(420,345,300,50);
      best_products_label.setFont(new Font("Arial", Font.BOLD, 16));
      
      
      rs = productDAO.getLowStockProducts();
      low_stocks_tbl= new JScrollPane(new TableBuilder(rs));
      low_stocks_tbl.setBounds(420,175,800,170);
      
      rs = productDAO.getBestSellerProducts();
      best_products_tbl = new JScrollPane(new TableBuilder(rs));
      best_products_tbl.setBounds(420,395,800,170);
      
      
      button_panel.add(order_btn);
      button_panel.add(product_btn);
      button_panel.add(sales_btn);
      button_panel.add(user_btn);
      
      button_panel.add(order_icon);
      button_panel.add(product_icon);
      button_panel.add(sales_icon);
      button_panel.add(user_icon);
      
      }
      
      if(user_ID != 1){
        product_btn.setEnabled(false);
        user_btn.setEnabled(false);
      }
      
      ImageIcon icon = new ImageIcon(getClass().getResource("/project2_inventorysystem/Windows/Icons/cup.png"));
      this.setIconImage(icon.getImage());
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setTitle("DASHBOARD");
      this.setLayout(null);
      this.setResizable(false);
      this.setSize(1270,650);
      this.getContentPane().setBackground(new Color(0xD3C3B9));
      this.setLocationRelativeTo(null);
      
      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          logout(user_ID); 
        }
      });
      
      
      this.add(low_stocks_tbl);
      this.add(best_products_tbl);
      
      this.add(low_stocks_label);
      this.add(best_products_label);
      this.add(header);
      this.add(button_panel);
      this.setVisible(true);
    }catch (Exception ex){
      ex.printStackTrace();
      //System.out.print(ex);
    }
  }
  
  void logout(int user_ID){
    try{
      int command = JOptionPane.showConfirmDialog(null,
              "Are you sure you want to log out?",
              "Log Out", JOptionPane.OK_CANCEL_OPTION
      );
      if (!(command == JOptionPane.OK_OPTION)) return;
      
      
      //userDAO.recordUserLogoutLog(user_ID);

      System.out.println("Logout Successful!");
      new Login();
      dispose();
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
}
