package sudokuapp.tests;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokuapp.logic.SudokuChecker;
import sudokuapp.logic.SudokuGenerator;

public class SudokuCheckerTest {
    SudokuGenerator generator;
    int[][] sudoku;
    SudokuChecker checker;
    ArrayList<Integer> incorrectCells;
    
    @Before
    public void setUp() {
        generator = new SudokuGenerator();
        sudoku = generator.generateSudoku();
        checker = new SudokuChecker();
    }
    
    @Test
    public void correctSudokuReturnsEmptyList() {
        incorrectCells = checker.checkSudoku(sudoku, sudoku);
        
        assertEquals(true, incorrectCells.isEmpty());
    }
    
    @Test
    public void incorrectSudokuReturnsListWithRightSize() {
        int[][] incorrectSudoku = generator.generateEmptySudoku(sudoku, 80);
        
        incorrectCells = checker.checkSudoku(incorrectSudoku, sudoku);
        
        assertEquals(1, incorrectCells.size());
    }
}
