package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DeadActivity extends Activity implements OnClickListener {

	private View wholeScreen;
	private TextView messageText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dead_layout);
		
		messageText = (TextView) findViewById(R.id.causeOfDeath);

		// test for clicks on the view
		wholeScreen = (View) findViewById(R.id.RelativeLayout1);
		wholeScreen.setOnClickListener(this);
		
		Bundle b = this.getIntent().getExtras();
		messageText.setText(b.getString("causeOfDeath"));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RelativeLayout1:
			//go back to the start menu on click
			Intent startMenuIntent = new Intent(this, StartMenuActivity.class);
			startActivity(startMenuIntent);
		}

	}
	
}
