package ui;

import business.CheckOutRecordEntry;
import business.ControllerFactory;
import business.LibraryMember;
import business.ValidationException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.utils.WindowUtils;

public class CheckOutWindow extends Stage implements LibWindow{

	public static final CheckOutWindow INSTANCE = new CheckOutWindow();

	private boolean isInitialized = false;
	private TableView<CheckOutRecordEntry> tbv;
	private TextField txtIsbn;
	private TextField txtMemberId;
	private Label errorMessage;


	private CheckOutWindow() {
	}

	@Override
	public void init() {
		try {

	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER_RIGHT);
	        grid.setHgap(20);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
			ColumnConstraints col1 = new ColumnConstraints();
			col1.setPercentWidth(15);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(35);
			ColumnConstraints col3 = new ColumnConstraints();
			col3.setPercentWidth(15);
			ColumnConstraints col4 = new ColumnConstraints();
			col4.setPercentWidth(35);
			grid.getColumnConstraints().addAll(col1,col2,col3,col4);
			grid.setPrefSize(700,500);

	        this.tbv = WindowUtils.createCheckOutRecordEntryListTableView();
			Label lbMemberId = new Label("Member ID:");
			Label lblIsbn = new Label("ISBN: ");
			txtIsbn = new TextField();
			txtMemberId = new TextField();

			grid.add(WindowUtils.createSceneText("Check out"), 0, 0, 4, 1);
			grid.add(lbMemberId, 0, 1);
			grid.add(txtMemberId, 1, 1);
			grid.add(lblIsbn, 2, 1);
			grid.add(txtIsbn, 3, 1);
			grid.add(this.tbv, 0, 3, 4, 4);

			HBox bBox = new HBox();
			grid.add(bBox, 2, 2, 2, 1);

			Button checkBtn = new Button("Search Checkout Record");
			checkBtn.setOnAction(e -> {
				this.errorMessage.setText("");

				LibraryMember member = ControllerFactory.of().getLibraryMember(this.txtMemberId.getText());
				if (member != null) {
					loadCheckOutRecordTableView(member);
					outputSuccessMessage("Successed");
				} else {
					reset();
					outputErrorMessage("Member id is not exist.");
				}
			});

			Button checkOutBtn = new Button("Check Out");
			checkOutBtn.setOnAction(e -> {
				try {
					this.errorMessage.setText("");
					LibraryMember member = ControllerFactory.of().getLibraryMember(this.txtMemberId.getText());
					if (member != null) {
						loadCheckOutRecordTableView(member);
					} else {
						reset();
					}
					ControllerFactory.of().checkOut(this.txtMemberId.getText(), this.txtIsbn.getText());
					loadCheckOutRecordTableView(member);
					outputSuccessMessage("Check out success!");
				} catch (ValidationException e1) {
					outputErrorMessage(e1.getMessage());
				}
			});


			bBox.setAlignment(Pos.CENTER_RIGHT);
			bBox.getChildren().add(checkBtn);
			bBox.getChildren().add(checkOutBtn);
			bBox.setSpacing(20);

			Button backBtn = new Button("< Back");
			backBtn.setMinSize(150, 20);
			backBtn.setOnAction(e -> {
				Start.showOperationWindow();
			});
			grid.add(backBtn, 0, 7,2,1);

			errorMessage = new Label();
			grid.add(errorMessage, 0, 2,2,1);

	        //Scene scene = new Scene(grid, 300, 200);
	        Scene scene = new Scene(grid);
	        setScene(scene);

	        this.isInitialized = true;

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		this.errorMessage.setText("");
		this.tbv.getItems().clear();
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void setInitialized(boolean val) {
		isInitialized = val;
	}

	private void loadCheckOutRecordTableView(LibraryMember member) {
		this.tbv.getItems().clear();
		member.getCheckOutRecord().getEntryList().forEach( r -> this.tbv.getItems().add(r));
	}

	private void outputErrorMessage(String text) {
		errorMessage.setTextFill(Color.web("#FF0000"));
		errorMessage.setText(text);
	}

	private void outputSuccessMessage(String text) {
		errorMessage.setTextFill(Color.web("#18A851"));
		errorMessage.setText(text);
	}
}
