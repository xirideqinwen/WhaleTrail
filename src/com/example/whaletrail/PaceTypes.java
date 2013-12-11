package com.example.whaletrail;

public enum PaceTypes {

	GRUELING("Grueling", 5, -2), STRENUOUS("Strenuous", .5 * 5, -1), STEADY(
			"Steady", .25 * 5, 0);

	private final String name;
	private final double speed;
	private final int healthChange;

	private PaceTypes(String name, double speed, int healthChange) {
		this.name = name;
		this.speed = speed;
		this.healthChange = healthChange;
	}

	public double getSpeed() {
		return this.speed;
	}

	public String toString() {
		return this.name;
	}
	
	public int getHealthChange() {
		return this.healthChange;
	}

}