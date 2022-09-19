package com.test.ut.randomstrings.command;

import com.test.ut.randomstrings.properties.ApplicationProperties;
import com.test.ut.randomstrings.service.FileWriterService;
import com.test.ut.randomstrings.service.RandomStringGeneratorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

@ShellComponent(value = "Application")
public class AppCommand {

	private static final String NEW_LINE = "\n";
	private final RandomStringGeneratorService service;
	private final FileWriterService fileWriterService;
	private final ApplicationProperties applicationProperties;

	public AppCommand(ApplicationProperties applicationProperties,
					  RandomStringGeneratorService service, FileWriterService fileWriterService) {
		this.service = service;
		this.applicationProperties = applicationProperties;
		this.fileWriterService = fileWriterService;
	}

	@ShellMethod(value = "Generate a file with random strings of given number of lines.")
	public void execute(int x) {

		if (x >= applicationProperties.getMinLines() && x <= applicationProperties.getMaxLines()) {
			File outPut = new File(String.valueOf(new Date().getTime()));
			FileWriter fileWriter = fileWriterService.getFileWriter(outPut);
			for (int i = 0; i < x; i++) {
				fileWriterService.write(fileWriter, service.generate(applicationProperties.getLineLength()));
				fileWriterService.write(fileWriter, NEW_LINE);
			}
			fileWriterService.flush(fileWriter);
			System.out.println("Output file " + outPut.getAbsolutePath());
		} else {
			System.out.println("x number of lines should be between " + applicationProperties.getMinLines() + " and " + applicationProperties.getMaxLines() + ".");
		}
	}
}
