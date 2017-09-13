package ui;

import java.util.Collections;
import java.util.List;

import business.Author;
import business.ControllerFactory;
import business.ControllerInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	private static Stage primStage = null;
	public static Stage primStage() {
		return primStage;
	}

	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}

	private static Stage[] allWindows = {
		LoginWindow.INSTANCE,
		AllMembersWindow.INSTANCE,
		AllBooksWindow.INSTANCE,
		AddACopyWindow.INSTANCE,
		OperationWindow.INSTANCE,
		CheckOutWindow.INSTANCE,
		AddACopyWindow.INSTANCE,
		AddBookWindow.INSTANCE,
		AddAuthorsWindow.INSTANCE,
		PrintWindow.INSTANCE,
		AddAMemberWindow.INSTANCE,
		OverdueWindow.INSTANCE,
	};

	public static void hideAllWindows() {
		primStage.hide();
		for(Stage st: allWindows) {
			st.hide();
		}
	}

	public static void destroyAllWindows() {
		for(Stage st: allWindows) {
			((LibWindow)st).setInitialized(false);;
		}
	}


	public static void showOperationWindow() {
		hideAllWindows();
		ControllerInterface controller = ControllerFactory.of();
		if(!OperationWindow.INSTANCE.isInitialized()) {
			OperationWindow.INSTANCE.init();
		}
		OperationWindow.INSTANCE.refreshBookList(controller.getBooksMap());;
		OperationWindow.INSTANCE.show();
	}

	public static void showAddACopyWindow() {
		hideAllWindows();
		ControllerInterface controller = ControllerFactory.of();
		if(!AddACopyWindow.INSTANCE.isInitialized()) {
			AddACopyWindow.INSTANCE.setData(controller.getCurrentUser(),
					controller.getBooksMap());
			AddACopyWindow.INSTANCE.init();
		}
		AddACopyWindow.INSTANCE.refreshBookList();;
		AddACopyWindow.INSTANCE.show();
	}

	public static void showCheckInOutWindow() {
		hideAllWindows();
		if(!CheckOutWindow.INSTANCE.isInitialized()) {
			CheckOutWindow.INSTANCE.init();
		}
		CheckOutWindow.INSTANCE.reset();
		CheckOutWindow.INSTANCE.show();
	}

	public static void showPrintWindow() {
		hideAllWindows();
		if(!PrintWindow.INSTANCE.isInitialized()) {
			PrintWindow.INSTANCE.init();
		}
		PrintWindow.INSTANCE.reset();
		PrintWindow.INSTANCE.show();
	}

	public static void showAddBookWindow(Boolean reset) {
		hideAllWindows();
		if(!AddBookWindow.INSTANCE.isInitialized()) {
			AddBookWindow.INSTANCE.init();
		}
		if (reset) {
			AddBookWindow.INSTANCE.reset();
		}
		AddBookWindow.INSTANCE.show();
	}

	public static void showAddBookWindow(List<Author> list) {
		hideAllWindows();
		if(!AddBookWindow.INSTANCE.isInitialized()) {
			AddBookWindow.INSTANCE.init();
		}
		AddBookWindow.INSTANCE.setAuthorList(list);;
		AddBookWindow.INSTANCE.show();
	}

	public static void showAddAuthorWindow(List<Author> list) {
		hideAllWindows();
		if(!AddAuthorsWindow.INSTANCE.isInitialized()) {
			AddAuthorsWindow.INSTANCE.init();
		}
		AddAuthorsWindow.INSTANCE.reset();
		AddAuthorsWindow.INSTANCE.setData(list);
		AddAuthorsWindow.INSTANCE.show();
	}
	public static void showAddAMemberWindow() {
		hideAllWindows();
		ControllerInterface controller = ControllerFactory.of();
		if(!AddAMemberWindow.INSTANCE.isInitialized()) {
			AddAMemberWindow.INSTANCE.setData(controller.getCurrentUser(),
					controller.getMembersMap());
			AddAMemberWindow.INSTANCE.init();
		}
		AddAMemberWindow.INSTANCE.refreshMemberList();;
		AddAMemberWindow.INSTANCE.show();
	}

	public static void showOverdueWindow() {
		hideAllWindows();
		if(!OverdueWindow.INSTANCE.isInitialized()) {
			OverdueWindow.INSTANCE.init();
		}
		OverdueWindow.INSTANCE.refreshOverdueList();;
		OverdueWindow.INSTANCE.show();
	}


	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Main Page");

		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		MenuBar mainMenu = new MenuBar();
		VBox imageHolder = new VBox();
		Image image = new Image("ui/library.jpg", 400, 300, false, false);

        // simply displays in ImageView the image as is
        ImageView iv = new ImageView();
        iv.setImage(image);
        imageHolder.getChildren().add(iv);
        imageHolder.setAlignment(Pos.CENTER);
        HBox splashBox = new HBox();
        Label splashLabel = new Label("The Library System");
        splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
        splashBox.getChildren().add(splashLabel);
        splashBox.setAlignment(Pos.CENTER);

		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);

		Menu optionsMenu = new Menu("Options");
		MenuItem login = new MenuItem("Login");

		login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	hideAllWindows();
    			if(!LoginWindow.INSTANCE.isInitialized()) {
    				LoginWindow.INSTANCE.init();
    			}
    			LoginWindow.INSTANCE.clear();
    			LoginWindow.INSTANCE.show();
            }
        });

		MenuItem bookIds = new MenuItem("All Book Ids");
		bookIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AllBooksWindow.INSTANCE.isInitialized()) {
					AllBooksWindow.INSTANCE.init();
				}
				List<String> ids = ControllerFactory.of().allBookIds();
				Collections.sort(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s + "\n");
				}
				AllBooksWindow.INSTANCE.setData(sb.toString());
				AllBooksWindow.INSTANCE.show();
            }
		});

		MenuItem memberIds = new MenuItem("All Member Ids");
		memberIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				List<String> ids = ControllerFactory.of().allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
				AllMembersWindow.INSTANCE.setData(sb.toString());
				AllMembersWindow.INSTANCE.show();
            }
		});
		optionsMenu.getItems().addAll(login, bookIds, memberIds);

		mainMenu.getMenus().addAll(optionsMenu);
		Scene scene = new Scene(topContainer, 420, 375);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();
	}

}
