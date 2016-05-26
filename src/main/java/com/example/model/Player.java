package com.example.model;

/**
 * @author gimbyeongsu
 * 
 */
public class Player {
	private int playerId;
	private String name;
	private int age;
	
	public Player() {
		
	}
	
	public Player(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
