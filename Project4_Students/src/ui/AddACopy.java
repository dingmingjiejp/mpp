package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddACopy extends Stage implements LibWindow {
	public static final AddACopy INSTANCE = new AddACopy();
	public static HashMap<String, Book> bookList;

	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private TextField txtIsbn;
	private TableView<Book> table;
	private Button addBtn;

	private AddACopy() {}

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add a copy");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 4, 1);

        Label lblIsbn = new Label("Insert ISBN: ");
        grid.add(lblIsbn, 0, 1);

		txtIsbn = new TextField();
//		txtIsbn.textProperty().addListener((observable, oldValue, newValue) -> {
//			updateTableItems(newValue);
//		});
		grid.add(txtIsbn, 1, 1);

		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		updateTableItems(txtIsbn.getText());
        	}
        });
		grid.add(searchBtn, 2, 1);

		Button clearBtn = new Button("Clear search");
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		txtIsbn.setText("");
        		resetItems();
        	}
        });
		grid.add(clearBtn, 3, 1);

		Label lblBookList = new Label("Book list");
		lblBookList.setFont(new Font("Arial", 20));
		grid.add(lblBookList, 0, 2, 4, 1);

		table = new TableView<Book>();
		table.setEditable(false);
		TableColumn isbnCol = new TableColumn("ISBN");
		isbnCol.setMinWidth(50);
		isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		isbnCol.setMinWidth(100);
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        TableColumn authorsCol = new TableColumn("Author(s)");
        authorsCol.setMinWidth(80);
        authorsCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorList"));
        TableColumn copyNumCol = new TableColumn("Copy Num");
        copyNumCol.setMinWidth(50);
        copyNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("numCopies"));
        table.getColumns().addAll(isbnCol, titleCol, authorsCol, copyNumCol);
        grid.add(table, 0, 3, 4, 1);

        addBtn = new Button("Add a copy");
        addBtn.setDisable(true);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Book book = table.getSelectionModel().getSelectedItem();
        		if(book == null) {
        			Alert alert = new Alert(AlertType.ERROR, "Select the book to add a new copy!", ButtonType.OK, ButtonType.CANCEL);
        			alert.show();
        		} else {
        			book.addCopy();
        			resetItems();
        		}
        	}
        });
		grid.add(addBtn, 0, 4, 4, 1);

		Button btnBack = new Button("<= Back to Main");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(btnBack);
        grid.add(hBack, 0, 5, 4, 1);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}

	private void updateTableItems(String newValue) {
		List<Book> list = new ArrayList<>();
		for(Entry<String, Book> e : bookList.entrySet()) {
		    if (e.getKey().startsWith(newValue)) {
		    	list.add(e.getValue());
		    }
		}
		if(list.size() > 0) {
			addBtn.setDisable(false);
		} else {
			addBtn.setDisable(true);
		}
		ObservableList<Book> data = FXCollections.observableArrayList(list);
		table.setItems(data);
	}
	public void resetItems() {
		if(bookList.size() > 0) {
			addBtn.setDisable(false);
		} else {
			addBtn.setDisable(true);
		}
		ObservableList<Book> data = FXCollections.observableArrayList(bookList.values());
		table.setItems(data);
	}
	public void setBookData(HashMap<String, Book> b) {
		bookList = b;
		resetItems();
	}
}
