package ui;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import business.Author;
import business.Book;
import business.BookCopy;
import dataaccess.Auth;
import dataaccess.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OperationWindow extends  Stage implements LibWindow{

	public static final OperationWindow INSTANCE = new OperationWindow();

	private boolean isInitialized = false;
	private User user;
	private HashMap<String,Book> booksMap;
	private TableView<Book> tbv;

	public OperationWindow() {
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
	        hbLeft.setAlignment(Pos.TOP_LEFT);

	        tbv = new TableView<>();
	        hbRight.getChildren().add(tbv);
	        initBookListView(tbv);

	        Text scenetitle = new Text("Dear " + this.user.getId() +  ", Welcome to library system.");
	        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma

	        grid.add(scenetitle, 0, 0, 2, 1);
	        grid.add(hbLeft, 0, 1);
	        grid.add(hbRight, 1, 1);

	        if (Auth.BOTH.equals(this.user.getAuthorization()) ||
	        		Auth.LIBRARIAN.equals(this.user.getAuthorization()) ){
		        Button checkOutIn = new Button("Check out/Check in");
		        checkOutIn.setMinSize(150, 20);
		        checkOutIn.setAlignment(Pos.CENTER_LEFT);
		        checkOutIn.setOnAction((e) -> {
		        });
		        hbLeft.getChildren().add(checkOutIn);
	        }


	        if (Auth.BOTH.equals(this.user.getAuthorization()) ||
	        		Auth.ADMIN.equals(this.user.getAuthorization()) ){
		        Button addBookCopy = new Button("Add book copy");
		        addBookCopy.setMinSize(150, 20);
		        addBookCopy.setAlignment(Pos.CENTER_LEFT);
		        addBookCopy.setOnAction((e) -> {
		        });

		        Button addMemeber = new Button("Add member");
		        addMemeber.setMinSize(150, 20);
		        addMemeber.setAlignment(Pos.CENTER_LEFT);
		        addMemeber.setOnAction((e) -> {
		        });

		        Button addBook = new Button("Add book");
		        addBook.setMinSize(150, 20);
		        addBook.setAlignment(Pos.CENTER_LEFT);
		        addBook.setOnAction((e) -> {
		        });

		        Button print = new Button("Print");
		        print.setMinSize(150, 20);
		        print.setAlignment(Pos.CENTER_LEFT);
		        print.setOnAction((e) -> {
		        });

		        Button overDueList = new Button("Search overdue book copy");
		        overDueList.setMinSize(150, 20);
		        overDueList.setAlignment(Pos.CENTER_LEFT);
		        overDueList.setOnAction((e) -> {
		        });

		        hbLeft.getChildren().add(addBookCopy);
		        hbLeft.getChildren().add(addMemeber);
		        hbLeft.getChildren().add(addBook);
		        hbLeft.getChildren().add(print);
		        hbLeft.getChildren().add(overDueList);
	        }

	        //Scene scene = new Scene(grid, 300, 200);
	        Scene scene = new Scene(grid);
	        setScene(scene);

	        Button logout = new Button("logout");
	        logout.setMinSize(150, 20);
	        logout.setAlignment(Pos.CENTER_LEFT);
	        logout.setOnAction((e) -> {
        		Start.hideAllWindows();
        		Start.primStage().show();
	        });
	        hbLeft.getChildren().add(logout);


		} catch(Exception e) {
			e.printStackTrace();
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
            String authorsName = authors.stream().map(OperationWindow::getAuthorName ).collect(Collectors.joining(","));
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
