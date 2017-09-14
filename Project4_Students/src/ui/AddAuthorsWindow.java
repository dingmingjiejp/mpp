package ui;

import java.util.ArrayList;
import java.util.List;

import business.Author;
import business.ControllerFactory;
import business.ValidationException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.utils.WindowUtils;

public class AddAuthorsWindow extends Stage implements LibWindow {
	public static final AddAuthorsWindow INSTANCE = new AddAuthorsWindow();

	private TextField firstTextField;
	private TextField lastNameTextField;
	private TextField telephoneTextField;
	private TextField bioTextField;
	private TextField streetTextField;
	private TextField cityTextField;
	private TextField stateTextField;
	private TextField zipTextField;

	private TableView<Author> tbv;

	private List<Author> authors = new ArrayList<>();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}
	public void setInitialized(boolean val) {
		isInitialized = val;
	}
	private Text messageBar = new Text();
	public void clear() {
		messageBar.setText("");
	}
    private AddAuthorsWindow () {}

    public void init() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add(getClass().getSimpleName());

        Text scenetitle = WindowUtils.createSceneText("Add an author");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label lblFirstName = new Label("FirstName:");
        grid.add(lblFirstName, 0, 1);

        firstTextField = new TextField();
        grid.add(firstTextField, 1, 1);

        Label lblLastName = new Label("LastName:");
        grid.add(lblLastName, 0, 2);

        lastNameTextField = new TextField();
        grid.add(lastNameTextField, 1, 2);

        Label lblTelephone = new Label("Telephone:");
        grid.add(lblTelephone, 0, 3);
        telephoneTextField = new TextField();
        grid.add(telephoneTextField, 1, 3);

        Label lblBio = new Label("Bio:");
        grid.add(lblBio, 0, 4);
        bioTextField = new TextField();
        grid.add(bioTextField, 1, 4);

        Label lblAddress = new Label("----------------------- Address -----------------------");
        grid.add(lblAddress, 0, 5,2,1);

        Label lblstreet = new Label("Street:");
    	streetTextField = new TextField();
        grid.add(lblstreet, 0, 6);
        grid.add(streetTextField, 1, 6);

        Label lblCity = new Label("City:");
        cityTextField = new TextField();
        grid.add(lblCity, 0, 7);
        grid.add(cityTextField, 1, 7);

        Label lblState = new Label("State:");
        stateTextField = new TextField();
        grid.add(lblState, 0, 8);
        grid.add(stateTextField, 1, 8);

        Label lblZip = new Label("Zip:");
        zipTextField = new TextField();
        grid.add(lblZip, 0, 9);
        grid.add(zipTextField, 1, 9);

        Button addBtn = new Button("Add/Update Author");
        addBtn.setOnAction( r -> {
        	try {
				Author author = ControllerFactory.of().createAuthor(
						firstTextField.getText(),
						lastNameTextField.getText(),
						telephoneTextField.getText(),
						bioTextField.getText(),
						streetTextField.getText(),
						cityTextField.getText(),
						stateTextField.getText(),
						zipTextField.getText()
						);

				List<Author> temp = new ArrayList<>();
				temp.addAll(this.authors);

				for (Author t : temp) {
					if (t.getFirstName().equals(author.getFirstName())
							&& t.getLastName().equals(author.getLastName())) {
						//replace
						this.authors.remove(t);
					}
				}
				this.authors.add(author);
    			messageBar.setFill(Start.Colors.red);
         	    messageBar.setText("Successed.");
         	    refreshTableView();
			} catch (ValidationException e1) {
    			messageBar.setFill(Start.Colors.red);
         	    messageBar.setText(e1.getMessage());
			}
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addBtn);
        grid.add(hbBtn, 0, 10, 2, 1);

        Button deleteBtn = new Button("Delete Author");
        hbBtn.getChildren().add(deleteBtn);


        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 1, 11);

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
				List<Author> temp = new ArrayList<>();
				temp.addAll(authors);
				for (Author t : temp) {
					if (t.getFirstName().equals(firstTextField.getText())
							&& t.getLastName().equals(lastNameTextField.getText())) {
						authors.remove(t);
					}
				}
         	    refreshTableView();
        	}
        });

        Button backBtn = new Button("<= Back to Add an book");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
         	    Start.showAddBookWindow(authors);
        	}
        });

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 11);

        tbv = WindowUtils.createAuthorListTableView();
        grid.add(tbv, 2, 1, 1, 10);

        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized = true;
    }

    public void setData(List<Author> authors) {
    	this.authors.clear();
    	this.authors.addAll(authors);
    	this.tbv.getItems().clear();
    	this.authors.forEach( r-> this.tbv.getItems().add(r));
    }

    private void refreshTableView() {
    	this.tbv.getItems().clear();
    	this.authors.forEach( r-> this.tbv.getItems().add(r));
    }

    public void reset() {
    	firstTextField.setText("");
    	lastNameTextField.setText("");
    	telephoneTextField.setText("");
    	bioTextField.setText("");
    	streetTextField.setText("");
    	cityTextField.setText("");
    	stateTextField.setText("");
    	zipTextField.setText("");
    	authors.clear();
    	messageBar.setText("");
    }

}
