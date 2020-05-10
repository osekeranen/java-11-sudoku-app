package sudokuapp.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This is a class for creating a scene for after-game report.
 */
class ReportScene {

    private UiController ui;

    private Label score;

    private TextField nameField;

    /**
     * Constructs an object for creating the after-action report.
     * 
     * @param controller the controller that controls ui
     */
    public ReportScene(UiController controller) {
        this.ui = controller;
    }

    /**
     * Returns a scene containing the after-action report.
     * 
     * @return the scene that contains the after-action report
     */
    public Scene getScene() {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Label header = new Label("Congratulations!");
        header.setFont(Font.font(36));
        layout.getChildren().add(header);

        score = new Label("Your score: ");
        score.setFont(Font.font(24));
        layout.getChildren().add(score);

        Pane spacer = new Pane();
        spacer.setMinHeight(20);
        layout.getChildren().add(spacer);

        layout.getChildren().add(this.getNameField());
        layout.getChildren().add(this.getSubmitButton());

        Scene scene = new Scene(layout, 336, 403);
        return scene;
    }

    /**
     * Updates the the header to show right amount of points.
     */
    public void refresh() {
        score.setText("Your score: " + ui.getScore());
    }

    private TextField getNameField() {
        nameField = new TextField();
        nameField.setMaxSize(188, 46);
        nameField.setMinSize(188, 46);
        nameField.setFont(Font.font(24));
        nameField.setPromptText("Your name");
        nameField.setAlignment(Pos.CENTER);

        return nameField;

    }

    private Button getSubmitButton() {
        Button submit = new Button("Submit");
        submit.setFont(Font.font(16));

        submit.setOnAction(e -> {
            ui.addHiscore(nameField.getText());
            ui.switchToMenuScene();
        });

        return submit;
    }
}
