package io;

/**
 * 
 * @author chengfeili 
 * Jun 13, 2017 10:41:57 PM
 * 
 *         The java.io API defines two sets of classes for reading and writing
 *         streams: those with Stream in their name and those with Reader /
 *         Writer in their name.
 * 
 *         Differences between Streams and Readers/Writers 
 *         1. The stream classes are used for inputting and outputting all 
 *         types of binary or byte data. 
 *         2. The reader and writer classes are used for inputting and
 *         outputting only character and String data.
 */
public class Streams {

	/**
	 * 
	 * A low-level stream connects directly with the source of the data, such as
	 * a file, an array, or a String
	 */
	public void lowLevelStreams() {

	}

	/**
	 * A high-level stream is built on top of another stream using wrapping.
	 * Wrapping is the process by which an instance is passed to the constructor
	 * of another class and operations on the resulting instance are filtered
	 * and applied to the original instance.
	 */
	public void highLevelStreasm() {

	}

	/**
	 * The java.io library defines four abstract classes that are the parents of
	 * all stream classes defined within the API: InputStream , OutputStream ,
	 * Reader , and Writer .
	 * 
	 * The constructors of high-level streams often take a reference to the
	 * abstract class. For example, BufferedWriter takes a Writer object as
	 * input, which allows it to take any subclass of Writer .
	 */
	public void streamBaseClasses() {

	}

	public static void main(String[] args) {
		Streams stream = new Streams();

	}
}
