package com.onpassive.onet.util;

public class BatchData {
	private Long successCount;
	private Long failureCount;
	
	
	public BatchData() {
		super();
	}

	public BatchData(Long successCount, Long failureCount, String attachmentLInk) {
		this.successCount = successCount;
		this.failureCount = failureCount;
	}

	public Long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}

	public Long getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(Long failureCount) {
		this.failureCount = failureCount;
	}
}
