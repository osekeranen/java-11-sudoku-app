package sudokuapp.ui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.SudokuChecker;
import sudokuapp.logic.SudokuGenerator;

/**
 * User interface for SudokuApp
 */
public class SudokuUi extends Application {
    private Stage stage;
    
    private Scene menuScene;
    private Scene difficultyScene;
    private Scene sudokuScene;
    
    private VBox menuButtons;
    private VBox menuLayout;
    
    private VBox difficultyButtons;
    
    private GridPane sudokuGrid;
    private HBox sudokuButtons;
    private VBox sudokuLayout;
    
    private HashMap<Integer, GridPane> gridMap;
    private HashMap<Integer, Button> cellMap;
    
    private int[][] completeSudoku;
    private int[][] emptySudoku;
    
    private Difficulty difficulty;
    
    private SudokuGenerator generator;
    private SudokuChecker checker;
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        generator = new SudokuGenerator();
        checker = new SudokuChecker();
        
        this.makeMenuScene();
        
        this.makeDifficultyScene();
        difficulty = Difficulty.BEGINNER;
        this.generateSudoku(difficulty.getClues());
        
        this.makeSudokuScene();
        
        this.stage.setMinWidth(336);
        this.stage.setMaxWidth(336);
        this.stage.setMinHeight(403);
        this.stage.setMaxHeight(403);
        this.stage.setTitle("Sudoku");
        this.stage.setScene(menuScene);
        this.stage.show();
    }
    
    private void makeMenuScene() {
        Label sudokuLabel = new Label("Sudoku");
        sudokuLabel.setFont(Font.font("Z003", 80));
        BorderPane sudokuLabelPane = new BorderPane();
        sudokuLabelPane.setCenter(sudokuLabel);
        sudokuLabelPane.setPadding(new Insets(40, 0, 35, 0));
        
        this.makeMenuButtons();
        
        menuLayout = new VBox();
        menuLayout.getChildren().addAll(sudokuLabelPane, menuButtons);
        
        menuScene = new Scene(menuLayout, 336, 403);
    }
    
    private void makeMenuButtons() {
        menuButtons = new VBox();
        menuButtons.setSpacing(10);
        menuButtons.setAlignment(Pos.BOTTOM_CENTER);
        
        this.makePlayButton();
        this.makeExitButton();
    }
    
    private void makePlayButton() {
        Button playButton = new Button("Play!");
        playButton.setFont(Font.font(24));
        playButton.setMinSize(92, 46);
        playButton.setMaxSize(92, 46);
        
        playButton.setOnAction(e -> {
            stage.setScene(difficultyScene);
        });
        
        menuButtons.getChildren().add(playButton);
    }
    
    private void makeExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font(24));
        exitButton.setMinSize(92, 46);
        exitButton.setMaxSize(92, 46);
        
        exitButton.setOnAction(e -> {
            stage.close();
        });
        
        menuButtons.getChildren().add(exitButton);
    }
    
    private void makeDifficultyScene() {
        this.makeDifficultyButtons();
        
        difficultyScene = new Scene(difficultyButtons, 336, 403);
    }
    
    private void makeDifficultyButtons() {
        difficultyButtons = new VBox();
        difficultyButtons.setSpacing(10);
        difficultyButtons.setAlignment(Pos.CENTER);

        
        this.makeLevel1Button();
        this.makeLevel2Button();
        this.makeLevel3Button();
    }
    
    private void makeLevel1Button() {
        Button beginner = new Button(Difficulty.BEGINNER.toString());
        beginner.setFont(Font.font(24));
        beginner.setMinSize(188, 46);
        beginner.setMaxSize(188, 46);
        
        beginner.setOnAction(e -> {
            difficulty = Difficulty.BEGINNER;
            this.generateSudoku(difficulty.getClues());
            this.makeGrid();
            stage.setScene(sudokuScene);
        });
        
        difficultyButtons.getChildren().add(beginner);
    }
    
    private void makeLevel2Button() {
        Button intermediate = new Button(Difficulty.INTERMEDIATE.toString());
        intermediate.setFont(Font.font(24));
        intermediate.setMinSize(188, 46);
        intermediate.setMaxSize(188, 46);
        
        intermediate.setOnAction(e -> {
            difficulty = Difficulty.INTERMEDIATE;
            this.generateSudoku(difficulty.getClues());
            this.makeGrid();
            stage.setScene(sudokuScene);
        });
        
        difficultyButtons.getChildren().add(intermediate);
    }
    
    private void makeLevel3Button() {
        Button Advanced = new Button(Difficulty.ADVANCED.toString());
        Advanced.setFont(Font.font(24));
        Advanced.setMinSize(188, 46);
        Advanced.setMaxSize(188, 46);
        
        Advanced.setOnAction(e -> {
            difficulty = Difficulty.ADVANCED;
            this.generateSudoku(difficulty.getClues());
            this.makeGrid();
            stage.setScene(sudokuScene);
        });
        
        difficultyButtons.getChildren().add(Advanced);
    }
    
    private void makeSudokuScene() {
        sudokuGrid = new GridPane();
        sudokuGrid.setHgap(10);
        sudokuGrid.setVgap(10);
        sudokuGrid.setPadding(new Insets(10, 10, 10, 10));
        
        this.makeGrid();
        
        this.makeSudokuButtons();
        
        sudokuLayout = new VBox();
        
        sudokuLayout.getChildren().add(sudokuButtons);
        sudokuLayout.getChildren().add(sudokuGrid);
        
        sudokuScene = new Scene(sudokuLayout, 336, 403);
    }
    
    private void generateSudoku(int clues) {
        completeSudoku = generator.generateSudoku();
        emptySudoku = generator.generateEmptySudoku(completeSudoku, clues);
    }
    
    private boolean checkSudoku() {
        int[][] sudoku = new int[9][9];
        
        for (int i = 0; i < cellMap.size(); i++) {
            int x = i % 9;
            int y = i / 9;
            
            if (cellMap.get(i).getText().equals(" ")) {
                sudoku[y][x] = 0;
            } else {
                sudoku[y][x] = Integer.valueOf(cellMap.get(i).getText());
            }
        }
        
        ArrayList<Integer> incorrectCells = checker.checkSudoku(sudoku, completeSudoku);
        
        this.paintSudokuCells(incorrectCells);
        
        return incorrectCells.isEmpty();
    }
    
    private void paintSudokuCells(ArrayList<Integer> incorrectCells) {
        for (Integer i : incorrectCells) {
            cellMap.get(i).setStyle("-fx-text-fill: red");
        }
    }

    private void makeGrid() {
        gridMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            GridPane gridpane = new GridPane();
            
            gridMap.put(i, gridpane);
            sudokuGrid.add(gridpane, i % 3, i / 3);
        }
        
        cellMap = new HashMap<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.makeSudokuCell(y, x);
            }
        }
    }
    
    private void makeSudokuCell(int y, int x) {
        if (emptySudoku[y][x] == 0) {
            Button button = new Button(" ");
            button.setFont(Font.font("Monospaced", 16));
            
            
            button.setOnKeyPressed(e -> {
                button.setStyle("-fx-text-fill: black");
                
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
    
    private void makeSudokuButtons() {
        sudokuButtons = new HBox();
        sudokuButtons.setPadding(new Insets(10, 18, 0, 10));
        
        this.makeBackButton();
        
        Pane sudokuButtonSpacer = new Pane();
        HBox.setHgrow(sudokuButtonSpacer, Priority.ALWAYS);
        sudokuButtons.getChildren().add(sudokuButtonSpacer);
        
        this.makeCheckButton();
        this.makeEmptyButton();
        this.makeNewSudokuButton();
    }
    
    private void makeBackButton() {
        Button back = new Button("\u2190");
        back.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        back.setOnAction(e ->{
            this.stage.setScene(menuScene);
        });
        
        sudokuButtons.getChildren().add(back);
    }
    
    private void makeCheckButton() {
        Button checkSudoku = new Button("\u2714");
        checkSudoku.setFont(Font.font("Monospaced", 16));
        checkSudoku.setOnAction(e ->{
            if (this.checkSudoku()) {
                System.out.println("Congratulations!");
            }
        });
        
        sudokuButtons.getChildren().add(checkSudoku);
    }
    
    private void makeEmptyButton() {
        Button emptySudoku = new Button("\u2718");
        emptySudoku.setFont(Font.font("Monospaced", 16));
        emptySudoku.setOnAction(e -> {
            this.makeGrid();
        });
        
        sudokuButtons.getChildren().add(emptySudoku);
    }
    
    private void makeNewSudokuButton() {
        Button newSudoku = new Button("\u21BB");
        newSudoku.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        newSudoku.setOnAction(e ->{
            this.generateSudoku(difficulty.getClues());
            this.makeGrid();
        });
        
        sudokuButtons.getChildren().add(newSudoku);
    }
}
