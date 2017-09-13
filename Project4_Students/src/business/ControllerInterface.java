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
    HashMap<String, LibraryMember> getMembersMap();
    HashMap<String, Book> searchBooks(String isbn);
    void validateCheckOutForm(String memberId, String isbn) throws ValidationException;
    void validateAddMemberForm(String memberId, String firstName, String lastName,
    		String telephone, String street, String city, String state, String zip) throws ValidationException;
    LibraryMember getLibraryMember(String memberId);
    void checkOut(String memberId, String isbn) throws ValidationException;
    void addACopy(Book book);
    void addMember(String memberId, String firstName, String lastName,
    		String telephone, String street, String city, String state, String zip);
    void updateBooksMap();
    void updateMembersMap();
}
