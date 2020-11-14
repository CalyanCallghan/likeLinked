package com.onpassive.onet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.entity.BatchProcessEntity;
import com.onpassive.onet.repository.BatchProcessRepository;
import com.onpassive.onet.service.PostStorageService;
import com.onpassive.onet.util.BatchData;
import com.onpassive.onet.util.BatchResponse;

@RestController
@CrossOrigin
@RequestMapping("/doBatch")
public class BatchProcessController {

	@Autowired
	private PostStorageService PostStorageService;
	@Autowired
	private BatchProcessRepository batchRepository;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@PostMapping("/storeAndProcessFile")
	public BatchResponse uploadFile(@RequestParam("file") MultipartFile file) {
		BatchResponse batchResponse = new BatchResponse();
		BatchData data = new BatchData();
		try {
			String fileName = PostStorageService.storeFileCvs(file);
			System.out.println("==============" + fileName);
			if (fileName.contains(fileName)) {
				load();
				List<BatchProcessEntity> findByJobExecutionId = batchRepository.findByStepName("ATTENDANCE-file-load");
				for (BatchProcessEntity batchProcessEntity : findByJobExecutionId) {
					data.setSuccessCount(batchProcessEntity.getReadCount());
					data.setFailureCount(batchProcessEntity.getReadSkipCount());
					batchResponse.setData(data);
					batchResponse.setMessage(" Upload File successfully...");
					batchResponse.setStatus(true);
				}
			}

		} catch (Exception e) {
			batchResponse.setMessage(" Upload File fail...");
			batchResponse.setData(data);
			batchResponse.setStatus(false);

			return batchResponse;
		}
		return batchResponse;
	}

	public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {

		Map<String, JobParameter> maps = new HashMap<>();

		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);

		System.out.println("JobExecution: " + jobExecution.getStatus());

		while (jobExecution.isRunning()) {
			System.out.println("...");
		}

		return jobExecution.getStatus();
	}
}
