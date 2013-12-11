package com.example.whaletrail;

public class FindRock extends AbstractFind {
	public FindRock(Family family) {
		super(family);
	}

	@Override
	public void update() {
		family.incItem(Item.FOOD, -10);
	}

	@Override
	public String message() {
		return "You ran into a rock and wasted some food!";
	}

}
