package com.example.whaletrail;

import java.util.ArrayList;


public class MakeTestFamily {
	public Family make() {
		// set up test family
		PaceTypes pace = PaceTypes.STEADY;
		RationTypes ration = RationTypes.FILLING;
		return new Family(ration, pace, "LastName");
	}
}
