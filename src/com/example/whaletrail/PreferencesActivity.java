package com.example.whaletrail;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencesActivity extends PreferenceActivity {
	
	private static final String OPTION_PACE = "PACE";
	private static final String OPTION_PACE_DEFAULT = "1";
	private static final String OPTION_RATION = "RATION";
	private static final String OPTION_RATION_DEFAULT = "1";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pace_rations_settings);
	}

}
