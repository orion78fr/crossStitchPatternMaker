package fr.orion78.crossStitchPatternMaker;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends JLabel {
  void setImage(BufferedImage img) {
    this.setIcon(new ImageIcon(img));
  }
}
