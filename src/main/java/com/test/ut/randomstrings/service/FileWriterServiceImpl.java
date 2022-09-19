package com.test.ut.randomstrings.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Log4j2
@Service
public class FileWriterServiceImpl implements FileWriterService{

	@Override
	public FileWriter getFileWriter(File file){
		try {
			log.debug("File Writer Created.");
			return new FileWriter(file);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	@Override
	public void write(FileWriter fileWriter, String content){
		try {
			fileWriter.write(content);
			log.debug("File Writer wries [" +content+"]." );
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void flush(FileWriter fileWriter){
		try {
			fileWriter.flush();
			log.debug("File Writer Flushed.");
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
