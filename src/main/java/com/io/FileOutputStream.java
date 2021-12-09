package com.io;

import java.io.File;
import java.io.FileWriter;

/**
 * @author Jay Patel
 */
public class FileOutputStream implements IOutputStream {

	private FileWriter writer;

	public FileOutputStream(String directory, String fileName) {
		try {
			File file = new File(directory);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(directory + "/" + fileName);
			file.createNewFile();
			writer = new FileWriter(file.getAbsoluteFile());
			writer.write("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(Object o) {
		try {
			writer.append(o.toString());
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void println(Object o) {
		try {
			writer.append(o.toString() + "\n");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		try {
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
