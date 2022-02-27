package no.ntnu;

import javax.swing.*;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {


        //Changing the default look of JFileChooser
        //Reference: https://stackoverflow.com/questions/2282211/windows-look-and-feel-for-jfilechooser
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Starting with reading a csv file
        CSVReader reader = new CSVReader();

        //Validating the sudoku puzzle
        SudokuValidator validator = new SudokuValidator(reader.getSudoku());

    }
}
