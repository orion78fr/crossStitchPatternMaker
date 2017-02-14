package fr.orion78.crossStitchPatternMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {
  public static MainWindow INSTANCE;
  private ImageComponent imageComponent = new ImageComponent();
  private BufferedImage currentImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY);

  private MainWindow() {
    this.setTitle("Cross Stitch Pattern Maker");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setJMenuBar(MenuFactory.create());

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(imageComponent, BorderLayout.NORTH);

    JScrollPane scroll = new JScrollPane(panel);

    this.getContentPane().add(scroll);

    this.setSize(800, 600);
  }

  public void setImage(BufferedImage img) {
    currentImage = img;
    imageComponent.setImage(img);
  }

  public BufferedImage getCurrentImage() {
    return currentImage;
  }

  public static void main(String[] args) {
    INSTANCE = new MainWindow();
    INSTANCE.setVisible(true);
  }
}
