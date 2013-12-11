package com.example.whaletrail;

public enum RationTypes {
	// can be set like this: Ration myRation = Ration.MEAGER

	FILLING("Filling", 3, 1), MEAGER("Meager", 2, 0), BARE("Bare Bones", 1, -1);

	private final String name;
	private final int amount;
	private final int healthChange;

	private RationTypes(String name, int amount, int healthChange) {
		this.name = name;
		this.amount = amount;
		this.healthChange = healthChange;
	}

	public int getAmount() {
		return amount;
	}

	public String toString() {
		return name;
	}
	
	public int getHealthChange() {
		return healthChange;
	}

}