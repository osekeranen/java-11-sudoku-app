package sudokuapp.logic;

import java.util.Random;

public class SudokuCreator {

    public SudokuCreator() {
    }
    
    public int[][] createSudoku() {
        int[][] sudoku = new int[9][9];
        
        sudoku[0] = this.createRandomisedLine();
        
        for (int i = 1; i < 9; i++) {
            if (i == 3 || i == 6) {
                sudoku[i] = this.shiftLine(sudoku[i - 1], 1);
            } else sudoku[i] = this.shiftLine(sudoku[i - 1], 3);
        }
        
        return sudoku;
    }
    
    private int[] createRandomisedLine() {
        int[] randomisedLine = new int[9];
        
        for (int i = 0; i < randomisedLine.length; i++) {
            randomisedLine[i] = i + 1;
        }
        
        Random rgen = new Random();
        
        for (int i = 0; i < randomisedLine.length; i++) {
            int newPosition = rgen.nextInt(randomisedLine.length);
            int temp = randomisedLine[newPosition];
            randomisedLine[newPosition] = randomisedLine[i];
            randomisedLine[i] = temp;
        }
        
        return randomisedLine;
    }
    
    private int[] shiftLine(int[] line, int howMany) {
        int[] newLine = new int[line.length];
        
        for (int i = 0; i < line.length; i++) {
            int newPosition = i + howMany;
            
            while (newPosition >= line.length) {
                newPosition -= line.length;
            }
            
            newLine[newPosition] = line[i];
        }
        
        return newLine;
    }
    
    public void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) System.out.println("---------------------");
            
            String line = "";
            
            for (int j = 0; j < 9; j++) {
                if (j == 3 || j == 6) line += " |";
                line += " " + sudoku[i][j];
            }
            
            System.out.println(line.trim());
        }
    }
}
