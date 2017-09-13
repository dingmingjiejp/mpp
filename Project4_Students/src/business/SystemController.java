package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	private User currentUser = null;
	private DataAccess da = new DataAccessFacade();
	private HashMap<String, User> usersMap;
	private HashMap<String, Book> booksMap;

	SystemController(){
		usersMap = da.readUserMap();
		booksMap = da.readBooksMap();
	};

	@Override
	public void login(String id, String password) throws LoginException {
		if(!usersMap.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}

		String passwordFound = usersMap.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}

		currentUser = usersMap.get(id);
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public User getCurrentUser() {
		return currentUser;
	}

	@Override
	public HashMap<String, Book> getBooksMap() {
		return booksMap;
	}

	@Override
	public void updateBooksMap() {
		booksMap = da.readBooksMap();
	};

	@Override
	public HashMap<String, Book> searchBooks(String isbn) {
		da = new DataAccessFacade();
		HashMap<String, Book> retval = da.findBooksMap(isbn);
		return retval;
	}

	@Override
	public void addACopy(Book book) {
		book.addCopy();
		da = new DataAccessFacade();
		da.saveNewCopy(book);
	}
}