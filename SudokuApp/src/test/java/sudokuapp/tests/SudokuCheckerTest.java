package sudokuapp.tests;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokuapp.logic.SudokuChecker;
import sudokuapp.logic.SudokuSolver;

public class SudokuCheckerTest {
    SudokuSolver solver;
    int[][] sudoku;
    SudokuChecker checker;
    ArrayList<Integer> incorrectCells;
    
    @Before
    public void setUp() {
        solver = new SudokuSolver();
        sudoku = solver.solve(0, 0, new int[9][9]);
        checker = new SudokuChecker();
    }
    
    @Test
    public void correctSudokuReturnsEmptyList() {
        incorrectCells = checker.checkSudoku(sudoku, sudoku);
        
        assertEquals(true, incorrectCells.isEmpty());
    }
    
    @Test
    public void incorrectSudokuReturnsListWithRightSize() {
        int[][] incorrectSudoku = solver.desolve(sudoku, 80);
        
        incorrectCells = checker.checkSudoku(incorrectSudoku, sudoku);
        
        assertEquals(1, incorrectCells.size());
    }
}