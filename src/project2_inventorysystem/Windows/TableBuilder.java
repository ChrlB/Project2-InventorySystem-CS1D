/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author user
 */
public class TableBuilder extends JTable{
  DefaultTableModel tbl_model;
  ResultSetMetaData rs_metadata;
  
  TableBuilder(ResultSet rs, int width, int height){
    try{
      rs_metadata = rs.getMetaData();
      int column_count = rs_metadata.getColumnCount();
      
      String[] column_names = new String[column_count];
      
      for(int column = 0; column < column_count; ){
        column_names[column] = rs_metadata.getColumnName(++column);
      }
      
      tbl_model = new DefaultTableModel(column_names,0);
      
      while(rs.next()){
        Object[] record = new Object[column_count];
        
        for(int column = 0; column < column_count;){
         record[column] = rs.getObject(++column);
        }
        tbl_model.addRow(record);
      }
      
      setSize(width, height);
      setModel(tbl_model);
      setVisible(true);
      
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
}
