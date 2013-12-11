//CAUSES THE FAMILY TO FIND SOMETHING

package com.example.whaletrail;

import java.util.Random;

public class RandEvent {
	private Family family;
	private int which = (int) (1 + Math.random() * 5);

	public RandEvent(Family family) {
		this.family = family;
	}

	// return a find
	public String getSummary() {
		switch (which) {
		case 1:
			return new FindRock(family).getSummary();
		case 2:
			return new FindGold(family).getSummary();
		case 3:
			return new FindFood(family).getSummary();
		case 4:
			return new FindSickness(family).getSummary();
		case 5:
			return new FindPirates(family).getSummary();
		default:
			return new FindNothing(family).getSummary();
		}
	}
}
