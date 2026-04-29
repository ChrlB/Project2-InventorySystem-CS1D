/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.*;
import project2_inventorysystem.Windows.MyComponents.Header;

/**
 *
 * @author user
 */
public class UserLogs extends JFrame {
      Header header;

    UserLogs(int userID,Connection conn){
        try{
                header = new Header();
            this.addWindowListener(new java.awt.event.WindowAdapter() {
              @Override
              public void windowClosing(java.awt.event.WindowEvent e) {
              new User(userID, conn);
                dispose();
              }
            });

            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setTitle("USER");
            this.setLayout(null);
            this.setResizable(false);
            this.setSize(1270,580);
            this.getContentPane().setBackground(new Color(0xD3C3B9));
            this.setLocationRelativeTo(null);
            this.add(header);
            this.setVisible(true);
        }catch(Exception ex){
        System.out.println(ex);
      }
        
  }
}
