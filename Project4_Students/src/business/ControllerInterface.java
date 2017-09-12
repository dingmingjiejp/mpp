package business;

import java.util.HashMap;
import java.util.List;

import dataaccess.User;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public User getCurrentUser();
	public HashMap<String, Book> getBooksMap();

}
