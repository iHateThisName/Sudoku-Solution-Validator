package no.ntnu;


import java.util.HashSet;

public class SudokuValidator {

    String[][] board;

    public SudokuValidator(String[][] sudoku) {

        board = sudoku;

        isValid();
    }

    public boolean isValid() {
        HashSet<String> seen = new HashSet();

        for(int row = 0; row <9; row++) {

            for(int column = 0; column <9; column++) {
                

                String currentValue = board [row][column];
                char charValue = currentValue.charAt(0);
                if (charValue != '.') {
                    //this works because the add method checks wether the item already is in the hash set or not. and returns false if the element already is present.
                    if(!seen.add(currentValue + "found in row" + row) ||
                            !seen.add(currentValue + "found in column" + column) ||
                            !seen.add(currentValue + "found in 3x3" + row/3 + "-" + column/3)) {

                        System.out.println("This board is not solvable");
                        return false;
                    }

                }
            }
        }
        System.out.println("this board is solvable");
        return true;
    }
}
