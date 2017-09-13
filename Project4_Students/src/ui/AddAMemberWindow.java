package ui;

import java.util.HashMap;

import business.ControllerFactory;
import business.ControllerInterface;
import business.LibraryMember;
import business.ValidationException;
import dataaccess.User;
import javafx.beans.property.ReadOnlyStringWrapper;
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
	        VBox hbLeft = new VBox(10);
	        hbLeft.setAlignment(Pos.TOP_LEFT);
	        hbBottom.setAlignment(Pos.BOTTOM_LEFT);

	        tbv = new TableView<>();
	        initMemberListView(tbv);

	        Text scenetitle = new Text("Add a New Library Member");
	        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma

	        GridPane gridMember = new GridPane();
	        gridMember.setHgap(10);
	        gridMember.setVgap(10);

	        grid.add(scenetitle, 0, 0, 2, 1);
	        grid.add(gridMember, 0, 1);
	        grid.add(tbv, 1, 1);
	        grid.add(hbBottom, 0, 2, 2, 1);

	        Scene scene = new Scene(grid, 725, 595);
	        setScene(scene);
	        this.INSTANCE.setResizable(false);
	        this.INSTANCE.sizeToScene();

	        initMemberGrid(gridMember);

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

	private GridPane initMemberGrid(GridPane gridMember) throws ValidationException {
		Label lblMemberId = new Label("Member ID: ");
        gridMember.add(lblMemberId, 0, 0);
        txtMemberId = new TextField();
        txtMemberId.setMaxWidth(100);
        gridMember.add(txtMemberId, 1, 0);

        Label lblFirstname = new Label("First name: ");
        gridMember.add(lblFirstname, 0, 1);
        txtFirstname = new TextField();
        txtFirstname.setMaxWidth(100);
        gridMember.add(txtFirstname, 1, 1);

        Label lblLastname = new Label("Last name: ");
        gridMember.add(lblLastname, 0, 2);
        txtLastname = new TextField();
        txtLastname.setMaxWidth(100);
        gridMember.add(txtLastname, 1, 2);

        Label lblTelephone = new Label("Telephone: ");
        gridMember.add(lblTelephone, 0, 3);
        txtTelephone = new TextField();
        txtTelephone.setMaxWidth(100);
        gridMember.add(txtTelephone, 1, 3);

        Label lblStreet = new Label("Street: ");
        gridMember.add(lblStreet, 0, 4);
        txtStreet = new TextField();
        txtStreet.setMaxWidth(100);
        gridMember.add(txtStreet, 1, 4);

        Label lblCity = new Label("City: ");
        gridMember.add(lblCity, 0, 5);
        txtCity = new TextField();
        txtCity.setMaxWidth(100);
        gridMember.add(txtCity, 1, 5);

        Label lblState = new Label("State: ");
        gridMember.add(lblState, 0, 6);
        txtState = new TextField();
        txtState.setMaxWidth(100);
        gridMember.add(txtState, 1, 6);

        Label lblZip = new Label("Zip: ");
        gridMember.add(lblZip, 0, 7);
        txtZip = new TextField();
        txtZip.setMaxWidth(100);
        gridMember.add(txtZip, 1, 7);

        gridMember.add(initBtnAdd(), 0, 8, 2, 1);

        return gridMember;
	}

	private Button initBtnAdd() throws ValidationException {
		Button btnAdd = new Button("Add");
		btnAdd.setMinSize(150, 20);
		btnAdd.setAlignment(Pos.BOTTOM_CENTER);
		btnAdd.setOnAction((e) -> {
			ControllerInterface ci = ControllerFactory.of();
			try {
				ci.validateAddMemberForm(txtMemberId.getText().trim(),
						txtFirstname.getText().trim(),
						txtLastname.getText().trim(),
						txtState.getText().trim(),
						txtCity.getText().trim(),
						txtStreet.getText().trim(),
						txtTelephone.getText().trim(),
						txtZip.getText().trim());
			} catch(ValidationException ex) {
				Alert alert = new Alert(AlertType.ERROR, ex.getMessage(),
    					ButtonType.OK);
    			alert.setTitle("Add a new library book");
    			alert.setHeaderText("Validation error");
    			alert.show();
    			return;
			}

			String memberId = txtMemberId.getText().trim();
			ci.addMember(txtMemberId.getText().trim(),
					txtFirstname.getText().trim(),
					txtLastname.getText().trim(),
					txtState.getText().trim(),
					txtCity.getText().trim(),
					txtStreet.getText().trim(),
					txtTelephone.getText().trim(),
					txtZip.getText().trim());
			updateMembersMap(ci.getMembersMap());
			refreshMemberList();

			resetFields();
			Alert alert = new Alert(AlertType.INFORMATION, "The new library member with member ID '" +
					memberId + "' was added successfully.", ButtonType.OK);
			alert.setTitle("Add a new library member");
			alert.setHeaderText("New library member was added");
			alert.show();
        });
        return btnAdd;
	}

	@SuppressWarnings("unchecked")
	private void initMemberListView(TableView<LibraryMember> table) {
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

        TableColumn<LibraryMember, String> colTelephone = new TableColumn<>("Telephone");
        colTelephone.setMinWidth(50);
        colTelephone.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getTelephone();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colTelephone);

        TableColumn<LibraryMember, String> colStreet = new TableColumn<>("Street");
        colStreet.setMinWidth(50);
        colStreet.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getAddress().getStreet();
            return new ReadOnlyStringWrapper(cellValue);
        });

        TableColumn<LibraryMember, String> colCity = new TableColumn<>("City");
        colCity.setMinWidth(50);
        colCity.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getAddress().getCity();
            return new ReadOnlyStringWrapper(cellValue);
        });

        TableColumn<LibraryMember, String> colState = new TableColumn<>("State");
        colState.setMinWidth(50);
        colState.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getAddress().getState();
            return new ReadOnlyStringWrapper(cellValue);
        });

        TableColumn<LibraryMember, String> colZip = new TableColumn<>("Zip");
        colZip.setMinWidth(50);
        colZip.setCellValueFactory(data -> {
        	LibraryMember rowValue = data.getValue();
            String cellValue = rowValue.getAddress().getZip();
            return new ReadOnlyStringWrapper(cellValue);
        });

        TableColumn<LibraryMember, String> colAddress = new TableColumn<>("Address");
        colAddress.getColumns().addAll(colStreet, colCity, colState, colZip);
        table.getColumns().add(colAddress);

        table.setPrefSize(450, 450);
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

	private void resetFields() {
		txtMemberId.setText("");
		txtFirstname.setText("");
		txtLastname.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtStreet.setText("");
		txtTelephone.setText("");
		txtZip.setText("");
	}

	public void updateMembersMap(HashMap<String,LibraryMember> membersMap) {
		this.membersMap = membersMap;
	}

    public void refreshMemberList() {
    	if (tbv != null) {
    		tbv.getItems().clear();
            this.membersMap.forEach((r,v) -> {
            	tbv.getItems().add(v);
            });
    	}
    }

}
