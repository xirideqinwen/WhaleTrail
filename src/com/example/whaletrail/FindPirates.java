package com.example.whaletrail;

import java.util.Random;

public class FindPirates extends AbstractFind {

	Random rand = new Random();
	private int which = rand.nextInt(2) + 1;
	private int amt1 = rand.nextInt(10) + 2;
	private int amt2 = rand.nextInt(100) + 1;

	public FindPirates(Family family) {
		super(family);
	}

	@Override
	public void update() {
		family.incItem(Item.CLOTHING, -amt1);
		family.incItem(Item.MONEY, -amt2);
	}

	@Override
	public String message() {
		switch (which) {
		case 1:
			return "A bunch of slimy sea robbers stole " + amt1 + " clothes and $" + amt2;
		default:
			return "Pirates! They stole " + amt1 + " clothes and $" + amt2;
		}
	}

}
