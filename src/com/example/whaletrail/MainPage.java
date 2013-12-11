package com.example.whaletrail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity implements OnClickListener {

	// game members
	private Family family;
	private static int numDays = 1;
	private static String updateMsg;
	private static int moving;

	// layout stuff
	private TextView paceText;
	private TextView rationsText;
	private TextView dayText;
	private TextView messageText;
	private TextView moneyText;
	private TextView foodText;
	private TextView nameText;
	private TextView milesLeftText;

	// Need handler for callback to the UI thread
	final Handler mHandler = new Handler();

	// Create runnable for posting
	final Runnable mUpdateResults = new Runnable() {
		public void run() {
			update();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_layout);
		// get all buttons, text fields
		initLayout();
		// get any sent info
		Bundle b = this.getIntent().getExtras();
		if (b.getBoolean("newGame")) {
			showToast("new game!");
			startNewGame();
			family.setName(b.getString("nameChoice"));
		} else
			family = (Family) b.getSerializable("FamilyObj");

		// create new family if it doesn't exist, update fields
		update();
	}

	public void initLayout() {
		// get text fields from the layout
		paceText = (TextView) findViewById(R.id.paceText);
		rationsText = (TextView) findViewById(R.id.rationsText);
		dayText = (TextView) findViewById(R.id.dayText);
		messageText = (TextView) findViewById(R.id.message_text);
		moneyText = (TextView) findViewById(R.id.moneyText);
		foodText = (TextView) findViewById(R.id.foodText);
		nameText = (TextView) findViewById(R.id.nameText);
		milesLeftText = (TextView) findViewById(R.id.locationText);
		// button handlers
		View continueButton = findViewById(R.id.continue_button);
		// make sure we can use it in the onClick method
		continueButton.setOnClickListener(this);
		View settingsButton = findViewById(R.id.settings_button);
		// settingsButton.setOnClickListener(this);
		View buyButton = findViewById(R.id.buy_button);
		buyButton.setOnClickListener(this);
		// register buttons for popup events
		registerForContextMenu(settingsButton);
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continue_button: // INFO
			nextDay();
			// Intent travelIntent = new Intent(this, TravellingActivity.class);
			// startActivity(travelIntent);
			break;
		case R.id.settings_button: // SETTINGS

			// Intent settingsIntent = new Intent(this, ChangePace.class);
			// startActivity(settingsIntent);
			break;
		case R.id.buy_button:
			Intent buyIntent = new Intent(MainPage.this, Store.class);
			startActivity(buyIntent);
		}
		update();
	}

	// POPUP MENU STUFF
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// create a new popup menu anchored to the settings button
		super.onCreateContextMenu(menu, v, menuInfo);
		// change pace menu
		if (v.getId() == R.id.settings_button) {
			getMenuInflater().inflate(R.menu.change_settings_popup, menu);
		}
	}

	// controller for the settings menu
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.bare_bones:
			family.setRation(RationTypes.BARE);
			showToast("changed rations!");
			return true;
		case R.id.meager:
			family.setRation(RationTypes.MEAGER);
			showToast("changed rations!");
			return true;
		case R.id.filling:
			family.setRation(RationTypes.FILLING);
			showToast("changed rations!");
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	// display a bubble message when we want to alert the user
	public void showToast(String message) {

		Toast toast = Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_SHORT);
		toast.show();
		update();
	}

	public void nextDay() {
		numDays++;
		updateMsg = family.nextDay();
		travel();
		// do a new event
		// doEvent();
		// update the message in the middle of the screen with a summary of
		// today's event
		// updateMsg = new Event(family).getSummary();
		// updateMsg += " " + family.getAmt(Item.FOOD);
		update();
		if (family.getPos() == 50) {
			Intent winIntent = new Intent(MainPage.this, WinActivity.class);
			startActivity(winIntent);
		}
		if (numDays > 50) {
			family.incItem(Item.HEALTH, -5);
		}

		if (family.getPos() == 20) {
			Intent wreckIntent = new Intent(MainPage.this, FindWreck.class);
			// wreckIntent.putExtra("FamilyObj", family);
			startActivity(wreckIntent);
		}

		if (family.getPos() == 30) {
			Intent sharkIntent = new Intent(MainPage.this, FindShark.class);
			// sharkIntent.putExtra("FamilyObj", family);
			startActivity(sharkIntent);
		}

		if (family.getPos() == 40) {
			// Actually turtles
			Intent mermaidIntent = new Intent(MainPage.this, FindMermaid.class);
			// mermaidIntent.putExtra("FamilyObj", family);
			startActivity(mermaidIntent);
		}

		// if the family died, go to the next activity
		if (family.isDead() != 0) {
			Intent deadIntent = new Intent(this, DeadActivity.class);
			// send the cause of death as a string
			deadIntent.putExtra("causeOfDeath", family.causeOfDeath());
			deadIntent.putExtra("causeOfDeath", true);
			startActivity(deadIntent);
		}
	}

	// update the text fields in the layout
	public void updateFields() {
		paceText.setText("Pace: " + family.getPace().toString());
		rationsText.setText("Rations: " + family.getRation().toString());
		dayText.setText("Day: " + numDays);
		moneyText.setText("Money: $" + family.getAmt(Item.MONEY));
		foodText.setText("Food: " + family.getAmt(Item.FOOD));
		nameText.setText(family.getName());
		messageText.setText(updateMsg);
		milesLeftText.setText("Miles until destination: "
				+ (50 - family.getPos()));
	}

	public void startNewGame() {
		family = new Family();
		numDays = 0;
		updateMsg = "Get ready for your first day at sea!";
	}

	public void update() {
		if (family == null) {
			startNewGame();
		}
		updateFields();
	}

	public void travel() {
		family.incPos(); // travel according to family's pace
	}

	public Family getFamily() {
		return family;
	}

}
