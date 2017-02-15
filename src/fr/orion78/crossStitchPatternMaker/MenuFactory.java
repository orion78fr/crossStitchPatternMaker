package fr.orion78.crossStitchPatternMaker;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFactory {
  private static final Map<String, List<Pair<String, ActionListener>>> menus = new HashMap<>();

  static {
    menus.put("File", Arrays.asList(
      new Pair<>("Load", (event) -> {
        JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
        int ret = fc.showDialog(MainWindow.INSTANCE, "Choose");
        if (ret == JFileChooser.APPROVE_OPTION) {
          try {
            MainWindow.INSTANCE.setImage(ImageIO.read(fc.getSelectedFile()));
          } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.INSTANCE, "File can't be opened");
          }
        }
      }),
      new Pair<>("Save as png", (event) -> {
        try {
          String name = JOptionPane.showInputDialog("Enter file name");
          File file = new File(System.getProperty("user.home"), name + ".png");
          if (file.exists()) {
            JOptionPane.showMessageDialog(MainWindow.INSTANCE, "File already exists");
          }
          if (!ImageIO.write(MainWindow.INSTANCE.getCurrentImage(), "png", file)) {
            JOptionPane.showMessageDialog(MainWindow.INSTANCE, "No writer found for png image");
          } else {
            JOptionPane.showMessageDialog(MainWindow.INSTANCE, "File saved as " + file.getAbsolutePath());
          }
        } catch (IOException e) {
          JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Invalid name");
        }
      }),
      new Pair<>("Exit", (event) -> System.exit(0))
    ));

    menus.put("Operations", Arrays.asList(
      new Pair<>("Set square size", (event) -> {
        String value = JOptionPane.showInputDialog(MainWindow.INSTANCE, "Choose a square size");
        if (value.trim().isEmpty()) {
          JOptionPane.showMessageDialog(MainWindow.INSTANCE, "You must enter a number");
          return;
        }
        int intVal;
        try {
          intVal = Integer.parseInt(value);
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Invalid number");
          return;
        }

        MainWindow.INSTANCE.setSquareSize(intVal);
      }),
      new Pair<>("To greyscale", (event) -> MainWindow.INSTANCE.toGreyScale()),
      new Pair<>("Set number of grayscale", (event) -> {
        String value = JOptionPane.showInputDialog(MainWindow.INSTANCE, "Choose number of greyscale");
        if (value.trim().isEmpty()) {
          JOptionPane.showMessageDialog(MainWindow.INSTANCE, "You must enter a number");
          return;
        }
        int intVal;
        try {
          intVal = Integer.parseInt(value);
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Invalid number");
          return;
        }

        MainWindow.INSTANCE.setNumberOfGreyscale(intVal);
      })
    ));
  }

  public static JMenuBar create() {
    JMenuBar result = new JMenuBar();

    for (Map.Entry<String, List<Pair<String, ActionListener>>> e : menus.entrySet()) {
      JMenu menu = new JMenu(e.getKey());

      for (Pair<String, ActionListener> p : e.getValue()) {
        JMenuItem item = new JMenuItem(p.getKey());
        item.addActionListener(p.getValue());
        menu.add(item);
      }

      result.add(menu);
    }

    return result;
  }
}
