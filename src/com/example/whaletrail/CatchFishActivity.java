package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class CatchFishActivity extends Activity implements OnClickListener {
	private ImageButton continueButton;
	private ImageButton fishButton;
	int numCaught;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		numCaught = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catch_fish_layout);

		// button handlers
		continueButton = (ImageButton) findViewById(R.id.continueButton);
		// make sure we can use it in the onClick method
		continueButton.setOnClickListener(this);

		fishButton = (ImageButton) findViewById(R.id.fishButton);
		fishButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.continueButton) {
			Intent continueIntent = new Intent(this, MainActivity.class);
			continueIntent.putExtra("newGame", false);
			startActivity(continueIntent);
		} else if (v.getId() == R.id.fishButton) {
			showToast("+1 food!");
			MainActivity.getFamily().incItem(Item.FOOD, 1);
			if (numCaught >= 20) {
				showToast("you can't carry any more fish");
				Intent continueIntent = new Intent(this, MainActivity.class);
				continueIntent.putExtra("newGame", false);
				startActivity(continueIntent);
			}
		}
	}

	public void showToast(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_SHORT);
		toast.show();
	}
}
