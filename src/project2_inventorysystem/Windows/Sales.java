/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import project2_inventorysystem.Windows.MyComponents.Header;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.TableBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;

/**
 *
 * @author user
 */
public class Sales extends JFrame{
    int user_ID;
    Connection conn;
    Header header;
    
    TableBuilder sales_tbl;
    JScrollPane sales_tbl_scrollpane;
    
    ResultSet rs;
    String sql;
    PreparedStatement pstmt;
    
    TextFieldBuilder total_orders_field,
                    total_sales_field,
                    total_quantity_field;
        
    LabelBuilder  total_orders_field_label,
              total_sales_field_label,
              total_quantity_field_label;
    
    JPanel footer_panel;
    
   public Sales(int userID,Connection conn){
      try{  
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        
        footer_panel = new JPanel();
//        footer_panel.setBackground(new Color(0x3D4D55));
        footer_panel.setBackground(new Color(0XB58863));
        footer_panel.setLayout(null);
        footer_panel.setBounds(0, 580, 1270, 120);
        
        total_orders_field = new TextFieldBuilder(false, 170, 590, 250, 50, 17);
        total_sales_field = new TextFieldBuilder(false, 560, 590, 260, 50, 17);
        total_quantity_field = new TextFieldBuilder(false, 980, 590, 250, 50, 17);
        
        total_orders_field_label = new LabelBuilder("TOTAL ORDERS: ",30,590,150,50,15);
        total_sales_field_label = new LabelBuilder("TOTAL SALES: ",430,590,150,50,15);
        total_quantity_field_label = new LabelBuilder("TOTAL QUANTITY: ",830,590,150,50,15);
        
        total_orders_field_label.setForeground(new Color(0x3D4D55));
        total_sales_field_label.setForeground(new Color(0x3D4D55));
        total_quantity_field_label.setForeground(new Color(0x3D4D55));
        
        total_sales_field.setText("₱");
        
        sql = """
              SELECT 
                S.saleID,
                S.orderID,
                O.customerName,
                S.productID,
                P.productName,
                S.salePrice,
                S.quantity             
              FROM tbl_sales AS S
              INNER JOIN tbl_products AS P
              ON S.productID = P.productID
              INNER JOIN tbl_orders AS O
              ON S.orderID = O.orderID
              """;
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        sales_tbl = new TableBuilder(rs);
        sales_tbl.addMouseListener(new MouseAdapter() {
         @Override
          public void mouseReleased(MouseEvent e) {  
              //showSelectedRecord();
          }
        });
        
        sales_tbl_scrollpane = new JScrollPane(sales_tbl);
        sales_tbl_scrollpane.setBounds(30,160,1200,410);
        
        sql = """
              SELECT 
                 SUM(salePrice) AS TOTAL_SALE_PRICE, 
                 SUM(quantity) AS TOTAL_QUANTITY
              FROM tbl_sales AS S
            
              """;
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        rs.next();
        total_sales_field.setText(rs.getString("TOTAL_SALE_PRICE"));
        total_quantity_field.setText(rs.getString("TOTAL_QUANTITY"));
        
        sql = """
              SELECT 
                 COUNT(DISTINCT orderID) AS TOTAL_ORDERS
                
              FROM tbl_sales AS S
            
              """;
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        rs.next();
        total_orders_field.setText(rs.getString("TOTAL_ORDERS"));
          
         this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            new Dashboard(user_ID,conn); 
            dispose();
          }
        });
          
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("SALES");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1270,700);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
        this.setLocationRelativeTo(null);
        
        this.add (header);
        this.add(sales_tbl_scrollpane);
        
        this.add(total_orders_field);
        this.add(total_sales_field);
        this.add(total_quantity_field);
        
        this.add(total_orders_field_label);
        this.add(total_sales_field_label);
        this.add(total_quantity_field_label);
        
        this.add(footer_panel);
        
        
        this.setVisible(true);
        
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
  
}
