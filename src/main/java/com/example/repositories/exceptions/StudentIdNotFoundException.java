package com.example.repositories.exceptions;

public class StudentIdNotFoundException extends RuntimeException{
	public StudentIdNotFoundException() {
		super();
	}
	public StudentIdNotFoundException(String message) {
		super(message);
	}
	public StudentIdNotFoundException(int id) {
		super("Student with id "+id+" not found");
	}
}
