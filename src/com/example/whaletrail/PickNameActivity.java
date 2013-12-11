package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class PickNameActivity extends Activity implements OnClickListener {

	private static EditText nameChoice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pick_name_layout);
		nameChoice = (EditText) findViewById(R.id.nameChoice);
		// test for clicks on the view
		View submitButton = (View) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submitButton:
			if (goodName(nameChoice.getText().toString())) {
				Intent gameIntent = new Intent(this, MainActivity.class);
				// send what the user entered to the main game
				gameIntent.putExtra("nameChoice", nameChoice.getText().toString());
				gameIntent.putExtra("newGame", true);
				startActivity(gameIntent);
			} else {
				// show an error message if it was all whitespace
				showToast("enter a name!");
			}
		}
	}
	
	//test if the name is whitespace
	private boolean goodName(String name) {
		if (name.matches("^ *$") || name.matches("")) {
			return false;
		}
		return true;
	}

	// display a bubble message when we want to alert the user
	public void showToast(String message) {

		Toast toast = Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_SHORT);
		toast.show();
	}

}
