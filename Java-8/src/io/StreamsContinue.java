package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 13, 2017 11:03:58 PM
 *
 */
public class StreamsContinue {
	// FileInputStream
	// FileOutputStream
	public void fileInputOutputStream() throws IOException {
		File source = new File("Zoo.class");
		File destination = new File("ZooCopy.class");
		try (InputStream in = new FileInputStream(source); 
				OutputStream out = new FileOutputStream(destination)) {
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		}
	}

	/**
	 * Instead of reading the data one byte at a time, we use the underlying
	 * read(byte[]) method of BufferedInputStream , which returns the number of
	 * bytes read into the provided byte array.
	 * 
	 * Why use the buffered classes?
	 * 
	 * The BufferedInputStream class is capable of retrieving and storing in
	 * memory more data than you might request with a single read() call. For
	 * successive calls to the read() method with small byte arrays, this would
	 * be faster in a wide variety of situations, since the data can be returned
	 * directly from memory without going to the file system.
	 */
	public void bufferedInputOutputStream() throws IOException {
		File source = new File("Zoo.class");
		File destination = new File("ZooCopy.class");
		try (InputStream in = new BufferedInputStream(new FileInputStream(source));
				OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, lengthRead);
				// Ensure that the written data actually makes it to disk before
				// the next buffer of data is read.
				out.flush();
			}
		}
	}

	public void fileReaderWiter() throws IOException {

	}

	// BufferedReader
	public static List<String> readFile(File source) throws IOException {
		List<String> data = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			String s;
			while ((s = reader.readLine()) != null) {
				data.add(s);
			}
		}
		return data;
	}

	// BufferedWriter
	public static void writeFile(List<String> data, File destination) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
			for (String s : data) {
				writer.write(s);
			}
		}
	}

	public static void main(String[] args) throws IOException {

	}
}
