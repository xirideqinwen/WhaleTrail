package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TravellingActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelling_layout);

		// test for clicks on the view
		View travellingView = (View) findViewById(R.id.travelling_view);
		travellingView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.travelling_view:
			Intent gameIntent = new Intent(this, MainActivity.class);
			startActivity(gameIntent);
		}

	}

}
