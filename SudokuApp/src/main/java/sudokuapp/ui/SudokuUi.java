package sudokuapp.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import sudokuapp.dao.FileHiscoreDao;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.Hiscore;
import sudokuapp.logic.ScoreCounter;
import sudokuapp.logic.SudokuChecker;
import sudokuapp.logic.SudokuGenerator;

/**
 * GUI for SudokuApp
 */
public class SudokuUi extends Application {
    private Stage stage;
    
    private Scene menuScene;
    private Scene hiscoreScene;
    private Scene difficultyScene;
    private Scene sudokuScene;
    private Scene congratulationScene;
    
    private VBox menuButtons;
    private VBox menuLayout;
    
    private Label hiscoreDifficultyLabel;
    
    private VBox hiscoreNames;
    private VBox hiscoreScores;
    private BorderPane hiscoreNamesAndScores;
    
    private HashMap<Integer, Label> nameMap;
    private HashMap<Integer, Label> scoreMap;
    
    private HBox hiscoreDifficultyButtons;
    private VBox hiscoreButtons;
    
    private VBox hiscoreLayout;
    
    private VBox difficultyButtons;
    
    private GridPane sudokuGrid;
    private HBox sudokuButtons;
    private VBox sudokuLayout;
    
    private HashMap<Integer, GridPane> gridMap;
    private HashMap<Integer, Button> cellMap;
    
    private int[][] completeSudoku;
    private int[][] emptySudoku;
    
    private VBox congratulationButtons;
    private VBox congratulationLayout;
    
    private Difficulty difficulty;
    
    private ScoreCounter scoreCounter;
    private int score;
    private Label scoreLabel;
    
    private SudokuGenerator generator;
    private SudokuChecker checker;
    
    private FileHiscoreDao hiscoreDao;
    private String hiscoresFile;
    
    private ArrayList<Hiscore> hiscores;
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        String hiscoresFile = "hiscoresFile";
        
        hiscoreDao = new FileHiscoreDao(hiscoresFile);
        
        difficulty = Difficulty.BEGINNER;
        
        generator = new SudokuGenerator();
        checker = new SudokuChecker();
        
        this.makeMenuScene();
        
        this.makeHiscoreScene();
        
        this.makeDifficultyScene();
        
        this.makeSudokuScene();
        
