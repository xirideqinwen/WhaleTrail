package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FindWreck extends Activity implements OnClickListener{
	
	public Family family;
	public int numDays;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_wreck);
		
		initialLayout();
		
		Intent mainIntent = getIntent();
		family = MainActivity.getFamily();
	}

	public void initialLayout()
	{
		View wreckAroundButton = findViewById(R.id.wreckAroundButton);
		wreckAroundButton.setOnClickListener(this);
		View wreckThroughButton = findViewById(R.id.wreckThroughButton);
		wreckThroughButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.wreckAroundButton:
		{
			family.nextDay();
			//family.nextDay();
			Intent aroundIntent = new Intent(this, Around.class);
			startActivity(aroundIntent);
			break;
		}
		case R.id.wreckThroughButton:
		{
			family.incItem(Item.FOOD, 25);
			family.incItem(Item.MONEY, 100);
			Intent throughIntent = new Intent(this, WreckThrough.class);
			startActivity(throughIntent);
			break;
		}
		}
	}

}
