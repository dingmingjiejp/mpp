package lesson10.labs.prob2.partA;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;



public class NewFileIO {
	private static final Logger LOG = Logger.getLogger(NewFileIO.class.getName());
	public final static String FILE_LOCATION = "src/lesson10/labs/prob2/partA/output.txt";
	public final static String TO_PRINT = "This is the string to print to file.";

	void writeText(String filename, String text) {

		try(PrintWriter writer = new PrintWriter(new FileWriter(new File(filename)))) {
			writer.print(text);
		} catch(IOException e) {
			LOG.warning("IOException attempting to write a file: " + e.getMessage());
			List<Throwable> suppressed = Arrays.asList(e.getSuppressed());
			suppressed.forEach(except -> LOG.warning("Suppressed message: " + except.getMessage()));
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		NewFileIO newFileIO = new NewFileIO();
		newFileIO.writeText(FILE_LOCATION, TO_PRINT);

	}

}
