package no.ntnu;

import javax.swing.*;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println( "Hello World!" );

        //Changing the default look of JFileChooser
        //Reference: https://stackoverflow.com/questions/2282211/windows-look-and-feel-for-jfilechooser
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Starting with reading a csv file
        CSVReader reader = new CSVReader();

        //you can get the sudoku like this:
        System.out.println(Arrays.deepToString(reader.getSudoku()));

    }
}
