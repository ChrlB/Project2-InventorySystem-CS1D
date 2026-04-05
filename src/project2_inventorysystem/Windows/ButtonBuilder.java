/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author user
 */
public class ButtonBuilder extends JButton{
  
  
  ButtonBuilder(String label,int x,int y, int width, int height){
    setBounds(x, y, width, height);
    setFocusable(false);
    setText(label);
    setForeground(Color.WHITE);
    setBackground(new Color(0x3D4D55));
    //setForeground(new Color(0xE1D4C2));
//    setBackground(new Color(0X291C0E));
  }
}
