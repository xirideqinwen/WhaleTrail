package com.example.whaletrail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Store extends Activity implements OnClickListener {

	private Family family;

	private TextView whaleTV;
	private TextView foodTV;
	private TextView clothingTV;
	private TextView moneyTV;
	private Button buyWhaleButton;
	private Button buyFoodButton;
	private Button buyClothesButton;
	private Button goBackButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		initialLayout();

		family = MainActivity.getFamily();

		updateFields();
	}

	public void initialLayout() {
		whaleTV = (TextView) findViewById(R.id.amountOfWhale);
		foodTV = (TextView) findViewById(R.id.amountOfFood);
		clothingTV = (TextView) findViewById(R.id.amountOfClothes);
		moneyTV = (TextView) findViewById(R.id.amountOfMoney);

		View buyWhaleButton = findViewById(R.id.buyWhale);
		buyWhaleButton.setOnClickListener((OnClickListener) this);
		View buyFoodButton = findViewById(R.id.buyFood);
		buyFoodButton.setOnClickListener(this);
		View buyClothesButton = findViewById(R.id.buyClothes);
		buyClothesButton.setOnClickListener(this);
		View goBackButton = findViewById(R.id.letsGoFromStoreButton);
		goBackButton.setOnClickListener(this);
	}

	// @SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buyWhale:
			buyWhaleFn();
			moneyTV.setText("" + family.getAmt(Item.MONEY));
			whaleTV.setText("" + family.getAmt(Item.WHALE));
			break;
		case R.id.buyFood:
			buyFoodFn();
			moneyTV.setText("" + family.getAmt(Item.MONEY));
			foodTV.setText("" + family.getAmt(Item.FOOD));
			break;
		case R.id.buyClothes:
			buyClothesFn();
			moneyTV.setText("" + family.getAmt(Item.MONEY));
			clothingTV.setText("" + family.getAmt(Item.CLOTHING));
			break;
		case R.id.letsGoFromStoreButton:
			Intent goBackIntent = new Intent(this, MainActivity.class);
			goBackIntent.putExtra("newGame", false);
			startActivity(goBackIntent);
		}
		// updateFields();
	}

	public void buyWhaleFn() {
		family.incItem(Item.WHALE, 1);
		family.incItem(Item.MONEY, -1);
	}

	public void buyFoodFn() {
		family.incItem(Item.FOOD, 1);
		family.incItem(Item.MONEY, -10);
	}

	public void buyClothesFn() {
		family.incItem(Item.CLOTHING, 1);
		family.incItem(Item.MONEY, -10);
	}

	public void updateFields() {
		moneyTV.setText("" + family.getAmt(Item.MONEY));
		foodTV.setText("" + family.getAmt(Item.FOOD));
		whaleTV.setText("" + family.getAmt(Item.WHALE));
		clothingTV.setText("" + family.getAmt(Item.CLOTHING));
	}
}
