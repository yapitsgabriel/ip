package atlas.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import atlas.Atlas;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Atlas atlas;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image atlasImage = new Image(this.getClass().getResourceAsStream("/images/DaAtlas.png"));

    /**
     * Initialise the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Atlas instance
     *
     * @param a The Atlas instance.
     */
    public void setAtlas(Atlas a) {
        atlas = a;
        atlas.loadData();
        dialogContainer.getChildren().addAll(DialogBox.getAtlasDialog(atlas.hello(), atlasImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Atlas's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = atlas.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getAtlasDialog(response, atlasImage)
        );
        userInput.clear();
    }
}
