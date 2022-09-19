package com.test.ut.randomstrings.command;

import com.test.ut.randomstrings.properties.ApplicationProperties;
import com.test.ut.randomstrings.service.FileWriterService;
import com.test.ut.randomstrings.service.RandomStringGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppCommandTest {

	@InjectMocks
	private AppCommand command;
	@Mock
	private ApplicationProperties properties;
	@Mock
	private RandomStringGeneratorService service;
	@Mock
	private FileWriterService fileWriterService;

	@BeforeEach
	void setUp() {
		Mockito.clearInvocations();
	}

	@Test
	void execute(){

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String output = "test";

		when(properties.getMinLines()).thenReturn(1);
		when(properties.getMaxLines()).thenReturn(230);
		when(properties.getLineLength()).thenReturn(100);
		when(fileWriterService.getFileWriter(any(File.class))).thenReturn(fileWriter);
		doNothing().when(fileWriterService).write(any(FileWriter.class), anyString());
		doNothing().when(fileWriterService).flush(fileWriter);
		when(service.generate(100)).thenReturn(output);

		command.execute(8);

		verify(properties, times(8)).getLineLength();
		verify(properties, times(1)).getMinLines();
		verify(properties, times(1)).getMaxLines();
		verify(fileWriterService, times(1)).getFileWriter(any(File.class));
		verify(fileWriterService, times(16)).write(any(FileWriter.class), anyString());
		verify(fileWriterService, times(1)).flush(fileWriter);
		verify(service, times(8)).generate(100);

	}

	@Test
	void executeSuccessWithX1(){

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String output = "test";

		when(properties.getMinLines()).thenReturn(1);
		when(properties.getMaxLines()).thenReturn(230);
		when(properties.getLineLength()).thenReturn(100);
		when(fileWriterService.getFileWriter(any(File.class))).thenReturn(fileWriter);
		doNothing().when(fileWriterService).write(any(FileWriter.class), anyString());
		doNothing().when(fileWriterService).flush(fileWriter);
		when(service.generate(100)).thenReturn(output);

		command.execute(1);

		verify(properties, times(1)).getLineLength();
		verify(properties, times(1)).getMinLines();
		verify(properties, times(1)).getMaxLines();
		verify(fileWriterService, times(1)).getFileWriter(any(File.class));
		verify(fileWriterService, times(2)).write(any(FileWriter.class), anyString());
		verify(fileWriterService, times(1)).flush(fileWriter);
		verify(service, times(1)).generate(100);

	}

	@Test
	void executeSuccessWithX230(){

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String output = "test";

		when(properties.getMinLines()).thenReturn(1);
		when(properties.getMaxLines()).thenReturn(230);
		when(properties.getLineLength()).thenReturn(100);
		when(fileWriterService.getFileWriter(any(File.class))).thenReturn(fileWriter);
		doNothing().when(fileWriterService).write(any(FileWriter.class), anyString());
		doNothing().when(fileWriterService).flush(fileWriter);
		when(service.generate(100)).thenReturn(output);

		command.execute(230);

		verify(properties, times(230)).getLineLength();
		verify(properties, times(1)).getMinLines();
		verify(properties, times(1)).getMaxLines();
		verify(fileWriterService, times(1)).getFileWriter(any(File.class));
		verify(fileWriterService, times(460)).write(any(FileWriter.class), anyString());
		verify(fileWriterService, times(1)).flush(fileWriter);
		verify(service, times(230)).generate(100);

	}

	@Test
	void executeUnsuccessfulWithX231(){

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String output = "test";

		when(properties.getMinLines()).thenReturn(1);
		when(properties.getMaxLines()).thenReturn(230);
		when(properties.getLineLength()).thenReturn(100);
		when(fileWriterService.getFileWriter(any(File.class))).thenReturn(fileWriter);
		doNothing().when(fileWriterService).write(any(FileWriter.class), anyString());
		doNothing().when(fileWriterService).flush(fileWriter);
		when(service.generate(100)).thenReturn(output);

		command.execute(231);

		verify(properties, times(0)).getLineLength();
		verify(properties, times(2)).getMinLines();
		verify(properties, times(2)).getMaxLines();
		verify(fileWriterService, times(0)).getFileWriter(any(File.class));
		verify(fileWriterService, times(0)).write(any(FileWriter.class), anyString());
		verify(fileWriterService, times(0)).flush(fileWriter);
		verify(service, times(0)).generate(100);

	}

	@Test
	void executeUnsuccessfulWithX0(){

		FileWriter fileWriter = Mockito.mock(FileWriter.class);
		String output = "test";

		when(properties.getMinLines()).thenReturn(1);
		when(properties.getMaxLines()).thenReturn(230);
		when(properties.getLineLength()).thenReturn(100);
		when(fileWriterService.getFileWriter(any(File.class))).thenReturn(fileWriter);
		doNothing().when(fileWriterService).write(any(FileWriter.class), anyString());
		doNothing().when(fileWriterService).flush(fileWriter);
		when(service.generate(100)).thenReturn(output);

		command.execute(0);

		verify(properties, times(0)).getLineLength();
		verify(properties, times(2)).getMinLines();
		verify(properties, times(1)).getMaxLines();
		verify(fileWriterService, times(0)).getFileWriter(any(File.class));
		verify(fileWriterService, times(0)).write(any(FileWriter.class), anyString());
		verify(fileWriterService, times(0)).flush(fileWriter);
		verify(service, times(0)).generate(100);

	}
}