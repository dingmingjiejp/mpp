package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import business.ControllerFactory;
import business.ControllerInterface;
import business.Overdue;
import business.ValidationException;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OverdueWindow extends Stage implements LibWindow{

	public static final OverdueWindow INSTANCE = new OverdueWindow();

	private boolean isInitialized = false;
	private TableView<Overdue> tbv;
	private TextField txtIsbn;

	public OverdueWindow() {
	}

	@Override
	public void init() {
		try {
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setHgap(20);
	        grid.setVgap(20);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        grid.getStyleClass().add(getClass().getSimpleName());

	        VBox hbBottom = new VBox(10);
	        hbBottom.setAlignment(Pos.BOTTOM_LEFT);

	        GridPane gridSearch = new GridPane();
	        gridSearch.setHgap(10);

	        tbv = new TableView<>();
	        initOverdueListView(tbv);

	        Text scenetitle = new Text("Overdue");
	        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma

	        grid.add(scenetitle, 0, 0, 2, 1);
	        grid.add(gridSearch, 0, 1, 2, 1);
	        grid.add(tbv, 0, 2, 2, 1);
	        grid.add(hbBottom, 0, 3, 2, 1);

	        Scene scene = new Scene(grid, 725, 595);
	        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
	        setScene(scene);
	        setResizable(false);
	        sizeToScene();

	        Label lblIsbn = new Label("ISBN: ");
	        gridSearch.add(lblIsbn, 0, 0);
	        txtIsbn = new TextField();
	        txtIsbn.setMaxWidth(100);
	        gridSearch.add(txtIsbn, 1, 0);

	        Button searchBtn = new Button("Search");
			searchBtn.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
	        		ControllerInterface ci = ControllerFactory.of();
	    			try {
	    				ci.validateOverdueForm(txtIsbn.getText().trim());
	    			} catch(ValidationException ex) {
	    				Alert alert = new Alert(AlertType.ERROR, ex.getMessage(),
	        					ButtonType.OK);
	        			alert.setTitle("Failed to find a book by ISBN");
	        			alert.setHeaderText("Validation error");
	        			alert.show();
	        			return;
	    			}
	        		searchForOverdue(txtIsbn.getText().trim());
	        	}
	        });
			gridSearch.add(searchBtn, 3, 0);

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
		setInitialized(true);
	}

	private void searchForOverdue(String isbn) {
		ControllerInterface controller = ControllerFactory.of();
		HashMap<String, Overdue> overdues = controller.getOverdues(isbn);
		refreshOverdueList();

    	if (tbv != null) {
			tbv.getItems().clear();
	        overdues.forEach((r,v) -> {
	        	tbv.getItems().add(v);
	        });
		}
	}


	private void initOverdueListView(TableView<Overdue> table) {
        // Add extra columns if necessary:
        TableColumn<Overdue, String> colIsbn = new TableColumn<>("ISBN");
        colIsbn.setMinWidth(30);
        colIsbn.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = rowValue.getEntry().getBookCopy().getBook().getIsbn();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colIsbn);

        TableColumn<Overdue, String> colTitle = new TableColumn<>("Title");
        colTitle.setMinWidth(50);
        colTitle.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = rowValue.getEntry().getBookCopy().getBook().getTitle();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colTitle);

        TableColumn<Overdue, String> colCopyNum = new TableColumn<>("Copy Num");
        colCopyNum.setMinWidth(50);
        colCopyNum.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = "" + rowValue.getEntry().getBookCopy().getCopyNum();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colCopyNum);

        TableColumn<Overdue, String> colStatus = new TableColumn<>("Status");
        colStatus.setMinWidth(50);
        colStatus.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = (rowValue.getEntry().getBookCopy().isAvailable() ? "Available" : "Out");
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colStatus);

        TableColumn<Overdue, String> colMemberId = new TableColumn<>("Member ID");
        colMemberId.setMinWidth(50);
        colMemberId.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = rowValue.displayMemberId();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colMemberId);

        TableColumn<Overdue, String> colDueDate = new TableColumn<>("Due date");
        colDueDate.setMinWidth(50);
        colDueDate.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = rowValue.displayDueDate();
            return new ReadOnlyStringWrapper(cellValue);
        });
        colDueDate.setCellFactory(new Callback<TableColumn<Overdue, String>, TableCell<Overdue, String>>() {
            public TableCell<Overdue, String> call(TableColumn<Overdue, String> param) {
                return new TableCell<Overdue, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setTextFill(null);
                            return;
                        } else if (!item.isEmpty()) {
                        	if(LocalDate.now().isAfter(LocalDate.parse(item, DateTimeFormatter.ofPattern("d/MM/uuuu")))) {
                        		this.setTextFill(Color.RED);
                        	} else {
                        		this.setTextFill(Color.GREEN);
                        	}
                        }
                        setText(item);
                    }
                };
            }
        });
        table.getColumns().add(colDueDate);

        TableColumn<Overdue, String> colDueDays = new TableColumn<>("Due days");
        colDueDays.setMinWidth(50);
        colDueDays.setCellValueFactory(data -> {
        	Overdue rowValue = data.getValue();
            String cellValue = rowValue.displayDueDays();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colDueDays);

        table.setPrefSize(600, 400);
        table.setColumnResizePolicy((param) -> true );
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void setInitialized(boolean val) {
		isInitialized = val;
	}

    public void refreshOverdueList() {
    	tbv.getItems().clear();
    }

}
