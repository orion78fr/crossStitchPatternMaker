package fr.orion78.crossStitchPatternMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {
  public static MainWindow INSTANCE;
  private ImageComponent imageComponent = new ImageComponent();
  private BufferedImage original = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY);
  private BufferedImage currentImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY);
  private Parameters params = new Parameters();

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

  private void refresh(){
    BufferedImage currentImage = original;
    if(params.getSquareSize() > 0){
      currentImage = ImageOP.toSquares(currentImage, params.getSquareSize());
    }
    if(params.isGreyScale()){
      currentImage = ImageOP.toGreyScale(currentImage);
    }
    if(params.getNbColors() > 0){
      currentImage = ImageOP.withNbColor(currentImage, params.getNbColors());
    }
    imageComponent.setImage(currentImage);
  }

  public void setImage(BufferedImage img) {
    original = img;
    params.reset();
    refresh();
  }

  public BufferedImage getCurrentImage() {
    return currentImage;
  }

  public void setSquareSize(int squareSize) {
    params.setSquareSize(squareSize);
    refresh();
  }

  public void switchGreyScale(){
    params.switchGreyScale();
    refresh();
  }

  public void setNumberOfColors(int nbColors) {
    params.setNbColors(nbColors);
  }

  public static void main(String[] args) {
    INSTANCE = new MainWindow();
    INSTANCE.setVisible(true);
  }
}
