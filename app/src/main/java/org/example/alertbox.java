package org.example;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class alertbox {

  public static void display(String title , String message) {
    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL); // ignore main actions in the main window
    window.setTitle(title);
    window.setWidth(300);
    window.setHeight(300);

    Label label = new Label();
    label.setText(message);
    Button closeButton = new Button("Close");
    closeButton.setOnAction(e -> window.close());

    VBox layout = new VBox();
    layout.getChildren().addAll(label, closeButton);
    layout.setAlignment(Pos.CENTER);
    layout.setSpacing(30);
    Scene scene = new Scene(layout);
    window.setScene(scene);

    window.showAndWait(); // before returns it needs to be closed
  }
}
