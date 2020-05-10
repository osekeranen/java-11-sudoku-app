package sudokuapp.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sudokuapp.logic.Difficulty;

/**
 * This is a class for creating a scene for selecting difficulty.
 */
public class DifficultyScene {

    private UiController ui;

    private static Font buttonFont = Font.font(24);

    /**
     * Constructs an object for managing the difficulty scene.
     * 
     * @param controller the controller that controls ui
     */
    public DifficultyScene(UiController controller) {
        this.ui = controller;
    }

    /**
     * Returns a scene for selecting difficulty.
     * 
     * @return the scene for selecting difficulty
     */
    public Scene getScene() {
        Scene scene = new Scene(this.getButtons(), 336, 403);
        return scene;
    }

    private Parent getButtons() {
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        buttons.getChildren().add(this.getBeginnerButton());
        buttons.getChildren().add(this.getIntermediateButton());
        buttons.getChildren().add(this.getAdvancedButton());

        return buttons;
    }

    private Button getBeginnerButton() {
        Button beginner = new Button(Difficulty.BEGINNER.toString());
        beginner.setFont(buttonFont);
        beginner.setMinSize(188, 46);
        beginner.setMaxSize(188, 46);

        beginner.setOnAction(e -> {
            ui.setDifficulty(Difficulty.BEGINNER);
            ui.switchToPuzzleScene();
        });

        return beginner;
    }

    private Button getIntermediateButton() {
        Button intermediate = new Button(Difficulty.INTERMEDIATE.toString());
        intermediate.setFont(buttonFont);
        intermediate.setMinSize(188, 46);
        intermediate.setMaxSize(188, 46);

        intermediate.setOnAction(e -> {
            ui.setDifficulty(Difficulty.INTERMEDIATE);
            ui.switchToPuzzleScene();
        });

        return intermediate;
    }

    private Button getAdvancedButton() {
        Button advanced = new Button(Difficulty.ADVANCED.toString());
        advanced.setFont(buttonFont);
        advanced.setMinSize(188, 46);
        advanced.setMaxSize(188, 46);

        advanced.setOnAction(e -> {
            ui.setDifficulty(Difficulty.ADVANCED);
            ui.switchToPuzzleScene();
        });

        return advanced;
    }
}
