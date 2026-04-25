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
import javax.swing.JScrollPane;
import project2_inventorysystem.Windows.MyComponents.Header;
import project2_inventorysystem.Windows.MyComponents.TableBuilder;

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
    
   public Sales(int userID,Connection conn){
      try{            
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        
        sql = """
              SELECT *
              FROM tbl_sales
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
        sales_tbl_scrollpane.setBounds(30,160,1200,480);
          
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
        this.setVisible(true);
        
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
  
}
