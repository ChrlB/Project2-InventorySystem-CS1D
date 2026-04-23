/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.Header;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;

/**
 *
 * @author juanv
 */
public class Category extends JFrame{
    int user_ID;
    Connection conn;
    Header header;
    JPanel category_form_panel;
    
    TextFieldBuilder category_name_field,
                     discription_field,
                     unit_field,
                     lowStockThreshold_field;
    LabelBuilder  category_name_field_label,
                  discription_field_label,
                  unit_field_label,
                  lowStockThreshold_field_label,
                  lowStockThreshold2_field_label;
    ButtonBuilder new_btn, 
                  delete_btn,
                  update_btn;
    
    public Category(int userID,Connection conn){
      try{
        category_name_field_label = new LabelBuilder("Category Name: ",30,50,150,50,15);
        discription_field_label= new LabelBuilder("Discription: ",30,130,150,50,15);
        unit_field_label= new LabelBuilder("Unit Field: ",30,210,150,50,15);
        lowStockThreshold_field_label= new LabelBuilder("Low Stock ",30,270,150,50,15);
        lowStockThreshold2_field_label= new LabelBuilder("Threshold: ",30,300,150,50,15);
        
        category_name_field = new TextFieldBuilder(false, 180, 50, 270, 50, 15);
        discription_field = new TextFieldBuilder(true, 180, 130, 270, 50, 15);
        unit_field = new TextFieldBuilder(true, 180, 210, 270, 50, 15);
        lowStockThreshold_field = new TextFieldBuilder(true, 180, 290, 270, 50, 15);
        
        new_btn = new ButtonBuilder("NEW",500, 30, 200, 50,15);
        update_btn = new ButtonBuilder("UPDATE",250, 370, 200, 50,15);
        delete_btn = new ButtonBuilder("DELETE",30, 370, 200, 50,15);
      
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        
        header.add(new_btn);
        
        
        category_form_panel = new JPanel();
        category_form_panel.setLayout(null);
        category_form_panel.setBounds(0,100, 480, 450);
        category_form_panel.setBackground(new Color(0XB58863));
        
        
        category_form_panel.add(category_name_field);
        category_form_panel.add(discription_field);
        category_form_panel.add(unit_field);
        category_form_panel.add(lowStockThreshold_field);
        
        
        category_form_panel.add(category_name_field_label);
        category_form_panel.add(discription_field_label);
        category_form_panel.add(unit_field_label);
        category_form_panel.add(lowStockThreshold_field_label);
        category_form_panel.add(lowStockThreshold2_field_label);
        
       
        category_form_panel.add(delete_btn);
        category_form_panel.add(update_btn);
        
        
        
        
        
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            new Dashboard(user_ID,conn); 
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
        this.add(category_form_panel);
        this.setVisible(true);
        
        
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
    
    
}
