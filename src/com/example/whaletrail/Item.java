package com.example.whaletrail;

public enum Item {
	FOOD("Food", 10), WHALE("Whale", 100), MONEY("Money", 1), CLOTHING("Clothing", 10), HEALTH("Health", 100);

	private final String name;
	private final int cost;

	private Item(String name, int cost) {
		this.name = name;
		this.cost = cost;

	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getName() {
		return this.name;
	}

}
