package business;

import java.time.format.DateTimeFormatter;

public class Overdue {
	private CheckOutRecordEntry entry;
	private LibraryMember member;
	private String entryKey;

	public Overdue(BookCopy bookCopy) {
		this.entryKey = generateKey(bookCopy);
		this.entry = new CheckOutRecordEntry(bookCopy);
	}

	public Overdue(CheckOutRecordEntry entry) {
		this.entryKey = generateKey(entry.getBookCopy());
		this.entry = entry;
	}

	public LibraryMember getMember() {
		return member;
	}

	public String getKey() {
		return entryKey;
	}

	public CheckOutRecordEntry getEntry() {
		return entry;
	}

	public static String generateKey(BookCopy bookCopy) {
		return bookCopy.getBook().getIsbn() + " - " + bookCopy.getCopyNum();
	}

	public String displayDueDate() {
		if(entry != null && entry.getDueDate() != null) {
			return entry.getDueDate().format(DateTimeFormatter.ofPattern("d/MM/uuuu"));
		}
		return "";
	}

	public String displayMemberId() {
		if(member != null) {
			return member.getMemberId();
		}
		return "";
	}
}
