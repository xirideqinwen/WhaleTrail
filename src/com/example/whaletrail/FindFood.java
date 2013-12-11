package com.example.whaletrail;

import java.util.Random;

public class FindFood extends AbstractFind {

	Random rand = new Random();
	private int which = rand.nextInt(8) + 1;
	private int amt = rand.nextInt(20) + 1;

	public FindFood(Family family) {
		super(family);

	}

	@Override
	public void update() {
		// find up to 20 food
		family.incItem(Item.FOOD, amt);
	}

	@Override
	public String message() {
		switch (which) {
		case 1:
			return "Found some delicious seaweed! You gained " + amt + " food";
		case 2:
			return "Mmm, you found " + amt + " barnacles for dinner!";
		case 3:
			return "Found some fish nuggets! You gained " + amt + " food";
		case 4:
			return "After a long day of fishing, you caught " + amt
					+ " tuna fish!";
		case 5:
			return "A wrinkly old man gave you " + amt + " marlins to eat";
		case 6:
			return "Found " + amt + " rolls of sushi";
		case 7:
			return "A fellow traveler gave you " + amt + " fish tacos";
		case 8:
			return "You pulled in your traps and found " + amt + " lobsters";
		default:
			return "Found some unknown fish...looks edible! +" + amt + " food";

		}

	}

}