        this.makeCongatulationScene();
        
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
        sudokuLabel.setFont(Font.font(72));
        BorderPane sudokuLabelPane = new BorderPane();
        sudokuLabelPane.setCenter(sudokuLabel);
        sudokuLabelPane.setPadding(new Insets(50, 0, 25, 0));
        
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
        this.makeHiscoreButton();
        this.makeExitButton();
    }
    
    private void makePlayButton() {
        Button playButton = new Button("Play!");
        playButton.setFont(Font.font(24));
        playButton.setMinSize(135, 46);
        playButton.setMaxSize(135, 46);
        
        playButton.setOnAction(e -> {
            stage.setScene(difficultyScene);
        });
        
        menuButtons.getChildren().add(playButton);
    }
    
    private void makeHiscoreButton() {
        Button hiscoreButton = new Button("Hiscores");
        hiscoreButton.setFont(Font.font(24));
        hiscoreButton.setMinSize(135, 46);
        hiscoreButton.setMaxSize(135, 46);
        
        hiscoreButton.setOnAction(e -> {
            hiscores = hiscoreDao.getAll();
            Collections.sort(hiscores);
            this.makeHiscoreScene();
            stage.setScene(hiscoreScene);
        });
        
        menuButtons.getChildren().add(hiscoreButton);
    }
    
    private void makeExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font(24));
        exitButton.setMinSize(135, 46);
        exitButton.setMaxSize(135, 46);
        
        exitButton.setOnAction(e -> {
            stage.close();
        });
        
        menuButtons.getChildren().add(exitButton);
    }
    
    private void makeHiscoreScene() {
        hiscoreDifficultyLabel = new Label("Select difficulty");
        hiscoreDifficultyLabel.setFont(Font.font(36));
        
        hiscoreLayout = new VBox();
        hiscoreLayout.setAlignment(Pos.TOP_CENTER);
        hiscoreLayout.setSpacing(20);
        hiscoreLayout.getChildren().add(hiscoreDifficultyLabel);
        hiscoreLayout.setPadding(new Insets(10, 18, 10, 10));
        
        this.makeHiscoreNames();
        this.makeHiscoreScores();
        
        hiscoreNamesAndScores = new BorderPane();
        hiscoreNamesAndScores.setPadding(new Insets(0, 10, 0, 10));
        hiscoreNamesAndScores.setLeft(hiscoreNames);
        hiscoreNamesAndScores.setRight(hiscoreScores);
        
        hiscoreLayout.getChildren().add(hiscoreNamesAndScores);
        
        this.makeHiscoreButtons();
        hiscoreLayout.getChildren().add(hiscoreButtons);
        
        hiscoreScene = new Scene(hiscoreLayout, 336, 403);
    }
    
    private void makeHiscoreNames() {
        hiscoreNames = new VBox();
        hiscoreNames.setSpacing(10);
        
        nameMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Label nameLabel = new Label("");
            nameLabel.setFont(Font.font(24));
            hiscoreNames.getChildren().add(nameLabel);
            nameMap.put(i, nameLabel);
        }
    }
    
    private void makeHiscoreScores() {
        hiscoreScores = new VBox();
        hiscoreScores.setSpacing(10);
        
        scoreMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Label scoreLabel = new Label("");
            scoreLabel.setFont(Font.font(24));
            hiscoreScores.getChildren().add(scoreLabel);
            scoreMap.put(i, scoreLabel);
        }
    }
    
    private void makeHiscoreButtons() {
        hiscoreDifficultyButtons = new HBox();
        hiscoreDifficultyButtons.setAlignment(Pos.CENTER);
        hiscoreDifficultyButtons.setSpacing(10);
        this.makeBeginnerHiscoresButton();
        this.makeIntermediateHiscoresButton();
        this.makeAdvancedHiscoresButton();
        
        hiscoreButtons = new VBox();
        hiscoreButtons.setAlignment(Pos.CENTER);
        hiscoreButtons.setSpacing(10);
        hiscoreButtons.getChildren().addAll(hiscoreDifficultyButtons);
        
        this.makeHiscoreBackButton();
    }
    
    private void makeBeginnerHiscoresButton() {
        Button beginnerButton = new Button("BEG");
        beginnerButton.setFont(Font.font(16));
        beginnerButton.setMinSize(61, 32);
        beginnerButton.setMaxSize(61, 32);
        
        beginnerButton.setOnAction(e -> {
            hiscoreDifficultyLabel.setText(Difficulty.BEGINNER.toString());
            
            int i = 0;
            
            for (Hiscore hiscore : hiscores) {
                if (i >= 5) {
                    break;
                }
                
                if (hiscore.getDifficulty() == Difficulty.BEGINNER) {
                    nameMap.get(i).setText(hiscore.getName());
                    scoreMap.get(i).setText(String.valueOf(hiscore.getScore()));
                    i++;
                }
            }
            
            while (i < 5) {
                nameMap.get(i).setText("");
                scoreMap.get(i).setText("");
                i++;
            }
        });
        
        hiscoreDifficultyButtons.getChildren().add(beginnerButton);
    }
    
    private void makeIntermediateHiscoresButton() {
        Button intermediateButton = new Button("INT");
        intermediateButton.setFont(Font.font(16));
        intermediateButton.setMinSize(61, 32);
        intermediateButton.setMaxSize(61, 32);
        
        intermediateButton.setOnAction(e -> {
            hiscoreDifficultyLabel.setText(Difficulty.INTERMEDIATE.toString());
            
            int i = 0;
            
            for (Hiscore hiscore : hiscores) {
                if (i >= 5) {
                    break;
                }
                
                if (hiscore.getDifficulty() == Difficulty.INTERMEDIATE) {
                    nameMap.get(i).setText(hiscore.getName());
                    scoreMap.get(i).setText(String.valueOf(hiscore.getScore()));
                    i++;
                }
            }
            
            while (i < 5) {
                nameMap.get(i).setText("");
                scoreMap.get(i).setText("");
                i++;
            }
        });
        
        hiscoreDifficultyButtons.getChildren().add(intermediateButton);
    }
    
    private void makeAdvancedHiscoresButton() {
        Button advancedButton = new Button("ADV");
        advancedButton.setFont(Font.font(16));
        advancedButton.setMinSize(61, 32);
        advancedButton.setMaxSize(61, 32);
        
        advancedButton.setOnAction(e -> {
            hiscoreDifficultyLabel.setText(Difficulty.ADVANCED.toString());
            
            int i = 0;
            
            for (Hiscore hiscore : hiscores) {
                if (i >= 5) {
                    break;
                }
                
                if (hiscore.getDifficulty() == Difficulty.ADVANCED) {
                    nameMap.get(i).setText(hiscore.getName());
                    scoreMap.get(i).setText(String.valueOf(hiscore.getScore()));
                    i++;
                }
            }
            
            while (i < 5) {
                nameMap.get(i).setText("");
                scoreMap.get(i).setText("");
                i++;
            }
        });
        
        hiscoreDifficultyButtons.getChildren().add(advancedButton);
    }
    
    private void makeHiscoreBackButton() {
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(16));
        backButton.setMinSize(61, 32);
        backButton.setMaxSize(61, 32);
        
        backButton.setOnAction(e -> {
            stage.setScene(menuScene);
        });
        
        hiscoreButtons.getChildren().add(backButton);
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
        
        this.generateSudoku(difficulty.getClues());
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
        scoreCounter = new ScoreCounter();
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
        
        this.makeSudokuBackButton();
        
        Pane sudokuButtonSpacer = new Pane();
        HBox.setHgrow(sudokuButtonSpacer, Priority.ALWAYS);
        sudokuButtons.getChildren().add(sudokuButtonSpacer);
        
        this.makeCheckButton();
        this.makeEmptyButton();
        this.makeNewSudokuButton();
    }
    
    private void makeSudokuBackButton() {
        Button back = new Button("\u2190");
        back.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        back.setMinSize(32, 32);
        back.setMaxSize(32, 32);
        back.setOnAction(e ->{
            this.stage.setScene(menuScene);
        });
        
        sudokuButtons.getChildren().add(back);
    }
    
    private void makeCheckButton() {
        Button checkSudoku = new Button("\u2714");
        checkSudoku.setFont(Font.font("Monospaced", 16));
        checkSudoku.setMinSize(32, 32);
        checkSudoku.setMaxSize(32, 32);
        checkSudoku.setOnAction(e ->{
            if (this.checkSudoku()) {
                score = scoreCounter.getScore();
                scoreLabel.setText("Your score: " + score);
                stage.setScene(congratulationScene);
            } else {
                scoreCounter.addFailedCheck();
            }
        });
        
        sudokuButtons.getChildren().add(checkSudoku);
    }
    
    private void makeEmptyButton() {
        Button emptySudoku = new Button("\u2718");
        emptySudoku.setFont(Font.font("Monospaced", 16));
        emptySudoku.setMinSize(32, 32);
        emptySudoku.setMaxSize(32, 32);
        emptySudoku.setOnAction(e -> {
            this.makeGrid();
        });
        
        sudokuButtons.getChildren().add(emptySudoku);
    }
    
    private void makeNewSudokuButton() {
        Button newSudoku = new Button("\u21BB");
        newSudoku.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        newSudoku.setMinSize(32, 32);
        newSudoku.setMaxSize(32, 32);
        newSudoku.setOnAction(e ->{
            this.generateSudoku(difficulty.getClues());
            this.makeGrid();
        });
        
        sudokuButtons.getChildren().add(newSudoku);
    }
    
    private void makeCongatulationScene() {
        Label congratulationLabel = new Label("Congratulations!");
        congratulationLabel.setFont(Font.font(36));
        
        scoreLabel = new Label("Your score: " + 3600);
        scoreLabel.setFont(Font.font(24));
        
        this.makeSubmitHiscoreButtons();
        
        congratulationLayout = new VBox();
        congratulationLayout.setAlignment(Pos.TOP_CENTER);
        congratulationLayout.setPadding(new Insets(80, 0, 0, 0));
        congratulationLayout.setSpacing(10);
        congratulationLayout.getChildren().addAll(congratulationLabel, scoreLabel, congratulationButtons);
        
        congratulationScene = new Scene(congratulationLayout, 336, 403);
    }
    
    private void makeSubmitHiscoreButtons() {
        congratulationButtons = new VBox();
        congratulationButtons.setAlignment(Pos.CENTER);
        congratulationButtons.setPadding(new Insets(30, 0, 0, 0));
        congratulationButtons.setSpacing(10);
        
        TextField yourName = new TextField();
        yourName.setMaxSize(188, 46);
        yourName.setMinSize(188, 46);
        yourName.setPromptText("Your name");
        yourName.setFont(Font.font(24));
        yourName.setAlignment(Pos.CENTER);
        
        Button submitButton = new Button("Submit");
        submitButton.setFont(Font.font(16));
        
        submitButton.setOnAction(e -> {
            try {
                hiscoreDao.add(new Hiscore(difficulty, score, yourName.getText()));
            } catch (Exception ex) {
                
            }
            stage.setScene(menuScene);
        });
        
        congratulationButtons.getChildren().addAll(yourName, submitButton);
    }
}
