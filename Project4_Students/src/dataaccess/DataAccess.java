package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import business.Overdue;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess {
    HashMap<String,Book> readBooksMap();
    HashMap<String,User> readUserMap();
    HashMap<String, LibraryMember> readMemberMap();
    void saveNewMember(LibraryMember member);
    HashMap<String,Book> findBooksMap(String isbn);
    HashMap<String,Overdue> findOverduesMap(Book book);
    void saveBook(Book book);
    void saveNewCopy(Book book);
}
