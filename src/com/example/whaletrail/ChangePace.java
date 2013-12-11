package com.example.whaletrail;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ChangePace extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_settings_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_settings_popup, menu);
		return true;
	}

}
