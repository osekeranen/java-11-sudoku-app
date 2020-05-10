package sudokuapp.ui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.Hiscore;

/**
 * This is a class for creating a scene for viewing hiscore.
 */
class HiscoreScene {

    private UiController ui;

    private ArrayList<Hiscore> hiscores;

    private HashMap<Integer, Label> nameMap;
    private HashMap<Integer, Label> scoreMap;

    private Label header;

    private static Font buttonFont = Font.font(16);

    /**
     * Constructs an object for managing the hiscore scene.
     * 
     * @param controller the controller that controls ui
     */
    public HiscoreScene(UiController controller) {
        this.ui = controller;
    }

    /**
     * Returns a scene for viewing hiscore.
     * 
     * @return the scene for viewing hiscore
     */
    public Scene getScene() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(10, 18, 10, 10));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        header = new Label("Select difficulty");
        header.setFont(Font.font(36));
        layout.getChildren().add(header);

        layout.getChildren().add(this.getHiscores());
        layout.getChildren().add(this.getButtons());

        Scene scene = new Scene(layout, 336, 403);
        return scene;
    }

    private Parent getHiscores() {
        BorderPane hiscore = new BorderPane();

        hiscore.setLeft(this.getNames());
        hiscore.setRight(this.getScores());

        return hiscore;
    }

    private Parent getNames() {
        VBox names = new VBox();
        names.setSpacing(10);

        nameMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Label name = new Label("");
            name.setFont(Font.font(24));
            names.getChildren().add(name);
            nameMap.put(i, name);
        }

        return names;
    }

    private Parent getScores() {
        VBox scores = new VBox();
        scores.setSpacing(10);

        scoreMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Label score = new Label("");
            score.setFont(Font.font(24));
            scores.getChildren().add(score);
            scoreMap.put(i, score);
        }

        return scores;
    }

    private Parent getButtons() {
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        buttons.getChildren().add(this.getDifficulties());

        buttons.getChildren().add(this.getBackButton());

        return buttons;
    }

    private Parent getDifficulties() {
        HBox difficulties = new HBox();
        difficulties.setAlignment(Pos.CENTER);
        difficulties.setSpacing(10);

        difficulties.getChildren().add(this.getBeginnerButton());
        difficulties.getChildren().add(this.getIntermediateButton());
        difficulties.getChildren().add(this.getAdvancedButton());

        return difficulties;
    }

    private Button getBeginnerButton() {
        Button beginner = new Button("BEG");
        beginner.setFont(buttonFont);
        beginner.setMinSize(61, 32);
        beginner.setMaxSize(61, 32);

        beginner.setOnAction(e -> {
            this.filterHiscores(Difficulty.BEGINNER);
        });

        return beginner;
    }

    private Button getIntermediateButton() {
        Button intermediate = new Button("INT");
        intermediate.setFont(buttonFont);
        intermediate.setMinSize(61, 32);
        intermediate.setMaxSize(61, 32);

        intermediate.setOnAction(e -> {
            this.filterHiscores(Difficulty.INTERMEDIATE);
        });

        return intermediate;
    }

    private Button getAdvancedButton() {
        Button advanced = new Button("ADV");
        advanced.setFont(buttonFont);
        advanced.setMinSize(61, 32);
        advanced.setMaxSize(61, 32);

        advanced.setOnAction(e -> {
            this.filterHiscores(Difficulty.ADVANCED);
        });

        return advanced;
    }

    private Button getBackButton() {
        Button back = new Button("Back");
        back.setFont(buttonFont);
        back.setMinSize(61, 32);
        back.setMaxSize(61, 32);

        back.setOnAction(e -> {
            header.setText("Select difficulty");

            for (int i = 0; i < 5; i++) {
                nameMap.get(i).setText("");
                scoreMap.get(i).setText("");
            }

            ui.switchToMenuScene();
        });

        return back;
    }

    private void filterHiscores(Difficulty difficulty) {
        hiscores = ui.getHiscores();

        header.setText(difficulty.toString());

        int i = 0;

        for (Hiscore hiscore : hiscores) {
            if (i >= 5) {
                break;
            }

            if (hiscore.getDifficulty() == difficulty) {
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
    }
}
