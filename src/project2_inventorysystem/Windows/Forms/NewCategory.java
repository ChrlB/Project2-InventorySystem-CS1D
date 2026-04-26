/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows.Forms;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JFrame;
import project2_inventorysystem.Windows.Category;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.SpinnerBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;
import project2_inventorysystem.Windows.Product;

/**
 *
 * @author user
 */
public class NewCategory extends JFrame{
  Category parent;
  Connection conn;
  
  TextFieldBuilder category_name_field,
                     description_field,
                     unit_field;
  SpinnerBuilder lowStockThreshold_spinner;

  LabelBuilder  category_name_field_label,
                description_field_label,
                unit_field_label,
                lowStockThreshold_field_label,
                lowStockThreshold2_field_label;
  
  ButtonBuilder confirm_btn, 
                cancel_btn;
  
  public NewCategory(Category parent,Connection conn){
    this.parent = parent;
    this.conn = conn;
    
    
    category_name_field_label = new LabelBuilder("Category Name: ",30,50,150,50,15);
    description_field_label= new LabelBuilder("Discription: ",30,130,150,50,15);
    unit_field_label= new LabelBuilder("Unit Field: ",30,210,150,50,15);
    lowStockThreshold_field_label= new LabelBuilder("Low Stock ",30,270,150,50,15);
    lowStockThreshold2_field_label= new LabelBuilder("Threshold: ",30,300,150,50,15);
    
    category_name_field_label.setForeground(new Color(0XB58863));
    description_field_label.setForeground(new Color(0XB58863));
    unit_field_label.setForeground(new Color(0XB58863));
    lowStockThreshold_field_label.setForeground(new Color(0XB58863));
    lowStockThreshold2_field_label.setForeground(new Color(0XB58863));

    category_name_field = new TextFieldBuilder(true, 180, 50, 270, 50, 15);
    description_field = new TextFieldBuilder(true, 180, 130, 270, 50, 15);
    unit_field = new TextFieldBuilder(true, 180, 210, 270, 50, 15);

    lowStockThreshold_spinner = new SpinnerBuilder(true,5,300);
    lowStockThreshold_spinner.setBounds( 180, 290, 270, 50);
    
    confirm_btn = new ButtonBuilder("CONFIRM",30, 370, 200, 50,15);
    cancel_btn = new ButtonBuilder("CANCEL",250, 370, 200, 50,15);

    cancel_btn.addActionListener((a) -> {closeWindow();});
    confirm_btn.addActionListener((a) -> {addCategory();});
    
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setLayout(null);
    this.setTitle("NEW CATEGORY FORM");
    this.setSize(490,480);
    this.getContentPane().setBackground(new Color(0x293A3E));
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        closeWindow();
      }
    });
    
    this.add(category_name_field);
    this.add(description_field);
    this.add(unit_field);
    this.add(lowStockThreshold_spinner);


    this.add(category_name_field_label);
    this.add(description_field_label);
    this.add(unit_field_label);
    this.add(lowStockThreshold_field_label);
    this.add(lowStockThreshold2_field_label);
    
    this.add(confirm_btn);
    this.add(cancel_btn);
    
    this.setVisible(true);
  }
  
  void closeWindow(){
    parent.setEnabled(true);
    this.dispose();
  }
  
  void addCategory(){
    
  }
  
  
}
