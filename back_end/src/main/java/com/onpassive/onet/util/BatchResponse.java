package com.onpassive.onet.util;


public class BatchResponse {
	private String message;
	private boolean status;
	private BatchData data;

	public BatchResponse() {

	}

	public BatchResponse(String message, boolean status, BatchData data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param batchStatus the message to set
	 */
	public void setMessage(String batchStatus) {
		this.message = batchStatus;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public BatchData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(BatchData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BatchResponse [message=" + message + ", status=" + status + ", data=" + data + "]";
	}

}

