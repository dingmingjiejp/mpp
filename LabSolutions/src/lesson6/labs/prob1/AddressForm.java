package lesson6.labs.prob1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddressForm extends Application {
    private TextField nameField;
    private TextField streetField;
    private TextField cityField;
    private TextField stateField;
    private TextField zipField;
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Address Form");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        //grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(5);
        
        Label name = new Label("Name");
        grid1.add(name, 0, 0);
        nameField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //nameField.setPrefWidth(80);
        grid1.add(nameField, 0, 1);

        Label street = new Label("Street");
        grid1.add(street, 1, 0);
        streetField = new TextField();
        grid1.add(streetField, 1, 1);
        
        Label city = new Label("City");
        grid1.add(city, 2, 0);
        cityField = new TextField();
        grid1.add(cityField, 2, 1);
        
        grid.add(grid1, 0, 0);
        
        
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(5);
        
        Label state = new Label("State");
        grid2.add(state, 0, 0);
        stateField = new TextField();
        grid2.add(stateField, 0, 1);
        
        Label zip = new Label("Zip");
        grid2.add(zip, 1, 0);
        zipField = new TextField();
        grid2.add(zipField, 1, 1);
        
        grid.add(grid2, 0, 1);
        
        //grid1.setGridLinesVisible(true);
        //grid2.setGridLinesVisible(true);

        
        
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 2);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                printToConsole();
                clearAll();
            }
        });

        //Scene scene = new Scene(grid, 300, 200);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void printToConsole() {
        System.out.println(nameField.getText());
        System.out.println(streetField.getText());
        System.out.println(cityField.getText() + ", " + stateField.getText() + zipField.getText() + "\n");
    }
    
    public void clearAll() {
        nameField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
    }
}