package com.example.whaletrail;

import java.util.Random;

public class FindNothing extends AbstractFind {

	Random rand = new Random();
	private int which = rand.nextInt(6) + 1;
	
	public FindNothing(Family family) {
		super(family);
	}

	@Override
	public void update() {

	}

	@Override
	public String message() {
		switch (which) {
		case 1: 
			return "Is that land ahead?...no, nevermind...";
		case 2:
			return "Nothing but calm, blue ocean ahead!";
		case 3:
			return "There's a storm a brewin'";
		case 4:
			return "Whale, whale, whale...another day at sea";
		case 5:
			return "Nothing but ocean for as far as the eye can SEA";
		case 6:
			return "You spend all day fishing";
		default:
			return "Another exciting day at sea!";
		}
	}

}