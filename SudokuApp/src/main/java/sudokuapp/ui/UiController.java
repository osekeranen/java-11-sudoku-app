package sudokuapp.ui;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudokuapp.dao.FileHiscoreDao;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.Hiscore;

/**
 *
 */
public class UiController extends Application {
    private Stage stage;
    
    private MenuScene menuScene;
    private Scene menu;
    
    private HiscoreScene hiscoreScene;
    private Scene hiscore;
    
    private DifficultyScene difficultyScene;
    private Scene difficulties;
    
    private PuzzleScene puzzleScene;
    private Scene puzzle;
    
    private ReportScene reportScene;
    private Scene report;
    
    private Difficulty difficulty;
    private int score;
    
    private FileHiscoreDao hiscoreDao;
    private ArrayList<Hiscore> hiscores;
    
    @Override
    public void start(Stage stage) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String hiscoreFile = properties.getProperty("hiscoreFile");
        hiscoreDao = new FileHiscoreDao(hiscoreFile);
        
        menuScene = new MenuScene(this);
        menu = menuScene.getScene();
        
        hiscoreScene = new HiscoreScene(this);
        hiscore = hiscoreScene.getScene();
        
        difficultyScene = new DifficultyScene(this);
        difficulties = difficultyScene.getScene();
        
        puzzleScene = new PuzzleScene(this);
        puzzle = puzzleScene.getScene();
        
        reportScene = new ReportScene(this);
        report = reportScene.getScene();
        
        this.stage = stage;
        
        this.stage.setMinWidth(336);
        this.stage.setMaxWidth(336);
        this.stage.setMinHeight(403);
        this.stage.setMaxHeight(403);
        this.stage.setTitle("Sudoku");
        this.stage.setScene(menu);
        this.stage.show();
    }
    
    public void switchToMenuScene() {
        stage.setScene(menu);
    }
    
    public void switchToHiscoreScene() {
        stage.setScene(hiscore);
    }
    
    public void switchToDifficultyScene() {
        stage.setScene(difficulties);
    }
    
    public void switchToPuzzleScene() {
        puzzleScene.refresh();
        stage.setScene(puzzle);
    }
    
    public void switchToReportScene() {
        reportScene.refresh();
        stage.setScene(report);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void addHiscore(String name) {
        try {
            hiscoreDao.add(new Hiscore(difficulty, score, name));
        } catch (Exception e) {
            
        }
    }
    
    public void refreshHiscores() {
        hiscores = hiscoreDao.getAll();
        Collections.sort(hiscores);
    }

    public ArrayList<Hiscore> getHiscores() {
        this.refreshHiscores();
        return hiscores;
    }
    
    public void exit() {
        stage.close();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
