package sudokuapp.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokuapp.logic.SudokuSolver;

/**
 *
 * @author okeranen
 */
public class SudokuSolverTest {
    SudokuSolver solver;
    int[][] sudoku;
    
    @Before
    public void setUp() {
        solver = new SudokuSolver();
        sudoku = solver.solve(0, 0, new int[9][9]);
    }
    
    @Test
    public void gridIsNineByNine() {
        int cells = 0;
        
        for (int i = 0; i < sudoku.length; i++) {
            cells += sudoku[i].length;
        }
        
        assertEquals(81, cells);
    }
    
    @Test
    public void eachNumberAppearsNineTimes() {
        int sum = 0;
        
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                sum += sudoku[i][j];
            }
        }
        
        assertEquals(405, sum);
    }
    
    @Test
    public void eachNumberAppearsOnceInRow() {
        for (int i = 0; i < sudoku.length; i++) {
            int sum = 0;
            
            for (int j = 0; j < sudoku[i].length; j++) {
                sum += sudoku[i][j];
            }
            
            assertEquals(45, sum);
        }
    }
    
    @Test
    public void eachNumberAppearsOnceInColumn() {
        for (int i = 0; i < sudoku.length; i++) {
            int sum = 0;
            
            for (int j = 0; j < sudoku[i].length; j++) {
                sum += sudoku[j][i];
            }
            
            assertEquals(45, sum);
        }
    }
    
    // TODO refactor the next test so it doesn't repeat itself
    
    @Test
    public void eachNumberAppearsOnceInSquare() {
        // 1st square
        int sum = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 2nd square
        sum = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 3rd square
        sum = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 9; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 4th square
        sum = 0;
        
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 5th square
        sum = 0;
        
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 6th square
        sum = 0;
        
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 7th square
        sum = 0;
        
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 8th square
        sum = 0;
        
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
        
        // 9th square
        sum = 0;
        
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                sum += sudoku[j][i];
            }
        }
        
        assertEquals(45, sum);
    }
    
    @Test
    public void emptySudokuHasCorrectAmountClues() {
        int[][] emptySudoku = solver.desolve(sudoku, 17);
        
        int clues = 0;
        for (int i = 0; i < emptySudoku.length; i++) {
            for (int j = 0; j < emptySudoku[0].length; j++) {
                if (emptySudoku[i][j] != 0) {
                    clues++;
                }
            }
        }
        
        assertEquals(17, clues);
    }
}