package business;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
    private User currentUser = null;
    private DataAccess da = new DataAccessFacade();
    private HashMap<String, User> usersMap;
    private HashMap<String, Book> booksMap;
    private HashMap<String, LibraryMember> membersMap;


    SystemController(){
        usersMap = da.readUserMap();
        booksMap = da.readBooksMap();
        membersMap = da.readMemberMap();
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
    public HashMap<String, LibraryMember> getMembersMap() {
        return membersMap;
    }

    @Override
    public HashMap<String, Book> searchBooks(String isbn) {
        HashMap<String, Book> retval = da.findBooksMap(isbn);
        return retval;
    }

    @Override
    public void validateCheckOutForm(String memberId, String isbn) throws ValidationException {
        if (!this.membersMap.containsKey(memberId)) {
            throw new ValidationException("Member is not exist!");
        }

        if (!this.booksMap.containsKey(isbn)) {
            throw new ValidationException("ISBN is not exist!");
        }

        if (this.booksMap.get(isbn).getNextAvailableCopy() == null) {
            throw new ValidationException(("No available copy for the book!"));
        }
    }

    @Override
    public LibraryMember getLibraryMember(String memberId) {
        return membersMap.get(memberId);
    }

    @Override
    public void checkOut(String memberId, String isbn)  throws ValidationException{
        validateCheckOutForm(memberId, isbn);

        Book book = this.booksMap.get(isbn);
        BookCopy bookCopy = book.getNextAvailableCopy();
        bookCopy.changeAvailability();

        LibraryMember member = this.membersMap.get(memberId);
        member.getCheckOutRecord().addCheckOutRecord(LocalDate.now(),
                LocalDate.now().plusMonths(1), bookCopy);


        da.saveNewMember(this.membersMap.get(memberId));
        da.saveBook(book);
    }

    @Override
    public void updateBooksMap() {
        booksMap = da.readBooksMap();
    };

    @Override
    public void addACopy(Book book) {
        book.addCopy();
        da.saveNewCopy(book);
        updateBooksMap();
    }

}