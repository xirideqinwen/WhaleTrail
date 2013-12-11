package com.example.whaletrail;

public class Person {
	private String name;
	private int health;
	
	//constructor
	public Person(String name) {
		this.name = name;
		this.health = 100;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void changeHealth(int amount) {
		health += amount;
	}
	
	//for printing player summary
	public String toString() {
		String out = name + ": " + health + " health";
		return out;
	}
	
}
