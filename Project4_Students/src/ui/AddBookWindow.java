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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.utils.WindowUtils;

public class AddBookWindow extends Stage implements LibWindow {
	public static final AddBookWindow INSTANCE = new AddBookWindow();

	private TextField isbnTextField;
	private TextField titleTextField;
	private TextField authorsTextField;
	private TextField maxCheckoutLensTextField;
	private TextField numOfCopysTextField;
	private List<Author> authorList = new ArrayList<>();

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
    private AddBookWindow () {}
    public void init() {

        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = WindowUtils.createSceneText("Add Book");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label lblISBN = new Label("ISBN:");
        grid.add(lblISBN, 0, 1);

        isbnTextField = new TextField();
        grid.add(isbnTextField, 1, 1);

        Label lblTitle = new Label("Title:");
        grid.add(lblTitle, 0, 2);

        titleTextField = new TextField();
        grid.add(titleTextField, 1, 2);

        Label lblAuthors = new Label("Authors:");
        grid.add(lblAuthors, 0, 3);
        authorsTextField = new TextField();
        authorsTextField.setEditable(false);
        grid.add(authorsTextField, 1, 3);
        Button addAuthorsBtn = new Button("..");
        addAuthorsBtn.setOnAction(r -> {
     	    Start.showAddAuthorWindow(this.authorList);
        });
        grid.add(addAuthorsBtn, 2, 3);


        Label lblMaxCheckoutLens = new Label("Max checkout length(days):");
        grid.add(lblMaxCheckoutLens, 0, 4);
        maxCheckoutLensTextField = new TextField();
        grid.add(maxCheckoutLensTextField, 1, 4);

        Label lblNumOfCopys = new Label("Number of copies:");
        grid.add(lblNumOfCopys, 0, 5);
        numOfCopysTextField = new TextField();
        grid.add(numOfCopysTextField, 1, 5);

        Button addBtn = new Button("Add book");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addBtn);
        grid.add(hbBtn, 1, 6);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 1, 7);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
					ControllerFactory.of().addBook(isbnTextField.getText(),
							titleTextField.getText(),
							maxCheckoutLensTextField.getText(),
							authorList,
							numOfCopysTextField.getText()
							);
        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Successed.");
				} catch (ValidationException e1) {
        			messageBar.setFill(Start.Colors.red);
             	    messageBar.setText(e1.getMessage());
				}
        	}
        });

        Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.showOperationWindow();
        	}
        });

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 8);
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized = true;
    }

    public void reset() {
    	isbnTextField.setText("");
    	titleTextField.setText("");
    	authorsTextField.setText("");
    	maxCheckoutLensTextField.setText("");
    	numOfCopysTextField.setText("");
    	authorList.clear();
    	messageBar.setText("");
    }

    public void setAuthorList(List<Author> authors) {
    	this.authorList.clear();
    	this.authorList.addAll(authors);

    	String d = "";
    	for(Author a : authors) {
    		String t = a.getFirstName() + " " + a.getLastName();
    		if ("".equals(d)) {
        		d = d + t;
    		} else {
        		d = d + "," + t;
    		}
    	}
    	this.authorsTextField.setText(d);
    }
}
