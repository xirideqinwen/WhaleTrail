package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class StartMenuActivity extends Activity implements OnClickListener {
	
	private View infoButton;
	private View newgameButton;
	private View quitButton;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_menu_layout);

		// button handlers
		infoButton = findViewById(R.id.info_button);
		// make sure we can use it in the onClick method
		infoButton.setOnClickListener(this);

		newgameButton = findViewById(R.id.newgame_button);
		newgameButton.setOnClickListener(this);
		
		quitButton = findViewById(R.id.quit_button);
		quitButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.info_button) {
			Intent infoIntent = new Intent(this, InfoActivity.class);
			startActivity(infoIntent);
		} else if (v.getId() == R.id.newgame_button) {
			Intent pickNameIntent = new Intent(this, PickNameActivity.class);
			startActivity(pickNameIntent);
		} else if (v.getId() == R.id.quit_button) {
			finish();
			System.exit(0);
		}
	}
}