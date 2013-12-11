package com.example.whaletrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FindMermaid extends Activity implements OnClickListener {
	
	public Family family;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_mermaid);
		
		initialLayout();
		
		family = MainActivity.getFamily();
	}

	public void initialLayout()
	{
		View mermaidAroundButton = findViewById(R.id.mermaidAroundButton);
		mermaidAroundButton.setOnClickListener(this);
		View mermaidThroughButton = findViewById(R.id.mermaidThroughButton);
		mermaidThroughButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.mermaidAroundButton:
		{
			family.nextDay();
			family.nextDay();
			Intent aroundIntent = new Intent(FindMermaid.this, Around.class);
			startActivity(aroundIntent);
			break;
		}
		case R.id.mermaidThroughButton:
		{
			family.incItem(Item.FOOD, 25);
			Intent throughIntent = new Intent(FindMermaid.this, MermaidThrough.class);
			startActivity(throughIntent);
			break;
		}
		}
	}
}
