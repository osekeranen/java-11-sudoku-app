package sudokuapp.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import sudokuapp.logic.SudokuGenerator;

public class SudokuUi extends Application {
    private Scene sudokuScene;
    
    private GridPane sudokuGrid;
    private HBox sudokuButtons;
    private VBox sudokuLayout;
    
    private HashMap<Integer, GridPane> gridMap;
    private HashMap<Integer, Button> cellMap;
    
    private int[][] completeSudoku;
    private int[][] emptySudoku;
    
    @Override
    public void start(Stage stage) {
        sudokuGrid = new GridPane();
        sudokuGrid.setHgap(10);
        sudokuGrid.setVgap(10);
        sudokuGrid.setPadding(new Insets(10, 10, 10, 10));
        
        this.generateSudoku(30);
        this.createGrid();
        
        Button emptySudoku = new Button("E");
        emptySudoku.setFont(Font.font("Monospaced", 16));
        emptySudoku.setOnAction(e -> {
            
        });
        
        Button checkSudoku = new Button("C");
        checkSudoku.setFont(Font.font("Monospaced", 16));
        checkSudoku.setOnAction(e ->{
            
        });
        
        Button newSudoku = new Button("N");
        newSudoku.setFont(Font.font("Monospaced", 16));
        newSudoku.setOnAction(e ->{
            this.generateSudoku(30);
            this.createGrid();
        });
        
        Pane sudokuButtonSpacer = new Pane();
        HBox.setHgrow(sudokuButtonSpacer, Priority.ALWAYS);
        
        sudokuButtons = new HBox();
        sudokuButtons.setPadding(new Insets(10, 10, 0, 10));
        sudokuButtons.getChildren().addAll(sudokuButtonSpacer, emptySudoku, checkSudoku, newSudoku);
        
        sudokuLayout = new VBox();
        
        sudokuLayout.getChildren().add(sudokuButtons);
        sudokuLayout.getChildren().add(sudokuGrid);
        
        sudokuScene = new Scene(sudokuLayout);
        
        stage.setTitle("Sudoku");
        stage.setScene(sudokuScene);
        stage.show();
    }
    
    private void generateSudoku(int clues) {
        SudokuGenerator generator = new SudokuGenerator();
        completeSudoku = generator.generateSudoku();
        emptySudoku = generator.generateEmptySudoku(completeSudoku, clues);
    }
    
    private void createGrid() {
        gridMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            GridPane gridpane = new GridPane();
            
            gridMap.put(i, gridpane);
            sudokuGrid.add(gridpane, i % 3, i / 3);
        }
        
        cellMap = new HashMap<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSudokuCell(y, x);
            }
        }
    }
    
    private void addSudokuCell(int y, int x) {
        if (emptySudoku[y][x] == 0) {
            Button button = new Button(" ");
            button.setFont(Font.font("Monospaced", 16));
            
            button.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.DIGIT1) {
                    button.setText("1");
                } else if (e.getCode() == KeyCode.DIGIT2) {
                    button.setText("2");
                } else if (e.getCode() == KeyCode.DIGIT3) {
                    button.setText("3");
                } else if (e.getCode() == KeyCode.DIGIT4) {
                    button.setText("4");
                } else if (e.getCode() == KeyCode.DIGIT5) {
                    button.setText("5");
                } else if (e.getCode() == KeyCode.DIGIT6) {
                    button.setText("6");
                } else if (e.getCode() == KeyCode.DIGIT7) {
                    button.setText("7");
                } else if (e.getCode() == KeyCode.DIGIT8) {
                    button.setText("8");
                } else if (e.getCode() == KeyCode.DIGIT9) {
                    button.setText("9");
                } else if (e.getCode() == KeyCode.BACK_SPACE) {
                    button.setText(" ");
                }
            });
            
            int grid = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
            
            cellMap.put(y * 9 + x, button);
            gridMap.get(grid).add(button, x, y);
        } else {
            Button button = new Button("" + emptySudoku[y][x]);
            button.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
            
            int grid = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
            
            cellMap.put(y * 9 + x, button);
            gridMap.get(grid).add(button, x, y);
        }
    }
}
