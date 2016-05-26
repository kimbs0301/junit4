package com.example.model;

/**
 * @author gimbyeongsu
 * 
 */
public class Person {

	private String name;

	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private void changeName(String name) {
		this.name = name;
	}
}
