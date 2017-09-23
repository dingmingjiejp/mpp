package lesson10.labs.prob2.partB;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;



public class NewFileIO {
	private static final Logger LOG = Logger.getLogger(NewFileIO.class.getName());
	public final static String FILE_LOCATION = "src/lesson10/labs/prob2/partA/output.txt";
	private static final String FILENAME ="output.txt";
	public final static String TO_PRINT = "This is the string to print to file.";

	private URL getFileAsInputStream(String filename) {
		URL fileAsUrl = null;
		String pkge = asPath(getClass().getPackage().getName());
		try {
			fileAsUrl = this.getClass().getClassLoader().getResource(pkge + "/" + filename);
			if(fileAsUrl == null) throw new IOException();
			InputStream is = fileAsUrl.openStream();
		} catch(IOException e) {
			String msg = "Unable to open file as an InputStream";
			LOG.warning(msg);
			throw new RuntimeException(msg);
		}
		return fileAsUrl;
	}
	private String asPath(String packageName) {
		//replace dots with '/'
		return packageName.replace('.','/');
	}

	void writeText(String filename, String text) {
		URL fileAsUrl = getFileAsInputStream(filename);
		System.out.println(fileAsUrl.getFile());
		try(PrintWriter writer = new PrintWriter(fileAsUrl.getFile())) {
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
		newFileIO.writeText(FILENAME, TO_PRINT);

	}

}
