package business;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Overdue {
	private CheckOutRecordEntry entry;
	private LibraryMember member;
	private String entryKey;

	public Overdue(BookCopy bookCopy) {
		this.entryKey = generateKey(bookCopy);
		this.entry = new CheckOutRecordEntry(bookCopy);
	}

	public Overdue(CheckOutRecordEntry entry, LibraryMember member) {
		this.entryKey = generateKey(entry.getBookCopy());
		this.entry = entry;
		this.member = member;
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

	public String displayDueDays() {
		if(entry != null && entry.getDueDate() != null) {
			if(LocalDate.now().isAfter(entry.getDueDate())) {
				long days = ChronoUnit.DAYS.between(entry.getDueDate(), LocalDate.now());
				return "" + days + " day" + (days > 1 ? "s" : "");
			}
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
