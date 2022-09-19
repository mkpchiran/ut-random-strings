package com.test.ut.randomstrings.service;

import java.io.File;
import java.io.FileWriter;

public interface FileWriterService {
	FileWriter getFileWriter(File file);

	void write(FileWriter fileWriter, String content);

	void flush(FileWriter fileWriter);
}
