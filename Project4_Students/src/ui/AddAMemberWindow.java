package ui;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import business.Author;
import business.ControllerFactory;
import business.ControllerInterface;
import business.LibraryMember;
import dataaccess.User;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAMemberWindow extends Stage implements LibWindow{

	public static final AddAMemberWindow INSTANCE = new AddAMemberWindow();

	private boolean isInitialized = false;
	private User user;
	private HashMap<String,LibraryMember> membersMap;
	private TableView<LibraryMember> tbv;
	private TextField txtMemberId;
	private TextField txtFirstname;
	private TextField txtLastname;
	private TextField txtTelephone;
	private TextField txtStreet;
	private TextField txtCity;
	private TextField txtState;
	private TextField txtZip;

	public AddAMemberWindow() {
	}

	public void setData(User user, HashMap<String,LibraryMember> membersMap) {
		this.user = user;
		this.membersMap = membersMap;
	}

	@Override
	public void init() {
		try {
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setHgap(20);
	        grid.setVgap(20);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        VBox hbBottom = new VBox(10);
	        hbBottom.setAlignment(Pos.BOTTOM_CENTER);

	        tbv = new TableView<>();
//	        initMemberListView(tbv);

	        Text scenetitle = new Text("Add a New Library Member");
	        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma

	        GridPane gridMember = new GridPane();
	        gridMember.setHgap(10);
	        gridMember.setVgap(10);

	        grid.add(scenetitle, 0, 0, 2, 1);
	        grid.add(gridMember, 0, 1, 2, 1);
	        grid.add(tbv, 0, 2, 2, 1);
	        grid.add(hbBottom, 0, 3, 2, 1);

	        Scene scene = new Scene(grid, 725, 595);
	        setScene(scene);
	        this.INSTANCE.setResizable(false);
	        this.INSTANCE.sizeToScene();


//	        Label lblMemberId = new Label("Member ID: ");
//	        gridMember.add(lblMemberId, 0, 0);
//	        txtIsbn = new TextField();
//	        txtIsbn.setMaxWidth(100);
//	        gridIsbn.add(txtIsbn, 1, 0);
//	        hbLeft.getChildren().add(gridIsbn);

	        GridPane gridSearchBtn = new GridPane();
	        gridSearchBtn.setHgap(10);
//	        gridSearchBtn.setVgap(20);

			GridPane gridBtn = new GridPane();
			gridBtn.setHgap(20);
			gridBtn.setVgap(20);

	        Button btnBack = new Button("< Back");
	        btnBack.setMinSize(150, 20);
	        btnBack.setAlignment(Pos.BOTTOM_LEFT);
	        btnBack.setOnAction((e) -> {
	        	Start.showOpertionWindow();
	        });
	        gridBtn.add(btnBack, 0, 0);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void initBookListView(TableView<LibraryMember> table) {
        // Add extra columns if necessary:
        TableColumn<LibraryMember, String> colmemberId = new TableColumn<>("MemberID");
        colmemberId.setMinWidth(30);
        colmemberId.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getMemberId();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colmemberId);

        TableColumn<LibraryMember, String> colFirstname = new TableColumn<>("First name");
        colFirstname.setMinWidth(50);
        colFirstname.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getFirstName();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colFirstname);

        TableColumn<LibraryMember, String> colLastname = new TableColumn<>("Last name");
        colLastname.setMinWidth(50);
        colLastname.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getFirstName();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colLastname);

        table.setPrefSize(500, 500);
        table.setMaxHeight(400);
        table.setColumnResizePolicy((param) -> true );
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public void updateBooksMap(HashMap<String,LibraryMember> membersMap) {
		this.membersMap = membersMap;
	}

    public void refreshBookList() {
    	if (tbv != null) {
    		tbv.getItems().clear();
            this.membersMap.forEach((r,v) -> {
            	tbv.getItems().add(v);
            });
    	}
    }

}
