package com.eams.exception;

//Custom exception to indicate invalid sensor data input.
public class InvalidSensorDataException extends RuntimeException {
	public InvalidSensorDataException(String message) {
		super(message);
	}
}
