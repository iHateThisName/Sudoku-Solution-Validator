package no.ntnu;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CSVReader {

    //Creating a filter so the user have the option to select a csv file
    FileFilter filter = new FileNameExtensionFilter("CSV file", "csv");
    //Retrieve information on current directory
    File workingDir = new File(System.getProperty("user.dir"));

    private static final int numberOfColumns = 9;
    private static final int numberOfRows = 9;

    private String[][] sudoku;

    public CSVReader() {

        //Retrieving the csv file
        File csv = retrieveCSVFile();

        if (csv == null){
            //There is no file to read
            System.out.println("Something went wrong when trying to retrieve the sudoku puzzle");
        } else {
            //Read the csv file
            sudoku = readFile(csv);
        }

    }
    private File retrieveCSVFile() {

        //Creating a file chooser
        JFileChooser fileChooser = new JFileChooser();
        //Setting this option to false, so the user can't select a non csv file
        fileChooser.setAcceptAllFileFilterUsed(false);
        //Setting the file choosers filter
        fileChooser.setFileFilter(filter);
        //Setting the file chooser starting point
        fileChooser.setCurrentDirectory(workingDir);
        //The file chooser will open up and return a value that tells us what the user did
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            return fileChooser.getSelectedFile();
        } else {
            if (returnVal == JFileChooser.CANCEL_OPTION) {
                //The user selected the cancel button
                System.out.println("User Select Cancel");
                return null;
            }
        }
        return null;
    }

    private String[][] readFile(File csv) {

        //A single number in the sudoku puzzle
        String sudokuCell;
        //Creating an array in an array that represent the sudoku puzzle
        String[][] sudoku = new String[numberOfColumns][numberOfRows];
        //keeps track of the row
        int i = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(csv));

            while ((sudokuCell = bufferedReader.readLine()) != null) {

                String[] rowString = sudokuCell.split(",");

                sudoku[i] = rowString;

                i = i + 1;

            }

        } catch (Exception e) {
            e.printStackTrace();
            //this should never happen
            //there is a fail-safe earlier in the code
        }
        return sudoku;
    }

    public String[][] getSudoku() {
        return sudoku;
    }
}
