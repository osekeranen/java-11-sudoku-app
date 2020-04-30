package sudokuapp.ui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.ScoreCounter;
import sudokuapp.logic.SudokuChecker;
import sudokuapp.logic.SudokuSolver;

/**
 * 
 */
public class PuzzleScene {
    private UiController ui;
    
    private SudokuSolver solver;
    private SudokuChecker checker;
    private ScoreCounter counter;
    
    private Difficulty difficulty;
    
    private int[][] sudoku;
    private int[][] completeSudoku;
    
    private HashMap<Integer, GridPane> squareMap;
    private HashMap<Integer, Button> cellMap;

    public PuzzleScene(UiController ui) {
        this.ui = ui;
        
        this.difficulty = Difficulty.BEGINNER;
        solver = new SudokuSolver();
        checker = new SudokuChecker();
        completeSudoku = solver.solve(0, 0, new int[9][9]);
        sudoku = solver.desolve(completeSudoku, difficulty.getClues());
        counter = new ScoreCounter();
    }
    
    public Scene getScene() {
        VBox layout = new VBox();
        layout.getChildren().add(this.getButtons());
        layout.getChildren().add(this.getGrid());
        
        Scene scene = new Scene(layout, 336, 403);
        return scene;
    }
    
    public void refresh() {
        this.difficulty = ui.getDifficulty();
        completeSudoku = solver.solve(0, 0, new int[9][9]);
        sudoku = solver.desolve(completeSudoku, difficulty.getClues());
        counter = new ScoreCounter();
        
        for  (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button cell = this.getCell(y, x);
                
                int square = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
                squareMap.get(square).add(cell, x, y);
                
                cellMap.put(y * 9 + x, cell);
            }
        }
    }
    
    private boolean check() {
        int[][] userSudoku = new int[9][9];
        
        for (int i = 0; i < cellMap.size(); i++) {
            int x = i % 9;
            int y = i / 9;
            
            if (cellMap.get(i).getText().equals(" ")) {
                userSudoku[y][x] = 0;
            } else {
                userSudoku[y][x] = Integer.valueOf(cellMap.get(i).getText());
            }
        }
        
        ArrayList<Integer> mistakes = checker.checkSudoku(userSudoku, completeSudoku);
        
        for (Integer i : mistakes) {
            cellMap.get(i).setStyle("-fx-text-fill: red");
        }
        
        return mistakes.isEmpty();
    }
    
    private void empty() {
        for (int i = 0; i < cellMap.size(); i++) {
            int y = i / 9;
            int x = i % 9;
            
            if (sudoku[y][x] == 0) {
                cellMap.get(i).setText(" ");
            }
        }
    }
    
    private Parent getGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        squareMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            GridPane square = new GridPane();
            
            grid.add(square, i % 3, i / 3);
            squareMap.put(i, square);
        }
        
        cellMap = new HashMap<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button cell = this.getCell(y, x);
                
                int square = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
                squareMap.get(square).add(cell, x, y);
                
                cellMap.put(y * 9 + x, cell);
            }
        }
        
        return grid;
    }
    
    private Button getCell(int y, int x) {
        Button cell = new Button();
        
        if (sudoku[y][x] == 0) {
            cell.setText(" ");
            cell.setFont(Font.font("Monospaced", 16));
            
            cell.setOnKeyPressed(e -> {
                cell.setStyle("-fx-text-fill: black");
                
                if (e.getCode() == KeyCode.DIGIT1) {
                    cell.setText("1");
                } else if (e.getCode() == KeyCode.DIGIT2) {
                    cell.setText("2");
                } else if (e.getCode() == KeyCode.DIGIT3) {
                    cell.setText("3");
                } else if (e.getCode() == KeyCode.DIGIT4) {
                    cell.setText("4");
                } else if (e.getCode() == KeyCode.DIGIT5) {
                    cell.setText("5");
                } else if (e.getCode() == KeyCode.DIGIT6) {
                    cell.setText("6");
                } else if (e.getCode() == KeyCode.DIGIT7) {
                    cell.setText("7");
                } else if (e.getCode() == KeyCode.DIGIT8) {
                    cell.setText("8");
                } else if (e.getCode() == KeyCode.DIGIT9) {
                    cell.setText("9");
                } else if (e.getCode() == KeyCode.BACK_SPACE) {
                    cell.setText(" ");
                }
            });
        } else {
            cell.setText("" + sudoku[y][x]);
            cell.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        }
        
        return cell;
    }
    
    private Parent getButtons() {
        HBox buttons = new HBox();
        buttons.setPadding(new Insets(10, 18, 0, 10));
        
        buttons.getChildren().add(this.getBackButton());
        
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttons.getChildren().add(spacer);
        
        buttons.getChildren().add(this.getCheckSudokuButton());
        buttons.getChildren().add(this.getEmptySudokuButton());
        buttons.getChildren().add(this.getNewSudokuButton());
        
        return buttons;
    }
    
    private Button getBackButton() {
        Button back = new Button("\u2190");
        back.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        back.setMinSize(32, 32);
        back.setMaxSize(32, 32);
        back.setOnAction(e ->{
            ui.switchToMenuScene();
        });
        
        return back;
    }
    
    private Button getCheckSudokuButton() {
        Button checkSudoku = new Button("\u2714");
        checkSudoku.setFont(Font.font("Monospaced", 16));
        checkSudoku.setMinSize(32, 32);
        checkSudoku.setMaxSize(32, 32);
        checkSudoku.setOnAction(e ->{
            if (this.check()) {
                ui.setScore(counter.getScore());
                ui.switchToReportScene();
            } else {
                counter.addFailedCheck();
            }
        });
        
        return checkSudoku;
    }
    
    private Button getEmptySudokuButton() {
        Button emptySudoku = new Button("\u2718");
        emptySudoku.setFont(Font.font("Monospaced", 16));
        emptySudoku.setMinSize(32, 32);
        emptySudoku.setMaxSize(32, 32);
        emptySudoku.setOnAction(e -> {
            this.empty();
        });
        
        return emptySudoku;
    }
    
    private Button getNewSudokuButton() {
        Button newSudoku = new Button("\u21BB");
        newSudoku.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        newSudoku.setMinSize(32, 32);
        newSudoku.setMaxSize(32, 32);
        newSudoku.setOnAction(e ->{
            this.refresh();
        });
        
        return newSudoku;
    }
}
