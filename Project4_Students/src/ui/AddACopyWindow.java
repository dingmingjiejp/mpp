package ui;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import business.Author;
import business.Book;
import business.BookCopy;
import business.ControllerFactory;
import business.ControllerInterface;
import dataaccess.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddACopyWindow extends Stage implements LibWindow{

	public static final AddACopyWindow INSTANCE = new AddACopyWindow();

	private boolean isInitialized = false;
	private User user;
	private HashMap<String,Book> booksMap;
	private TableView<Book> tbv;
	private TextField txtIsbn;

	public AddACopyWindow() {
	}

	public void setData(User user, HashMap<String,Book> booksMap) {
		this.user = user;
		this.booksMap = booksMap;
	}

	@Override
	public void init() {
		try {
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER_LEFT);
	        grid.setHgap(20);
	        grid.setVgap(20);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        VBox hbLeft = new VBox(10);
	        VBox hbRight = new VBox();
	        VBox hbBottom = new VBox(10);
	        hbLeft.setAlignment(Pos.TOP_LEFT);
	        hbBottom.setAlignment(Pos.BOTTOM_CENTER);

	        tbv = new TableView<>();
	        hbRight.getChildren().add(tbv);
	        initBookListView(tbv);

	        Text scenetitle = new Text("Add a book copy");
	        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma

	        grid.add(scenetitle, 0, 0, 2, 1);
	        grid.add(hbLeft, 0, 1);
	        grid.add(hbRight, 1, 1);
	        grid.add(hbBottom, 0, 2, 2, 1);


	        //Scene scene = new Scene(grid, 300, 200);
	        Scene scene = new Scene(grid);
	        setScene(scene);

//	        GridPane gridIsbn = new GridPane();
	        Label lblIsbn = new Label("ISBN: ");
	        hbLeft.getChildren().add(lblIsbn);
//	        gridIsbn.add(lblIsbn, 0, 0);
	        txtIsbn = new TextField();
//	        gridIsbn.add(txtIsbn, 1, 0);
//	        hbLeft.getChildren().add(gridIsbn);
	        hbLeft.getChildren().add(txtIsbn);

	        GridPane gridIsbn = new GridPane();
	        gridIsbn.setHgap(20);
	        gridIsbn.setVgap(20);
	        Button searchBtn = new Button("Search");
			searchBtn.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
	        		searchForBook(txtIsbn.getText());
	        	}
	        });
//			hbLeft.getChildren().add(searchBtn);
			gridIsbn.add(searchBtn, 0, 0);

			Button clearBtn = new Button("Clear search");
			clearBtn.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
	        		txtIsbn.setText("");
	        		refreshBookList();
	        	}
	        });
			gridIsbn.add(clearBtn, 1, 0);
			hbLeft.getChildren().add(gridIsbn);

	        Button btnBack = new Button("< Back");
	        btnBack.setMinSize(150, 20);
	        btnBack.setAlignment(Pos.BOTTOM_LEFT);
	        btnBack.setOnAction((e) -> {
	        	Start.showOperationWindow();
	        });
	        hbBottom.getChildren().add(btnBack);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void searchForBook(String isbn) {
		ControllerInterface controller = ControllerFactory.of();
		HashMap<String, Book> filteredBooks = controller.searchBooks(isbn);
		updateBookListView(filteredBooks);
	}

	private void updateBookListView(HashMap<String, Book> filteredBooks) {
		if (tbv != null) {
    		tbv.getItems().clear();
    		filteredBooks.forEach((r,v) -> {
            	tbv.getItems().add(v);
            });
    	}
	}

	private void initBookListView(TableView<Book> table) {
        // Add extra columns if necessary:
        TableColumn<Book, String> colIsbn = new TableColumn<>("isbn");
        colIsbn.setMinWidth(80);
        colIsbn.setCellValueFactory(data -> {
            Book rowValue = data.getValue();
            String cellValue = rowValue.getIsbn();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colIsbn);

        TableColumn<Book, String> colTitle = new TableColumn<>("title");
        colTitle.setMinWidth(80);
        colTitle.setCellValueFactory(data -> {
            Book rowValue = data.getValue();
            String cellValue = rowValue.getTitle();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colTitle);

        TableColumn<Book, String> colAuthors = new TableColumn<>("authors");
        colAuthors.setMinWidth(80);
        colAuthors.setCellValueFactory(data -> {
            Book rowValue = data.getValue();
            List<Author> authors = rowValue.getAuthors();
            String authorsName = authors.stream().map(AddACopyWindow::getAuthorName ).collect(Collectors.joining(","));
            return new ReadOnlyStringWrapper(authorsName);
        });
        table.getColumns().add(colAuthors);

        TableColumn<Book, String> colCopies = new TableColumn<>("copies");
        colCopies.setMinWidth(80);
        colCopies.setCellValueFactory(data -> {
            Book rowValue = data.getValue();
            BookCopy[] copyies = rowValue.getCopies();
            int availableNum = 0;
            for (BookCopy copy : copyies) {
            	if (copy.isAvailable()) {
            		availableNum ++;
            	}
            }
            return new ReadOnlyStringWrapper( availableNum + "/" + copyies.length);
        });
        table.getColumns().add(colCopies);
        table.setPrefSize(500, 500);
        table.setColumnResizePolicy((param) -> true );
	}

	public static String getAuthorName(Author author) {
		return author.getFirstName() + " " + author.getLastName();
	}


	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

    public void refreshBookList() {
    	if (tbv != null) {
    		tbv.getItems().clear();
            this.booksMap.forEach((r,v) -> {
            	tbv.getItems().add(v);
            });
    	}
    }

}