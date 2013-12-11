package com.example.whaletrail;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class WinActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win);
		
		// test for clicks on the view
		View wholeScreen = (View) findViewById(R.id.RelativeLayout2);
		wholeScreen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RelativeLayout2:
			//go back to the start menu on click
			Intent startMenuIntent = new Intent(this, StartMenuActivity.class);
			startActivity(startMenuIntent);
		}

	}
}
