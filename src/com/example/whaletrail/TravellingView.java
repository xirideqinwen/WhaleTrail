package com.example.whaletrail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class TravellingView extends View {

	private int xCenter;
	private int yCenter;
	private int y = 0;
	private int yVeloc = 2;
	private int bounceDist = 20;
	private final int FRAME_RATE = 30;
	private Context mContext;
	private Handler handler;

	public TravellingView(Context context) {
		super(context);
		mContext = context;
		handler = new Handler();
	}

	public TravellingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		handler = new Handler();
	}

	public TravellingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		handler = new Handler();
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.blue_background));

		BitmapDrawable whale = (BitmapDrawable) mContext.getResources()
				.getDrawable(R.drawable.whale);

		// figure out centers
		xCenter = getWidth() / 2 - whale.getMinimumWidth() / 2;
		// bounce the whale up and down
		if (this.y < 0 || this.y > bounceDist) {
			this.yVeloc = -this.yVeloc;
		}
		this.y += this.yVeloc;

		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		canvas.drawBitmap(whale.getBitmap(), xCenter, y, null);
		handler.postDelayed(runnable, FRAME_RATE);
	}

}
