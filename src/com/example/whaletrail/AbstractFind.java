//ABSTRACT

package com.example.whaletrail;

public abstract class AbstractFind {
	protected Family family;


	public AbstractFind(Family family) {
		this.family = family;
	}
	
	//all child classes will these
	public abstract void update();
	public abstract String message();
	public String getSummary() {
		update();
		return message();
	}

}
