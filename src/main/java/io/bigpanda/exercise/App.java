package io.bigpanda.exercise;

import io.bigpanda.exercise.processing.EventConsumer;
import io.bigpanda.exercise.processing.EventPublisher;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gal.a
 *
 */
@SpringBootApplication
@ComponentScan
public class App {
	Logger logger = LoggerFactory.getLogger(App.class);

	private ProcessBuilder processBuilder;
	@Autowired
	private Vertx vertx;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void initVerticals() throws IOException {
		DeploymentOptions options = new DeploymentOptions().setWorker(true);
		String uri = copyExecToTempLocation();
		processBuilder = new ProcessBuilder(uri);
		logger.debug("Deploying Verticales...");
		vertx.deployVerticle(new EventPublisher(processBuilder.start()), options);
		vertx.deployVerticle(new EventConsumer(), options);
	}

	//Ugly hack to make it work when running the jar file
	//since maven copy resource file is not working well when running the jar via CLI
	private static String copyExecToTempLocation() throws IOException {
		InputStream inputStream = App.class.getResourceAsStream("/generators/generator-windows-amd64.exe");
		Path filePath = Files.createTempFile("generator", "tmp");
		FileUtils.copyInputStreamToFile(inputStream, filePath.toFile());
		return filePath.toString();
	}

}
