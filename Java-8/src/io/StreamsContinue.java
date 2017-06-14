package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(destination)) {
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
	public List<String> readFile(File source) throws IOException {
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
	public void writeFile(List<String> data, File destination) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
			for (String s : data) {
				writer.write(s);
			}
		}
	}

	// Deserialize
	public List<Animal> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
		List<Animal> animals = new ArrayList<Animal>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			while (true) {
				Object object = in.readObject();
				if (object instanceof Animal)
					animals.add((Animal) object);
			}
		} catch (EOFException e) {
			// File end reached
		}
		return animals;
	}

	// Serialize
	public static void createAnimalsFile(List<Animal> animals, File dataFile) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for (Animal animal : animals)
				out.writeObject(animal);
		}
	}

	public static void main(String[] args) throws IOException {

	}
}

/**
 * Any class can implement the Serializable interface since there are no
 * required methods to implement.
 * 
 * Note that the requirement for properly marking an object as Serializable may
 * involve nested objects.
 * 
 * Therefore, any object references contained within the Tail class must belong
 * to classes that are also marked as Serializable , and so on.
 * 
 * Besides transient instance variables, static class members will also be
 * ignored during the serialization and deserialization process.
 * 
 * Why Not Mark Every Class as Serializable ?
 * 
 * The reason that we do not is that there are some classes that we want to
 * instruct the JVM not to serialize. In particular, process-heavy classes such
 * as the Thread class or any of the Stream classes would be diffi cult, often
 * impossible, to save to persistent storage, since much of their work involves
 * managing JVM processes or resources in real time.
 *
 */
class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private char type;

	public Animal(String name, int age, char type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public char getType() {
		return type;
	}

	public String toString() {
		return "Animal [name=" + name + ", age=" + age + ", type=" + type + "]";
	}
}

class Animal1 implements Serializable {
	private static final long serialVersionUID = 2L;
	private transient String name;
	private transient int age = 10;
	private static char type = 'C';
	{
		this.age = 14;
	}

	public Animal1() {
		this.name = "Unknown";
		this.age = 12;
		this.type = 'Q';
	}

	public Animal1(String name, int age, char type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}
	// Same methods as before

	// [Animal [name=null, age=0, type=P], Animal [name=null, age=0, type=P]]
	/*
	 * As expected, you can see that the values for name and age are lost on
	 * serialization and not set again during deserialization.
	 */
}