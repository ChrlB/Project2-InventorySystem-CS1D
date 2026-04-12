/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows.MyComponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author user
 */
public class SpinnerBuilder extends JSpinner {

  public SpinnerBuilder() {
    super(new SpinnerNumberModel(1, 1, 1, 1));
    applyStyle();
  }

  public void setMax(int max) {
    setModel(new SpinnerNumberModel(1, 1, max, 1));
    applyStyle();
  }

  private void applyStyle() {
    JComponent editor = this.getEditor();
    if (editor instanceof JSpinner.DefaultEditor) {
      ((JSpinner.DefaultEditor) editor)
          .getTextField()
          .setEditable(false);
    }
    this.setFont(new Font("Arial", Font.BOLD, 14));
    this.setForeground(new Color(0x3D4D55));
  }
}
