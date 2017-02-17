package fr.orion78.crossStitchPatternMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComponent {
  private JLabel label = new JLabel();

  void setImage(BufferedImage img) {
    label.setIcon(new ImageIcon(img));
  }

  public Component getComponent() {
    return label;
  }
}
