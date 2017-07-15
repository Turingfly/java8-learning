package io;

import java.io.File;

/**
 * 
 * @author chengfeili 
 * Jun 13, 2017 10:33:04 PM
 *
 */
public class FileClass {

	public void creat() {
		System.out.println(java.io.File.separator); // /
		File parent = new File("/home/smith");
		File child = new File(parent, "data/zoo.txt");
	}

	public void fileObject() {
		File file = new File("/usr/local");
		System.out.println("File Exists: " + file.exists());
		if (file.exists()) {
			System.out.println("Absolute Path: " + file.getAbsolutePath());
			System.out.println("Is Directory: " + file.isDirectory());
			System.out.println("Parent Path: " + file.getParent());
			if (file.isFile()) {
				System.out.println("File size: " + file.length());
				System.out.println("File LastModified: " + file.lastModified());
			} else {
				for (File subfile : file.listFiles()) {
					System.out.println("\t" + subfile.getName());
				}
			}
		}
	}

	public static void main(String[] args) {
		FileClass fc = new FileClass();
		fc.creat();
		fc.fileObject();
	}
}
