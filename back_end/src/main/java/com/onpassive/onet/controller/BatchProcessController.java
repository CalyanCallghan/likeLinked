package com.onpassive.onet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.service.PostStorageService;
import com.onpassive.onet.util.UploadFileResponse;

@RestController
@RequestMapping("/doBatch")
public class BatchProcessController {


	@Autowired
	private JobLauncher launcher;

	@Autowired

	private Job job;

	@Autowired
	PostStorageService postStorageService;

	LocalDateTime dateTime = null;

	@PostMapping("/storeAndProcessFile")
	public ResponseEntity<UploadFileResponse> process(@RequestParam("file") MultipartFile file)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
			JobParametersInvalidException, IOException {

		byte[] bytes = file.getBytes();
		Path path = Paths.get(new File(file.getOriginalFilename()).getAbsolutePath());
		Files.write(path, bytes);

		System.out.println(" fileName :::  " + file.getOriginalFilename());
		String fileName = postStorageService.storeFileCvs(file);

		JobExecution execution = launcher.run(job,
				new JobParametersBuilder().addString("fullPathFileName", file.getOriginalFilename())
						.addDate("date", new Date(), true).toJobParameters());
		System.out.println("Job execution status" + execution.getStatus());
		String msg = "failed to upload";
		if (execution.getStatus().toString().equals("COMPLETED")) {
			msg = "file uploaded successfully";
		}
		return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.OK, msg));

	}
}
