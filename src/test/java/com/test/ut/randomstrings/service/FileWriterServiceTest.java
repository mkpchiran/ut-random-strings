package com.test.ut.randomstrings.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {FileWriterService.class, FileWriterServiceImpl.class})
class FileWriterServiceTest {

	@Autowired
	private FileWriterService service;

	@Test
	void getFileWriter() throws IOException {
		File file = File.createTempFile("test", "tst");
		FileWriter fileWriter = service.getFileWriter(file);

		assertNotNull(fileWriter);
	}

	@Test
	void getFileWriterThrowsRunTimeExceptionOnFileNotFound() {
		File file = new File("-/\\\"");
		Exception e = assertThrows(RuntimeException.class, () -> service.getFileWriter(file));
		assertNotNull(e);
		assertEquals("java.io.FileNotFoundException: -\\\" (The system cannot find the path specified)", e.getMessage());
	}

	@Test
	void write() throws IOException {

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String content = "content";
		service.write(fileWriter, content);
		doNothing().when(fileWriter).write(content);

		verify(fileWriter, times(1)).write(content);
	}

	@Test
	void writeThrowsRunTimeExceptionOnIOExceptions() throws IOException {

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String content = "content";
		doThrow(new IOException("Test Exception")).when(fileWriter).write(content);
		Exception e = assertThrows(RuntimeException.class, () -> service.write(fileWriter, content));
		assertNotNull(e);
		assertEquals("java.io.IOException: Test Exception", e.getMessage());

		verify(fileWriter, times(1)).write(content);
	}

	@Test
	void flush() throws IOException {
		FileWriter fileWriter = Mockito.mock(FileWriter.class);

		doNothing().when(fileWriter).flush();
		service.flush(fileWriter);

		verify(fileWriter, times(1)).flush();
	}

	@Test
	void flushThrowsRunTimeExceptionOnIOExceptions() throws IOException {
		FileWriter fileWriter = Mockito.mock(FileWriter.class);

		doThrow(new IOException("Test Exception")).when(fileWriter).flush();
		Exception e = assertThrows(RuntimeException.class, () -> service.flush(fileWriter));
		assertNotNull(e);
		assertEquals("java.io.IOException: Test Exception", e.getMessage());

		verify(fileWriter, times(1)).flush();
	}
}