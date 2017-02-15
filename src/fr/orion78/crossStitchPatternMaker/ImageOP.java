package fr.orion78.crossStitchPatternMaker;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageOP {
  public static BufferedImage toSquares(BufferedImage original, int squareSize) {
    int sizex = original.getWidth();
    int sizey = original.getHeight();
    int newSizeX = (int) Math.ceil(sizex / squareSize) * squareSize;
    int newSizeY = (int) Math.ceil(sizey / squareSize) * squareSize;
    BufferedImage newImg = new BufferedImage(newSizeX, newSizeY, original.getType());

    for (int i = 0; i < sizex; i += squareSize) {
      for (int j = 0; j < sizey; j += squareSize) {
        Color[] colors = getPixelRange(original, i, j, squareSize);
        Color avg = getMediumColor(colors);
        for (int k = 0; k < squareSize; k++) {
          for (int l = 0; l < squareSize; l++) {
            newImg.setRGB(Math.min(newSizeX - 1, i + k), Math.min(newSizeY - 1, j + l), avg.getRGB());
          }
        }
      }
    }

    return newImg;
  }

  private static Color[] getPixelRange(BufferedImage original, int i, int j, int squareSize) {
    int scanx = i + squareSize > original.getWidth() ? original.getWidth() - i : squareSize;
    int scany = j + squareSize > original.getHeight() ? original.getHeight() - j : squareSize;
    int[] colorsAsInts = original.getRGB(i, j, scanx, scany, null, 0, scanx);

    Color[] colors = new Color[colorsAsInts.length];

    for (int k = 0; k < colorsAsInts.length; k++) {
      colors[k] = new Color(colorsAsInts[k]);
    }

    return colors;
  }

  private static Color getMediumColor(Color[] colors) {
    long r = 0, g = 0, b = 0;
    for (Color c : colors) {
      r += c.getRed();
      g += c.getGreen();
      b += c.getBlue();
    }
    r /= colors.length;
    g /= colors.length;
    b /= colors.length;
    return new Color((int) r, (int) g, (int) b);
  }

  public static BufferedImage toGreyScale(BufferedImage original) {
    int sizex = original.getWidth();
    int sizey = original.getHeight();
    BufferedImage newImg = new BufferedImage(sizex, sizey, original.getType());

    for (int i = 0; i < sizex; i++) {
      for (int j = 0; j < sizey; j++) {
        Color originalColor = new Color(original.getRGB(i, j));
        int gs = originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue();
        gs /= 3;
        newImg.setRGB(i, j, new Color(gs, gs, gs).getRGB());
      }
    }

    return newImg;
  }
}
