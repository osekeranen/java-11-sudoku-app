package sudokuapp.logic;

import java.util.Random;

public class SudokuGenerator {

    public SudokuGenerator() {
    }
    
    public int[][] generateSudoku() {
        int[][] sudoku = new int[9][9];
        
        sudoku[0] = this.generateRandomisedRow();
        
        for (int i = 1; i < 9; i++) {
            if (i == 3 || i == 6) {
                sudoku[i] = this.shiftRow(sudoku[i - 1], 1);
            } else {
                sudoku[i] = this.shiftRow(sudoku[i - 1], 3);
            }
        }
        
        for (int i = 0; i < 9; i++) {
            this.shuffle(sudoku);
        }
        
        return sudoku;
    }
    
    // generate solveable sudoku with empty spots from complete sudoku
    public int[][] generateEmptySudoku(int[][] sudoku, int clues) {
        int[][] emptySudoku = new int[9][9];
        
        for (int i = 0; i < emptySudoku.length; i++) {
            for (int j = 0; j < emptySudoku[0].length; j++) {
                emptySudoku[i][j] = 0;
            }
        }
        
        Random rgen = new Random();
        
        for (int i = 0; i < clues; i++) {
            int x = rgen.nextInt(sudoku.length);
            int y = rgen.nextInt(sudoku.length);
            
            if (emptySudoku[x][y] == 0) {
                emptySudoku[x][y] = sudoku[x][y];
            } else {
                i--;
                continue;
            }
        }
        
        return emptySudoku;
    }
    
    public void shuffle(int[][] sudoku) {
        Random rgen = new Random();
        
        // shuffling single rows
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int row1 = (i * 3) + rgen.nextInt(2);
                int row2 = (i * 3) + rgen.nextInt(2);
                
                this.swapRow(sudoku, row1, row2);
            }
        }
        
        // shuffling rowblocks
        for (int i = 0; i < 9; i++) {
            int rowblock1 = rgen.nextInt(2);
            int rowblock2 = rgen.nextInt(2);
            
            this.swapRowBlock(sudoku, rowblock1, rowblock2);
        }
        
        // shuffling single columns
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int column1 = (i * 3) + rgen.nextInt(2);
                int column2 = (i * 3) + rgen.nextInt(2);
                
                this.swapColumn(sudoku, column1, column2);
            }
        }
        
        // shuffling columnblocks
        for (int i = 0; i < 9; i++) {
            int columnblock1 = rgen.nextInt(2);
            int columnblock2 = rgen.nextInt(2);
            
            this.swapColumnBlock(sudoku, columnblock1, columnblock2);
        }
    }
    
    private static int[] generateRandomisedRow() {
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
    
    private static int[] shiftRow(int[] line, int howMany) {
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
    
    private void swapRow(int[][] sudoku, int row1, int row2) {
        int[] temp = sudoku[row2];
        sudoku[row2] = sudoku[row1];
        sudoku[row1] = temp;
    }
    
    // rowblocks are groups of three rows
    // rowblock with a value of 0 means rows 0-2, 1 means 3-5, and 2 means 6-8
    private void swapRowBlock(int[][] sudoku, int rowblock1, int rowblock2) {
        for (int i = 0; i < 3; i++) {
            this.swapRow(sudoku, (rowblock1 * 3) + i, (rowblock2 * 3) + i);
        }
    }
    
    private void swapColumn(int[][] sudoku, int column1, int column2) {
        for (int i = 0; i < sudoku[0].length; i++) {
            int temp = sudoku[i][column2];
            sudoku[i][column2] = sudoku[i][column1]; 
            sudoku[i][column1] = temp; 
        }
    }
    
    // columnblocks are groups of three columns
    // columnblock with a value of 0 means columns 0-2, 1 means 3-5, and 2 means 6-8
    private void swapColumnBlock(int[][] sudoku, int columnblock1, int columnblock2) {
        for (int i = 0; i < 3; i++) {
            this.swapColumn(sudoku, (columnblock1 * 3) + i, (columnblock2 * 3) + i);
        }
    }
    
    // for development purposes only
//    public void printSudoku(int[][] sudoku) {
//        for (int i = 0; i < 9; i++) {
//            if (i == 3 || i == 6) System.out.println("---------------------");
//            
//            String line = "";
//            
//            for (int j = 0; j < 9; j++) {
//                if (j == 3 || j == 6) line += " |";
//                line += " " + sudoku[i][j];
//            }
//            
//            System.out.println(line.trim());
//        }
//    }
}
