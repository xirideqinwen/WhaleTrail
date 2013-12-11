package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FindShark extends Activity implements OnClickListener {
	
	public Family family;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_shark);
		
	initialLayout();
		
		family = MainActivity.getFamily();
	}

	public void initialLayout()
	{
		View sharkAroundButton = findViewById(R.id.sharkAroundButton);
		sharkAroundButton.setOnClickListener(this);
		View sharkThroughButton = findViewById(R.id.sharkThroughButton);
		sharkThroughButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.sharkAroundButton:
		{
			family.nextDay();
			//family.nextDay();
			Intent aroundIntent = new Intent(FindShark.this, Around.class);
			startActivity(aroundIntent);
			break;
		}
		case R.id.sharkThroughButton:
		{
			family.incItem(Item.FOOD, -25);
			family.incItem(Item.HEALTH, -10);
			Intent throughIntent = new Intent(FindShark.this, SharkThrough.class);
			startActivity(throughIntent);
			break;
		}
		}
	}

}
