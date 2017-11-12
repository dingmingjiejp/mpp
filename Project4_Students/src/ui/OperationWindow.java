package ui;

import java.util.HashMap;

import business.Book;
import business.ControllerFactory;
import dataaccess.Auth;
import dataaccess.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.utils.WindowUtils;

public class OperationWindow extends  Stage implements LibWindow{

	public static final OperationWindow INSTANCE = new OperationWindow();

	private boolean isInitialized = false;
	private HashMap<String,Book> booksMap = new HashMap<>();
	private TableView<Book> tbv;

	private OperationWindow() {
	}

	@Override
	public void init() {
		try {
			User user = ControllerFactory.of().getCurrentUser();
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER_LEFT);
	        grid.setHgap(20);
	        grid.setVgap(20);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        VBox hbLeft = new VBox(10);
	        VBox hbRight = new VBox();
	        hbLeft.setAlignment(Pos.TOP_LEFT);

	        this.tbv = WindowUtils.createBookListTableView();
	        hbRight.getChildren().add(this.tbv);


	        Text sceneTitle = new Text("Dear " + ControllerFactory.of().getCurrentUser().getId() +  ", Welcome to library system.");
			sceneTitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));

	        grid.add(sceneTitle, 0, 0, 2, 1);
	        grid.add(hbLeft, 0, 1);
	        grid.add(hbRight, 1, 1);

	        if (Auth.BOTH.equals(user.getAuthorization()) ||
	        		Auth.LIBRARIAN.equals(user.getAuthorization()) ){
		        Button checkOutIn = new Button("Check out");
		        checkOutIn.setMinSize(150, 20);
		        checkOutIn.setAlignment(Pos.CENTER_LEFT);
		        checkOutIn.setOnAction((e) -> {
					Start.showCheckInOutWindow();
		        });
		        hbLeft.getChildren().add(checkOutIn);

		        Button print = new Button("Print");
		        print.setMinSize(150, 20);
		        print.setAlignment(Pos.CENTER_LEFT);
		        print.setOnAction((e) -> {
		        	Start.showPrintWindow();
		        });
		        hbLeft.getChildren().add(print);

		        Button overDueList = new Button("Overdue Book Copies");
		        overDueList.setMinSize(150, 20);
		        overDueList.setAlignment(Pos.CENTER_LEFT);
		        overDueList.setOnAction((e) -> {
		        	Start.showOverdueWindow();
		        });
		        hbLeft.getChildren().add(overDueList);
	        }


	        if (Auth.BOTH.equals(user.getAuthorization()) ||
	        		Auth.ADMIN.equals(user.getAuthorization()) ){
		        Button addBookCopy = new Button("Add book copy");
		        addBookCopy.setMinSize(150, 20);
		        addBookCopy.setAlignment(Pos.CENTER_LEFT);
		        addBookCopy.setOnAction((e) -> {
		        	Start.showAddACopyWindow();
		        });

		        Button addMemeber = new Button("Add a Member");
		        addMemeber.setMinSize(150, 20);
		        addMemeber.setAlignment(Pos.CENTER_LEFT);
		        addMemeber.setOnAction((e) -> {
		        	Start.showAddAMemberWindow();
		        });

		        Button addBook = new Button("Add a Book");
		        addBook.setMinSize(150, 20);
		        addBook.setAlignment(Pos.CENTER_LEFT);
		        addBook.setOnAction((e) -> {
		        	Start.showAddBookWindow(true);
		        });

		        hbLeft.getChildren().add(addBookCopy);
		        hbLeft.getChildren().add(addMemeber);
		        hbLeft.getChildren().add(addBook);
	        }

	        //Scene scene = new Scene(grid, 300, 200);
	        Scene scene = new Scene(grid);
	        setScene(scene);

	        Button logout = new Button("Logout");
	        logout.setMinSize(150, 20);
	        logout.setAlignment(Pos.CENTER_LEFT);
	        logout.setOnAction((e) -> {
        		Start.hideAllWindows();
        		Start.destroyAllWindows();
        		Start.primStage().show();
	        });
	        hbLeft.getChildren().add(logout);

	        this.isInitialized = true;

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void setInitialized(boolean val) {
		isInitialized = val;
	}

    public void refreshBookList(HashMap<String,Book> booksMap) {
		this.booksMap.clear();
		this.booksMap.putAll(booksMap);
    	if (tbv != null) {
    		tbv.getItems().clear();
            this.booksMap.forEach((r,v) -> {
            	tbv.getItems().add(v);
            });
    	}
    }

}
