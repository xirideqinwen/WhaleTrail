package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Around extends Activity implements OnClickListener{
	
	public Family family;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_around);
		
		// test for clicks on the view
		View wholeScreen = (View) findViewById(R.id.RelativeLayout3);
		wholeScreen.setOnClickListener(this);
		
		family = MainActivity.getFamily();
		family.nextDay();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RelativeLayout3:
			//go back to the start menu on click
			Intent mainIntent = new Intent(this, MainActivity.class);
			mainIntent.putExtra("newGame",false);
			startActivity(mainIntent);
		}

	}

}
