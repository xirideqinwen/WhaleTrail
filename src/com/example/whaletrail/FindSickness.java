package com.example.whaletrail;

import java.util.Random;

public class FindSickness extends AbstractFind {

	public FindSickness(Family family) {
		super(family);
	}

	@Override
	public void update() {
		//subtract more health if there aren't enough clothes
		if (family.getAmt(Item.CLOTHING) < 20) {
			family.incItem(Item.HEALTH, -3);
		} 
		//even fewer clothes
		if (family.getAmt(Item.CLOTHING) < 5)	{
			family.incItem(Item.HEALTH, -7);
		}
		//can lose up to 10 health per person
	}

	@Override
	public String message() {	
		if (family.getAmt(Item.CLOTHING) > 20) {
			return "A cold day at sea...but your clothing keeps you warm!";
		} else if (family.getAmt(Item.CLOTHING) > 5) {
			return "You got pneumonia and lost 3 health!";
		} else {
			return "You got pneumonia and lost 10 health! You could buy more warm clothes...";
		}
		
	}

}