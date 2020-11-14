package com.onpassive.onet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batch_step_execution")
public class BatchProcessEntity {
	
	@Id
	@Column(name = "STEP_EXECUTION_ID")
	private Long stepExecutionId;	
	
	@Column(name = "WRITE_COUNT")
	private Long writeCount;	
	
	@Column(name = "EXIT_MESSAGE")
	private String exitMessage;
	
	@Column(name = "FILTER_COUNT")
	private String filterCount;
	
	@Column(name = "JOB_EXECUTION_ID")
	private Long jobExecutionId;
	
	@Column(name = "READ_COUNT")
	private Long ReadCount;
	
	@Column(name = "STATUS")
	private String Status;
	
	@Column(name = "READ_SKIP_COUNT")
	private Long ReadSkipCount;
	
	@Column(name = "STEP_NAME")
	private String stepName;
	
	public BatchProcessEntity() {

	}

	public Long getStepExecutionId() {
		return stepExecutionId;
	}

	public void setStepExecutionId(Long stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
	}

	public Long getWriteCount() {
		return writeCount;
	}

	public void setWriteCount(Long writeCount) {
		this.writeCount = writeCount;
	}

	public String getExitMessage() {
		return exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	public String getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(String filterCount) {
		this.filterCount = filterCount;
	}

	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public Long getReadCount() {
		return ReadCount;
	}

	public void setReadCount(Long readCount) {
		ReadCount = readCount;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Long getReadSkipCount() {
		return ReadSkipCount;
	}

	public void setReadSkipCount(Long readSkipCount) {
		ReadSkipCount = readSkipCount;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
}
