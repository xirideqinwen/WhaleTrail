package com.example.whaletrail;

import java.util.Arrays;
import java.util.Random;

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

public class MainActivity extends Activity implements OnClickListener {

	// game members
	private static Family family;
	private int numMiles;
	private static String updateMsg;
	private boolean fishedYet;
	private int[] milestones = new int[3];

	// layout stuff
	private TextView paceText;
	private TextView rationsText;
	private TextView dayText;
	private TextView messageText;
	private TextView moneyText;
	private TextView foodText;
	private TextView nameText;
	private TextView healthText;
	private TextView milesLeftText;
	private View fishingButton;
	private View buyButton;
	private View settingsButton;
	private View continueButton;

	// Need handler for callbacks to the UI thread
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
		}

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
		healthText = (TextView) findViewById(R.id.healthText);
		milesLeftText = (TextView) findViewById(R.id.locationText);
		// button handlers
		continueButton = findViewById(R.id.continue_button);
		// make sure we can use it in the onClick method
		continueButton.setOnClickListener(this);
		settingsButton = findViewById(R.id.settings_button);
		// settingsButton.setOnClickListener(this);
		buyButton = findViewById(R.id.buy_button);
		buyButton.setOnClickListener(this);
		fishingButton = findViewById(R.id.fish_button);
		fishingButton.setOnClickListener(this);
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
		case R.id.fish_button:
			if (fishedYet) {
				showToast("you already went fishing today!");
			} else {
				fishedYet = true;
				Intent fishingIntent = new Intent(this, CatchFishActivity.class);
				startActivity(fishingIntent);
			}
			break;
		case R.id.buy_button:
			Intent buyIntent = new Intent(this, Store.class);
			startActivity(buyIntent);
			break;
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
		case R.id.steady:
			family.setPace(PaceTypes.STEADY);
			showToast("changed pace!");
			return true;
		case R.id.strenuous:
			family.setPace(PaceTypes.STRENUOUS);
			showToast("changed pace!");
			return true;
		case R.id.grueling:
			family.setPace(PaceTypes.GRUELING);
			showToast("changed pace!");
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
		fishedYet = false;
		updateMsg = family.nextDay();
		update();
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
		dayText.setText("Day: " + numMiles);
		moneyText.setText("Money: $" + family.getAmt(Item.MONEY));
		foodText.setText("Food: " + family.getAmt(Item.FOOD));
		nameText.setText(family.getName());
		healthText.setText("Health: " + family.getAmt(Item.HEALTH));
		messageText.setText(updateMsg);
		milesLeftText.setText("Miles until destination: " + (50 - numMiles));
	}

	public void startNewGame() {
		family = new Family();
		pickMilestones();
		fishedYet = false;
		numMiles = 0;
		updateMsg = "Get ready for your first day at sea!";
	}

	public void update() {
		if (family == null) {
			startNewGame();
		}
		checkLocation();
		updateFields();
	}

	public void travel() {
		family.incPos(); // travel according to family's pace
	}

	public static Family getFamily() {
		return family;
	}
	
	public void pickMilestones() {
		// set random positions for the milestones
		Random rand = new Random();
		for (int i = 0; i < milestones.length; i++) {
			int randMile = rand.nextInt(50) + 1;
			//make sure that another event isn't already there
			while (Arrays.asList(milestones).contains(randMile)) {
				randMile = rand.nextInt(50) + 1;
			}
			milestones[i] = randMile;
		}
	}

	public void checkLocation() {
		numMiles = family.getPos();
		if (numMiles >= 50) {
			Intent winIntent = new Intent(MainActivity.this, WinActivity.class);
			startActivity(winIntent);
		}
		if (numMiles == milestones[0]) {
			Intent wreckIntent = new Intent(MainActivity.this, FindWreck.class);
			// wreckIntent.putExtra("FamilyObj", family);
			startActivity(wreckIntent);
		}

		if (numMiles == milestones[1]) {
			Intent sharkIntent = new Intent(MainActivity.this, FindShark.class);
			// sharkIntent.putExtra("FamilyObj", family);
			startActivity(sharkIntent);
		}

		if (numMiles == milestones[2]) {
			// Actually turtles
			Intent mermaidIntent = new Intent(MainActivity.this,
					FindMermaid.class);
			// mermaidIntent.putExtra("FamilyObj", family);
			startActivity(mermaidIntent);
		}
	}

}
