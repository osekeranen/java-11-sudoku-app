package sudokuapp.ui;

import java.util.Collections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * 
 */
public class MenuScene {
    private UiController ui;

    public MenuScene(UiController ui) {
        this.ui = ui;
    }
    
    public Scene getScene() {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(40);
        
        Label header = new Label("Sudoku");
        header.setFont(Font.font(72));
        layout.getChildren().add(header);
        
        layout.getChildren().add(this.getButtons());
        
        Scene scene = new Scene(layout, 336, 403);
        return scene;
    }
    
    private Parent getButtons() {
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        
        buttons.getChildren().add(this.getPlayButton());
        buttons.getChildren().add(this.getHiscoreButton());
        buttons.getChildren().add(this.getExitButton());
        
        return buttons;
    }
    
    private Button getPlayButton() {
        Button play = new Button("Play!");
        play.setFont(Font.font(24));
        play.setMinSize(135, 46);
        play.setMaxSize(135, 46);
        
        play.setOnAction(e -> {
            ui.switchToDifficultyScene();
        });
        
        return play;
    }
    
    private Button getHiscoreButton() {
        Button hiscores = new Button("Hiscores");
        hiscores.setFont(Font.font(24));
        hiscores.setMinSize(135, 46);
        hiscores.setMaxSize(135, 46);
        
        hiscores.setOnAction(e -> {
            ui.switchToHiscoreScene();
        });
        
        return hiscores;
    }
    
    private Button getExitButton() {
        Button exit = new Button("Exit");
        exit.setFont(Font.font(24));
        exit.setMinSize(135, 46);
        exit.setMaxSize(135, 46);
        
        exit.setOnAction(e -> {
            ui.exit();
        });
        
        return exit;
    }
}
