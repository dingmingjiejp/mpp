package ui.utils;

import business.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ui.OperationWindow;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public final class WindowUtils {

    public static TableView createBookListTableView() {
        TableView table = new TableView<>();

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
            String authorsName = rowValue.getAuthorList();
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
        table.setPrefSize(600, 500);
        table.setColumnResizePolicy((param) -> true );

        table.setPlaceholder(new Label(""));
        return table;
    }

    public static TableView createCheckOutRecordEntryListTableView() {
        TableView table = new TableView<>();

        // Add extra columns if necessary:
        TableColumn<CheckOutRecordEntry, String> colIsbn = new TableColumn<>("isbn");
        colIsbn.setMinWidth(80);
        colIsbn.setCellValueFactory(data -> {
            Book rowValue = data.getValue().getBookCopy().getBook();
            String cellValue = rowValue.getIsbn();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colIsbn);

        TableColumn<CheckOutRecordEntry, String> colTitle = new TableColumn<>("title");
        colTitle.setMinWidth(80);
        colTitle.setCellValueFactory(data -> {
            Book rowValue = data.getValue().getBookCopy().getBook();
            String cellValue = rowValue.getTitle();
            return new ReadOnlyStringWrapper(cellValue);
        });
        table.getColumns().add(colTitle);

        TableColumn<CheckOutRecordEntry, String> colAuthors = new TableColumn<>("authors");
        colAuthors.setMinWidth(120);
        colAuthors.setCellValueFactory(data -> {
            Book rowValue = data.getValue().getBookCopy().getBook();
            String authorsName = rowValue.getAuthorList();
            return new ReadOnlyStringWrapper(authorsName);
        });
        table.getColumns().add(colAuthors);

        TableColumn<CheckOutRecordEntry, String> colCopyNumber = new TableColumn<>("copy number");
        colCopyNumber.setMinWidth(100);
        colCopyNumber.setCellValueFactory(data ->
            new ReadOnlyStringWrapper(String.valueOf(data.getValue().getBookCopy().getCopyNum()))
        );
        table.getColumns().add(colCopyNumber);

        TableColumn<CheckOutRecordEntry, String> colCheckOutDate = new TableColumn<>("check out date");
        colCheckOutDate.setMinWidth(120);
        colCheckOutDate.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(String.valueOf(data.getValue().getCheckOutDate().format(DateTimeFormatter.BASIC_ISO_DATE)))
        );

        table.getColumns().add(colCheckOutDate);

        TableColumn<CheckOutRecordEntry, String> colDueDate = new TableColumn<>("check out date");
        colDueDate.setMinWidth(120);
        colDueDate.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(String.valueOf(data.getValue().getDueDate().format(DateTimeFormatter.BASIC_ISO_DATE)))
        );

        table.getColumns().add(colDueDate);

        table.setPrefSize(800, 500);
        table.setColumnResizePolicy((param) -> true );

        table.setPlaceholder(new Label(""));
        return table;
    }

    public static Text createSceneText(String text) {
        Text sceneTitle = new Text(text);
        sceneTitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20));
        return sceneTitle;
    }


}
