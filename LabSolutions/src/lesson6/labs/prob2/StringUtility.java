package lesson6.labs.prob2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class StringUtility extends Application {

    private TextField inputField;
    private TextField outputField;

    public static void main(String[] args) {
        launch(args);
    }

    public static String valueOf(int i) {
        return String.valueOf((char) i);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("String Utility");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button countBtn = new Button("Count Letters");
        countBtn.setPrefSize(150, 20);
        countBtn.setAlignment(Pos.CENTER_LEFT);
        countBtn.setOnAction((e) -> {
            String input = inputField.getText();
            if (input != null) {
                outputField.setText(String.valueOf(input.length()));
            } else {
                outputField.setText("0");
            }
        });

        Button revertBtn = new Button("Revers Letters");
        revertBtn.setPrefSize(150, 20);
        revertBtn.setAlignment(Pos.CENTER_LEFT);
        revertBtn.setOnAction((e) -> {
            String input = inputField.getText();
            if (input != null) {
                StringBuffer sb = new StringBuffer(input);

                outputField.setText(sb.reverse().toString());
            }

        });

        Button removeBtn = new Button("Remove Duplicates");
        removeBtn.setPrefSize(150, 20);
        removeBtn.setAlignment(Pos.CENTER_LEFT);
        removeBtn.setOnAction((e) -> {
            String input = inputField.getText();
            if (input != null) {
                outputField.setText(input.chars().mapToObj(StringUtility::valueOf).distinct().collect(Collectors.joining()));
            }
        });

        VBox hbBtn = new VBox(10);

        hbBtn.setAlignment(Pos.CENTER_LEFT);
        hbBtn.getChildren().add(countBtn);
        hbBtn.getChildren().add(revertBtn);
        hbBtn.getChildren().add(removeBtn);

        grid.add(hbBtn, 0, 0);

        VBox hbInput1 = new VBox();
        hbInput1.getChildren().add(new Label("input"));

        inputField = new TextField();
        hbInput1.getChildren().add(inputField);

        hbInput1.getChildren().add(new Label("output"));
        outputField = new TextField();
        hbInput1.getChildren().add(outputField);

        grid.add(hbInput1, 1, 0);

        //Scene scene = new Scene(grid, 300, 200);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
