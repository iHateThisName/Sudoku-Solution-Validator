package no.ntnu;

import javax.swing.*;

/**
 * Main application for running the Sudoku Validator.
 *
 */
public class Main extends Thread{

    private static String[][] sudoku;
    private static boolean validStatus = false;



    public static void main(String[] args) {
        /**
         * Changes the default look of JFileChooser
         * Reference: https://stackoverflow.com/questions/2282211/windows-look-and-feel-for-jfilechooser
         */
        //sets the valid check to true- this will be changed if one or more threads find an error.
        validStatus = true;

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Starts with reading a CSV file.
         * Calls on getSudoko method.
         */
        CSVReader reader = new CSVReader();
        sudoku = reader.getSudoku();


        /**
         * Creating a new thread that'll execute the row checker.
         */
        Thread rowChecker = new Thread(new Runnable() {
            @Override
            public void run() {

                //row checker
                for (int row = 0; row < 9; row++){
                    for (int col = 0; col < 8; col++) {
                        for (int col2 = col + 1; col2 < 9; col2++) {

                            if ((sudoku[row][col].equals(sudoku[row][col2]))) {
                                System.out.println("Row Checker: Not solvable " + sudoku[row][col] + " same as: " + sudoku[row][col2]);
                                validStatus = false;
                            }
                        }
                    }
                }
            }
        });

        /**
         * Column checker to be executed by a new thread.
         */

        Thread columnChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                //column checker
                for (int col = 0; col < 9; col++){
                    for (int row = 0; row < 8; row++) {
                        for (int row2 = row + 1; row2 < 9; row2++) {

                            if (sudoku[row][col].equals(sudoku[row2][col])) {
                                //The sudoku is not solvable
                                System.out.println("Column Checker: Not solvable");
                                validStatus = false;
                            }
                        }
                    }
                }

            }

        });

        /**
         * Grid checker to be executed by a new thread.
         */
        Thread gridChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                //gridChecker
                for (int row = 0; row < 9; row += 3) {
                    for (int col = 0; col < 9; col += 3) {
                        //row, col is start of the 3 by 3 grid

                        for (int pos = 0; pos < 8; pos++) {
                            for (int pos2 = pos + 1; pos2 < 9; pos2++) {
                                if (sudoku[row + pos % 3][col + pos / 3].equals(sudoku[row + pos2 % 3][col + pos2 / 3])) {
                                    System.out.println("Grid Checker: Not solvable");
                                    validStatus = false;
                                }
                            }
                        }
                    }
                }
            }
        });

        //Starting all the threads
        rowChecker.start();
        columnChecker.start();
        gridChecker.start();

        //Waiting for all the threads to be finished
        try {
        rowChecker.join();
        columnChecker.join();
        gridChecker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (validStatus) {
            System.out.println("This sudoku is solvable");
        }
        else {
            System.out.println("This sudoku is NOT solvable");
        }

    }

}

