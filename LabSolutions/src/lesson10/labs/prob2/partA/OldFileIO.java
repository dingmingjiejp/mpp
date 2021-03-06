package lesson10.labs.prob2.partA;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;



public class OldFileIO {
	private static final Logger LOG = Logger.getLogger(OldFileIO.class.getName());
	public final static String FILE_LOCATION = "src/lesson10/labs/prob3/output.txt";
	public final static String TO_PRINT = "This is the string to print to file.";

	void writeText(String filename, String text) {
		FileWriter fw = null;
		PrintWriter writer = null;
		try {
		File f = new File(filename);
		fw = new FileWriter(f);
		writer = new PrintWriter(fw);
		writer.print(text);
		} catch(IOException e) {
			LOG.warning("IOException attempting to write a file: " + e.getMessage());
			System.out.println(e);
		} finally {
			try {
				if(fw != null) fw.close();
				if(writer != null) writer.close();
			} catch(IOException ex) {
				LOG.warning("IOException attempting to close Writers: " + ex.getMessage());
				System.out.println(ex);
			}
		}
	}

	public static void main(String[] args) {
		OldFileIO old = new OldFileIO();
	    old.writeText(FILE_LOCATION, TO_PRINT);

	}

}
