package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.User;

public interface ControllerInterface {
	void login(String id, String password) throws LoginException;

	List<String> allMemberIds();

	List<String> allBookIds();

	User getCurrentUser();

	HashMap<String, Book> getBooksMap();

	HashMap<String, Book> searchBooks(String isbn);

	void validateCheckOutForm(String memberId, String isbn) throws ValidationException;

	LibraryMember getLibraryMember(String memberId);

	void checkOut(String memberId, String isbn) throws ValidationException;

	void addACopy(Book book);

	void updateBooksMap();

	void addBook(String isbn, String title, String maxCheckoutLength, List<Author> authors, String copyNum) throws ValidationException;

	Author createAuthor(String f, String l, String t, String bio, String street, String city, String state, String zip) throws ValidationException;

	void printCheckOutRecord(LibraryMember member, CheckOutRecordEntry entry);
}
