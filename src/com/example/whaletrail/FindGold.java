package com.example.whaletrail;

import java.util.Random;

public class FindGold extends AbstractFind {

	Random rand = new Random();
	private int which = rand.nextInt(5) + 1;
	private int amt = rand.nextInt(100) + 1;
	
	public FindGold(Family family) {
		super(family);
		
	}

	@Override
	public void update() {
		//find up to 100 gold
		family.incItem(Item.MONEY, amt);
	}

	@Override
	public String message() {
		switch (which) {
		case 1:
			return "Found some sunken booty filled with " + amt + " gold";
		case 2:
			return "A kind fisherman paid you $" + amt + " to help you on your quest";
		case 3:
			return "Found a forgotten $" + amt + " in your pocket!";
		case 4:
			return "Sold a shiny fish for $" + amt;
		case 5:
			return "Found a pearl! It sold for $" + amt;
		default:
			return "Wahoo! Found $" + amt + " on the ground";
			
		}
		
	}


}