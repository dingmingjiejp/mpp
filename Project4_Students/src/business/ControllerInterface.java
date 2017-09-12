package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.User;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();

	public HashMap<String, Book> allBookList();

	public User getCurrentUser();
	public HashMap<String, Book> getBooksMap();

}
