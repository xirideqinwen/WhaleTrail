package com.example.whaletrail;


public class Family {

	private int position;
	private RationTypes myRation;
	private PaceTypes myPace;
	private String name;
	private int foodAmt, whaleAmt, clothingAmt, money, health;

	// constructor
	// takes a List of members, and a Ration and Pace enum
	public Family(RationTypes myRation, PaceTypes myPace, String name) {
		this.myRation = myRation;
		this.myPace = myPace;
		this.foodAmt = 0;
		this.whaleAmt = 0;
		this.money = 1000;
		this.health = 100;
		this.setName(name);
	}

	// default constructor
	public Family() {
		this.myRation = RationTypes.MEAGER;
		this.myPace = PaceTypes.STRENUOUS;
		this.foodAmt = 50;
		this.whaleAmt = 2;
		this.money = 100;
		this.health = 100;
		this.setName("Johnson");
	}

	// update family, return a description of that day's event
	public String nextDay() {
		eatFood();
		updateHealth();
		incPos();
		// do a random event with this family
		String message = new RandEvent(this).getSummary();
		return message;
	}

	// getters
	public PaceTypes getPace() {
		return this.myPace;
	}

	public RationTypes getRation() {
		return this.myRation;
	}

	public int getPos() {
		return this.position;
	}

	public Boolean atTown() {
		if (this.position % 5 == 0) {
			return true;
		}
		return false;
	}

	// setters
	public void setPosition(int pos) {
		this.position = pos;
	}

	public void incPos() {
		this.position += this.myPace.getSpeed();
	}

	public void incPos(int amt) {
		this.position += amt;
	}

	public void setPace(PaceTypes pace) {
		myPace = pace;
	}

	public void setRation(RationTypes ration) {
		myRation = ration;
	}

	public void eatFood() {
		// this.foodAmt -= (this.myRation.getAmount() * this.people.size());
		incItem(Item.FOOD, -myRation.getAmount());
	}

	public void updateHealth() {
		incItem(Item.HEALTH, myPace.getHealthChange());
	}

	// changes item amount, checks to make sure no negative amounts
	public void incItem(Item item, int amt) {
		switch (item) {
		case FOOD:
			foodAmt = incByAtLeast(foodAmt, amt);
			break;
		case WHALE:
			whaleAmt = incByAtLeast(whaleAmt, amt);
			break;
		case MONEY:
			money = incByAtLeast(money, amt);
			break;
		case CLOTHING:
			clothingAmt = incByAtLeast(clothingAmt, amt);
			break;
		case HEALTH:
			health = incByAtLeast(health, amt);
			if (health > 100)
				health = 100;
			break;
		}
	}

	
	// a function to make sure adding/subtracting an amount will not be <0
	private int incByAtLeast(int initial, int amt) {
		if (initial + amt >= 0) {
			initial += amt;
		} else
			initial = 0;
		return initial;
	}

	public int getAmt(Item item) {
		switch (item) {
		case FOOD:
			return foodAmt;
		case WHALE:
			return whaleAmt;
		case MONEY:
			return money;
		case CLOTHING:
			return clothingAmt;
		case HEALTH:
			return health;
		default:
			return 0;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int isDead() {
		if (foodAmt <= 0) {
			return 1;
		} else if (health <= 0) {
			return 2;
		}
		return 0;
	}

	public String causeOfDeath() {
		if (isDead()==1) {
			return "Ran out of food!";
		} else if (isDead()==2) {
			return "No health left!";
		} else {
			return "Not sure why you died...";
		}
	}

}
