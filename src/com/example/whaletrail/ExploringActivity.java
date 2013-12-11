package com.example.whaletrail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ExploringActivity extends Activity implements OnClickListener {

		
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.travelling_layout);

			// test for clicks on the view
			//View exploringView = (View) findViewById(R.id.exploring_view);
			//exploringView.setOnClickListener(this);
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}

}
